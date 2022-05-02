package outcastfoods.integration.zoho.service;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import outcastfoods.integration.zoho.mapping.ProductMapping;
import outcastfoods.integration.zoho.mapping.RegionMapping;
import outcastfoods.integration.zoho.model.externalapi.LineItem;
import outcastfoods.integration.zoho.model.internal.Order;
import outcastfoods.integration.zoho.model.internal.OrderCsvLine;
import outcastfoods.integration.zoho.utils.CsvUtils;

import javax.enterprise.context.ApplicationScoped;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class WriteOrderFileService {


    private static final Logger LOG = Logger.getLogger(WriteOrderFileService.class);

    @ConfigProperty(name = "output.pnp.order.dir")
    String orderOutPutDir;

    static final String BRAND_CATZ_REGION_ORDERS_INFO_DATE_CSV = "BrandCatz-[REGION]-Orders-Info-[DATE].csv";
    static final String UNMATCHED_ORDERS_CSV = "Unmatched-Orders-[DATE].csv";


    public Object createOrderCsv(List<Order> orders, RegionMapping regionMapping) {

        SimpleDateFormat csvDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String fileNameDate = csvDateFormat.format(new Date());
        String fullPathFileName = orderOutPutDir
                + BRAND_CATZ_REGION_ORDERS_INFO_DATE_CSV.replace("[DATE]", fileNameDate).replace("[REGION]",regionMapping.name());

        try {

            Order ordersHeader = new Order();
            ordersHeader.setPoNumber("PO NUMBER");
            ordersHeader.setStoreCode("STORE CODE");
            ordersHeader.setStoreDescription("STORE NAME");
            ordersHeader.setInvoiceNumber("INVOICE NUMBER");
            ordersHeader.setDeliveryDate("DUE DATE");
          /*  ordersHeader.setClassicNumberOfCases("Falafel Classic Case(6)");
            ordersHeader.setCrazyNumberOfCases("Falafel Crazy Case(6)");
            ordersHeader.setBurgerNumberOfCases("Burger Mix Cases(6)");
            ordersHeader.setFlapjackNumberOfCases("Flapjacks Cases(6)");*/

            ordersHeader.setPod("POD");

            orders.add(0,ordersHeader);

            try (PrintWriter writer = new PrintWriter(
                    Files.newBufferedWriter(Paths.get(fullPathFileName)))) {

                int count = 0;
                for (Order order : orders) {

                    Map<ProductMapping, String> productToQuantity = new HashMap();

                    // Don't do the header
                    if(count > 0 ) {
                        List<LineItem> lineItems = order.getLineItems();
                        for (LineItem lineItem : lineItems) {
                            ProductMapping product = ProductMapping.findByItemId(lineItem.getItem_id());
                            Integer numberOfCases = Integer.valueOf(lineItem.getQuantity());
                            productToQuantity.put(product, numberOfCases.toString());
                        }
                    }

                    writer.print(order.getPoNumber()+",");
                    writer.print(order.getStoreCode()+",");
                    writer.print(order.getStoreDescription()+",");
                    writer.print(order.getInvoiceNumber()+",");
                    writer.print(order.getDeliveryDate()+",");
                    if(count == 0) {
                        //for the header
                        writer.print("Falafel Classic Case(6),");
                        writer.print("Falafel Crazy Case(6),");
                        writer.print("Burger Mix Cases(6),");
                        writer.print("Flapjacks Cases(6),");
                    } else {
                        writer.print(CsvUtils.getCaseQuantityForProduct(productToQuantity, ProductMapping.CLASSIC_FALAFEL)+",");
                        writer.print(CsvUtils.getCaseQuantityForProduct(productToQuantity, ProductMapping.CRAZY_FALAFEL)+",");
                        writer.print(CsvUtils.getCaseQuantityForProduct(productToQuantity, ProductMapping.BURGER_MIX)+",");
                        writer.print(CsvUtils.getCaseQuantityForProduct(productToQuantity, ProductMapping.FLAPJACKS)+",");
                    }
                    writer.print(order.getPod()+"");



                    writer.println();
                    count ++;
                }
            }

        } catch (Throwable t) {
            LOG.error("Can't create pick n pay order file:", t);
            t.printStackTrace();
        }

        return fullPathFileName;
    }


    public Object createUnmatchedOrdersCsv(List<OrderCsvLine> orders) {

        SimpleDateFormat csvDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String fileNameDate = csvDateFormat.format(new Date());
        String fullPathFileName = orderOutPutDir
                + UNMATCHED_ORDERS_CSV.replace("[DATE]", fileNameDate);

        try {

            OrderCsvLine ordersHeader = new OrderCsvLine();
            ordersHeader.setPoNumber("PO NUMBER");
            ordersHeader.setStoreCode("STORE CODE");
            ordersHeader.setStoreDescription("STORE NAME");
            ordersHeader.setDeliveryDate("DUE DATE");

            orders.add(0,ordersHeader);

            try (PrintWriter writer = new PrintWriter(
                    Files.newBufferedWriter(Paths.get(fullPathFileName)))) {

                for (OrderCsvLine order : orders) {

                    writer.print(order.getPoNumber()+",");
                    writer.print(order.getStoreCode()+",");
                    writer.print(order.getStoreDescription()+",");
                    writer.print(order.getDeliveryDate()+",");

                    writer.println();
                }
            }

        } catch (Throwable t) {
            LOG.error("Can't create unmatched stores file:", t);
            t.printStackTrace();
        }

        return fullPathFileName;
    }
}
