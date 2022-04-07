package outcastfoods.integration.zoho.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import outcastfoods.integration.zoho.mapping.ProductMapping;
import outcastfoods.integration.zoho.model.externalapi.InvoiceDetail;
import outcastfoods.integration.zoho.model.externalapi.InvoiceFetch;
import outcastfoods.integration.zoho.model.internal.PicknPayTransactionType;
import outcastfoods.integration.zoho.model.internal.PnpCsvLine;
import outcastfoods.integration.zoho.model.internal.TabDelimitedLine;
import outcastfoods.integration.zoho.zohoapiclient.ZohoApiClient;

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

    @ConfigProperty(name = "output.statement.dir")
    String statementOutPutDir;

    static final String FILE_NAME_TEMPLATE = "pnp1000011382_[DATE]_outcastfoods_invoices.csv";

    public Object createStatement(String customerId, String dateAfter, String dateBefore) {


        SimpleDateFormat invoiceFileNameDateFormat = new SimpleDateFormat(
                "yyyyMMdd");
        String fileNameDate = invoiceFileNameDateFormat.format(new Date());

        String fullPathStatementFileName = statementOutPutDir + FILE_NAME_TEMPLATE.replace("[DATE]", fileNameDate);

        SimpleDateFormat pnpInvoiceDateFormat = new SimpleDateFormat(
                "dd/MM/yyyy");

        SimpleDateFormat zohoDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");

        try {
            List<TabDelimitedLine> csvLines = new ArrayList<>();

            List<InvoiceFetch> invoiceFetches = getInvoices(customerId, dateAfter, dateBefore);
            for (InvoiceFetch invoiceFetch : invoiceFetches) {

                InvoiceDetail invoice = invoiceService.getInvoice(invoiceFetch.getInvoice_id());
                ArrayList<InvoiceDetail.LineItem> line_items = invoice.getLine_items();
                for (InvoiceDetail.LineItem line_item : line_items) {

                    PnpCsvLine pnpCsvLine = new PnpCsvLine();
                    pnpCsvLine.setInvoiceNumber(invoice.getInvoice_number());

                    Date invoiceDate = zohoDateFormat.parse(invoice.getDate());
                    String pnpInvoiceDate = pnpInvoiceDateFormat.format(invoiceDate);
                    String pnpDueDate = pnpInvoiceDateFormat.format(dateBefore);

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
                    pnpCsvLine.setQuantity(line_item.getQuantity()+"");

                    BigDecimal taxPercentage = line_item.getTax_percentage();

                    BigDecimal linePriceExVat = line_item.getItem_total();
                    linePriceExVat = linePriceExVat.setScale(2, RoundingMode.HALF_UP);

                    BigDecimal linePriceInclVat = linePriceExVat.multiply(taxPercentage);
                    linePriceInclVat = linePriceInclVat.setScale(2, RoundingMode.HALF_UP);


                    pnpCsvLine.setTaxPercentage(taxPercentage.toString());
                    pnpCsvLine.setPriceExVat(linePriceExVat.toString());
                    pnpCsvLine.setPriceInclVat(linePriceInclVat.toString());
                }

                SimpleDateFormat invFormat = new SimpleDateFormat(
                        "yyyy-MM-dd");
                Date invoiceDate = invFormat.parse(invoiceFetch.getDate());
                SimpleDateFormat tabDocLineFormat = new SimpleDateFormat(
                        "yyyy.MM.dd");
                String tabDelimDate = tabDocLineFormat.format(invoiceDate);

                TabDelimitedLine tabDelimitedLine = new TabDelimitedLine();
                tabDelimitedLine.setDate(tabDelimDate);
                tabDelimitedLine.setTransactionType(PicknPayTransactionType.INV);
                tabDelimitedLine.setAmount(invoiceFetch.getTotal() + "");
                tabDelimitedLine.setReference1(invoiceFetch.getInvoice_number());
                tabDelimitedLine.setReference2(invoiceFetch.getReference_number());

                csvLines.add(tabDelimitedLine);
            }

            try (PrintWriter writer = new PrintWriter(
                    Files.newBufferedWriter(Paths.get(fullPathStatementFileName)))) {

                for (TabDelimitedLine tabDelimitedLine : csvLines) {

                    writer.print(tabDelimitedLine.getDate()+"\t");
                    writer.print(tabDelimitedLine.getTransactionType().toString()+"\t");
                    writer.print(tabDelimitedLine.getAmount()+"\t");
                    writer.print(tabDelimitedLine.getReference1()+"\t");
                    writer.print(tabDelimitedLine.getReference2()+"\t");
                    writer.println();
                }
            }


        } catch (Throwable t) {
            LOG.error("Can't create pick n pay statement:", t);
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
                final InvoiceFetch invoiceFetch = mapper.convertValue(invoicesMap, InvoiceFetch.class);
                invoiceFetches.add(invoiceFetch);
            }

        } catch (Throwable t) {
            LOG.error("Can't get invoices:", t);

        }

        return invoiceFetches;
    }


}
