package my.integration.zoho.service;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import my.integration.zoho.mapping.ProductMapping;
import my.integration.zoho.model.externalapi.InvoiceDetail;
import my.integration.zoho.model.externalapi.InvoiceFetch;
import my.integration.zoho.model.externalapi.InvoiceLineItem;
import my.integration.zoho.model.internal.PnpCsvLine;
import my.integration.zoho.zohoapiclient.ZohoApiClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@ApplicationScoped
public class PnpCsvEdiInvoiceService {

    private static final Logger LOG = Logger.getLogger(PnpCsvEdiInvoiceService.class);


    @Inject
    AuthService authService;

    @RestClient
    ZohoApiClient zohoApiClient;

    @Inject
    InvoiceService invoiceService;

    @ConfigProperty(name = "output.invoice.dir.pnp")
    String invoiceOutPutDir;

    static final String FILE_NAME_TEMPLATE = "pnp1000011382_[DATE]_outcastfoods_invoices.csv";

    public Object createCsvInvoiceFile(String customerId, String dateAfter, String dateBefore) {


        SimpleDateFormat invoiceFileNameDateFormat = new SimpleDateFormat(
                "yyyyMMdd");
        String fileNameDate = invoiceFileNameDateFormat.format(new Date());

        String fullPathStatementFileName = invoiceOutPutDir + FILE_NAME_TEMPLATE.replace("[DATE]", fileNameDate);

        SimpleDateFormat pnpInvoiceDateFormat = new SimpleDateFormat(
                "dd/MM/yyyy");

        SimpleDateFormat zohoDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");

        try {
            List<PnpCsvLine> csvLines = new ArrayList<>();

            List<InvoiceFetch> invoiceFetches = getInvoices(customerId, dateAfter, dateBefore);
            for (InvoiceFetch invoiceFetch : invoiceFetches) {

                InvoiceDetail invoice = invoiceService.getInvoice(invoiceFetch.getInvoice_id());
                ArrayList<InvoiceLineItem> line_items = invoice.getLine_items();
                for (InvoiceLineItem line_item : line_items) {

                    PnpCsvLine pnpCsvLine = new PnpCsvLine();
                    pnpCsvLine.setInvoiceNumber(invoice.getInvoice_number());

                    Date invoiceDate = zohoDateFormat.parse(invoice.getDate());
                    Date dueDate = zohoDateFormat.parse(dateBefore);
                    String pnpInvoiceDate = pnpInvoiceDateFormat.format(invoiceDate);
                    String pnpDueDate = pnpInvoiceDateFormat.format(dueDate);

                    pnpCsvLine.setDate(pnpInvoiceDate);
                    pnpCsvLine.setDueDate(pnpDueDate);
                    pnpCsvLine.setPoNumber(invoice.getReference_number());

                    BigDecimal total = invoice.getTotal();
                    total = total.setScale(2, RoundingMode.HALF_UP);

                    pnpCsvLine.setInvoiceTotal(total.toString());

                    ProductMapping productMapping = ProductMapping.findByItemId(line_item.getItem_id() + "");

                    pnpCsvLine.setBarcode(productMapping.getCaseOf6Barcode());
                    pnpCsvLine.setVendorProductCode(line_item.getName());
                    pnpCsvLine.setArticleDescription(productMapping.getArticleDescription());
                    //int numberOfCases = line_item.getQuantity() / 6;
                    pnpCsvLine.setQuantity(line_item.getQuantity()+"");

                    BigDecimal taxPercentage = line_item.getTax_percentage();

                    BigDecimal linePriceExVat = line_item.getItem_total();
                    linePriceExVat = linePriceExVat.setScale(2, RoundingMode.HALF_UP);

                    BigDecimal linePriceInclVat = linePriceExVat.multiply((taxPercentage.divide(BigDecimal.valueOf(100),2,RoundingMode.HALF_UP)).add(BigDecimal.ONE));
                    linePriceInclVat = linePriceInclVat.setScale(2, RoundingMode.HALF_UP);

                    pnpCsvLine.setTaxPercentage(taxPercentage.toString());
                    pnpCsvLine.setPriceExVat(linePriceExVat.toString());
                    pnpCsvLine.setPriceInclVat(linePriceInclVat.toString());

                    csvLines.add(pnpCsvLine);
                }

            }

            try (PrintWriter writer = new PrintWriter(
                    Files.newBufferedWriter(Paths.get(fullPathStatementFileName)))) {

                for (PnpCsvLine pnpCsvLine : csvLines) {

                    writer.print(pnpCsvLine.getInvoiceNumber()+",");
                    writer.print(pnpCsvLine.getDate()+",");
                    writer.print(pnpCsvLine.getDueDate()+",");
                    writer.print(pnpCsvLine.getPoNumber()+",");
                    writer.print("ZAR,");
                    writer.print(pnpCsvLine.getInvoiceTotal() + ",");
                    writer.print(pnpCsvLine.getBarcode() + ",");
                    writer.print(pnpCsvLine.getVendorProductCode() + ",");
                    writer.print(pnpCsvLine.getArticleDescription() + ",");
                    writer.print(pnpCsvLine.getQuantity() + ",");
                    writer.print("CS,");
                    writer.print("STANDARD_RATE,");
                    writer.print(pnpCsvLine.getTaxPercentage() + ",");
                    writer.print(pnpCsvLine.getPriceExVat() + ",");
                    writer.print(pnpCsvLine.getPriceInclVat());

                    writer.println();
                }
            }


        } catch (Throwable t) {
            LOG.error("Can't create pick n pay order invoices:", t);
            t.printStackTrace();
        }

        return fullPathStatementFileName;
    }


    /**
     *
     * @param customerId
     * @param dateAfter  yyyy-mm-dd
     * @param dateBefore  yyyy-mm-dd
     * @return
     */
    public List<InvoiceFetch> getInvoices(String customerId, String dateAfter, String dateBefore) {

        List<InvoiceFetch> invoiceFetches = new ArrayList<>();

        try {

            HashMap response = (HashMap) zohoApiClient.getInvoices(authService.getAccessToken().getBearerToken(),
                    customerId,
                    dateAfter,
                    dateBefore);
            List<Map> invoicesMaps = (List<Map>) response.get("invoices");

            for (Map invoicesMap : invoicesMaps) {
                ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                final InvoiceFetch invoiceFetch = mapper.convertValue(invoicesMap, InvoiceFetch.class);
                invoiceFetches.add(invoiceFetch);
            }

        } catch (Throwable t) {
            LOG.error("Can't get invoices:", t);

        }

        return invoiceFetches;
    }


}
