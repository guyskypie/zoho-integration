package outcastfoods.integration.zoho.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.collections4.ListUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import outcastfoods.integration.zoho.exception.DataException;
import outcastfoods.integration.zoho.mapping.StoreInfoMapping;
import outcastfoods.integration.zoho.model.internal.InvoiceInfo;
import outcastfoods.integration.zoho.utils.DateUtils;
import outcastfoods.integration.zoho.zohoapiclient.CreateInvoiceClient;
import outcastfoods.integration.zoho.zohoapiclient.ZohoApiClient;
import outcastfoods.integration.zoho.mapping.ProductMapping;
import outcastfoods.integration.zoho.mapping.RegionMapping;
import outcastfoods.integration.zoho.model.externalapi.*;
import outcastfoods.integration.zoho.model.internal.Order;
import outcastfoods.integration.zoho.model.internal.OrderCsvLine;
import outcastfoods.integration.zoho.utils.CsvUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class PnpZohoInvoiceService {

    private static final Logger LOG = Logger.getLogger(PnpZohoInvoiceService.class);

    @Inject
    AuthService authService;

    @RestClient
    ZohoApiClient zohoApiClient;

    @Inject
    WriteOrderFileService writeOrderFileService;

    @Inject
    CreateInvoiceClient createInvoiceClient;


    @ConfigProperty(name = "input.pnp.csv.upload.folder")
    String inputFolder;

    @ConfigProperty(name = "output.pnp.order.dir")
    String orderOutPutDir;


    @ConfigProperty(name = "valid.pnp.storecodes")
    String validStoreCodesCsv;

    List<String> validStoreCodes;


    static final String INVOICE_FILE_NAME_TEMPLATE = "invoices-[REGION]-[DATE]-doc[NUM].pdf";


    static String PICK_N_PAY_CUSTOMER_ID = "REDACTED_ID";


    @PostConstruct
    public void init(){
        String[] validStoreCodesArr = validStoreCodesCsv.split(",");
        validStoreCodes = Arrays.asList(validStoreCodesArr);
    }

    public List<OrderCsvLine>  createInvoicesAndOrderFiles() throws IOException, InterruptedException, ParseException {

        List<OrderCsvLine> orderCsvLines = CsvUtils.readOrderLines(inputFolder);

        //now group by PO and translate to an Order
        Map<String, List<OrderCsvLine>> groupByPOMap =
                orderCsvLines.stream().collect(Collectors.groupingBy(OrderCsvLine::getPoNumber));

        List<Order> orders = new ArrayList<>();

        Map<String, OrderCsvLine> unMatchedOrdersMap = new HashMap<>();
        //List<OrderCsvLine> unMatchedOrders = new ArrayList<>();

        for (String poNumber : groupByPOMap.keySet()) {

            List<OrderCsvLine> orderLines = groupByPOMap.get(poNumber);

            List<LineItem> lineItems = new ArrayList<>();
            Order order = new Order();
            int itemCount = 0;
            for (OrderCsvLine orderLine : orderLines) {
                if(validStoreCodes.contains(orderLine.getStoreCode().trim())){
                    LineItem lineItem = new LineItem();
                    lineItem.setItem_id(ProductMapping.findItemIdByProductCode(orderLine.getProductCode()));
                    Integer quantityToOrder = /*orderLine.getPackSize() **/ orderLine.getQuantity();
                    lineItem.setQuantity(quantityToOrder.toString());

                    lineItems.add(lineItem);

                    if(itemCount == 0){
                        order.setDeliveryDate(orderLine.getDeliveryDate());
                        order.setStoreDescription(orderLine.getStoreDescription());
                        order.setStoreCode(orderLine.getStoreCode());
                        order.setPoNumber(orderLine.getPoNumber());
                    }

                    itemCount++;
                } else if (unMatchedOrdersMap.get(orderLine.getPoNumber()) == null){
                    // if not already added, add to list
                    unMatchedOrdersMap.put(orderLine.getPoNumber(),orderLine);
                }

            }

            // check if this was a valid store order
            if(order.getPoNumber() != null){
                order.setLineItems(lineItems);
                orders.add(order);
            }

        }

        //Now group the orders into JHB,CT and KZN
        List<Order> wcOrders = getOrdersByStoreCodeParts(orders, RegionMapping.WC.getCodes());
        List<Order> gautengOrders = getOrdersByStoreCodeParts(orders, RegionMapping.GAUTENG.getCodes());
        List<Order> kznOrders = getOrdersByStoreCodeParts(orders, RegionMapping.KZN.getCodes());

        generateInvoicesAndFiles(wcOrders, RegionMapping.WC);
        generateInvoicesAndFiles(gautengOrders, RegionMapping.GAUTENG);
        generateInvoicesAndFiles(kznOrders, RegionMapping.KZN);

        //now download
        writeOrderFileService.createUnmatchedOrdersCsv(new ArrayList<>(unMatchedOrdersMap.values()));

        return null;
    }

    private List<String> generateInvoicesAndFiles(List<Order> orders, RegionMapping regionMapping) throws IOException, InterruptedException, ParseException {
        List<String> invoiceIds = new ArrayList<>();

        //Map<String, String> poNumberToInvoiceNumber = new HashMap<>();
        //now write the order files
        for (Order order : orders) {
            InvoiceCreate invoiceCreate = new InvoiceCreate();
            invoiceCreate.setInvoiceDate(DateUtils.getTodaysDate());
            invoiceCreate.setCustomer_id(PICK_N_PAY_CUSTOMER_ID);
            invoiceCreate.setReference_number(order.getPoNumber());
            invoiceCreate.setNotes(order.getStoreCode() + " - " + order.getStoreDescription()
                    + "\n - Address: " + StoreInfoMapping.findByStoreCode(order.getStoreCode()).getAddress()
                    + "\n - Due Date: " + order.getDeliveryDate());
            invoiceCreate.setLineItems(order.getLineItems());

            InvoiceInfo invoice = createInvoiceClient.createInvoice(invoiceCreate);

           // poNumberToInvoiceNumber.put(order.getPoNumber(), invoiceNumber);
            order.setInvoiceNumber(invoice.getInvoiceNumber());
            order.setInvoiceId(invoice.getId());

            invoiceIds.add(invoice.getId());
        }

        writeOrderFileService.createOrderCsv(orders, regionMapping);


        int count = 1;
        List<List<String>> invoiceIdsSplitLists = ListUtils.partition(invoiceIds, 25);
        for (List<String> invoiceIdsPerFile : invoiceIdsSplitLists) {

            String csvInvoiceNumbers = String.join(",", invoiceIdsPerFile);

            SimpleDateFormat pdfFileDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String fileNameDate = pdfFileDateFormat.format(new Date());
            String fullPathStatementFileName = orderOutPutDir
                    + INVOICE_FILE_NAME_TEMPLATE.replace("[DATE]", fileNameDate)
                    .replace("[REGION]", regionMapping.name())
                    .replace("[NUM]", count + "");

            createInvoiceClient.downloadInvoicePdfs(new File(fullPathStatementFileName), csvInvoiceNumbers);
            //zohoApiClient.getInvoices(authService.getAccessToken().getBearerToken(), PICK_N_PAY_CUSTOMER_ID, csvInvoiceNumbers);
            count ++;
        }

        return invoiceIds;
    }

    /**
     *
     * @param orders
     * @param storeCodePart list of the start of all store codes to collect
     * @return filtered list by the codes
     */
    private List<Order> getOrdersByStoreCodeParts(List<Order> orders, String [] storeCodePart) {

        List<Order> allRegionOrders = new ArrayList<>();

        for (String storeCodePrefix : storeCodePart) {

            List<Order> prefixOrders = orders.stream()
                    .filter(order -> order.getStoreCode().contains(storeCodePrefix))
                    .collect(Collectors.toList());

            allRegionOrders.addAll(prefixOrders);
        }



        return allRegionOrders;
    }
}
