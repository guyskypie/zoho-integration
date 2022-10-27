package outcastfoods.integration.zoho.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import outcastfoods.integration.zoho.mapping.ProductMapping;
import outcastfoods.integration.zoho.model.externalapi.InvoiceDetail;
import outcastfoods.integration.zoho.model.externalapi.InvoiceFetch;
import outcastfoods.integration.zoho.model.externalapi.InvoiceLineItem;
import outcastfoods.integration.zoho.model.externalapi.InvoiceStateEnum;
import outcastfoods.integration.zoho.model.internal.SparCsvLine;
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
public class SparCsvEdiInvoiceService {

    private static final Logger LOG = Logger.getLogger(SparCsvEdiInvoiceService.class);


    @Inject
    AuthService authService;

    @RestClient
    ZohoApiClient zohoApiClient;

    @Inject
    InvoiceService invoiceService;

    @ConfigProperty(name = "output.invoice.dir.spar")
    String invoiceOutPutDir;

    static final String FILE_NAME_TEMPLATE = "OutcastFoods_203149_[DATE].csv";

    public Object createXcelInvoiceSchedule(String customerId, String dateAfter, String dateBefore) {


        String[] splitCustomerIds = customerIds.split(",");

        SimpleDateFormat invoiceFileNameDateFormat = new SimpleDateFormat(
                "ddMMyyyy");

        SimpleDateFormat scheduleDateFormat = new SimpleDateFormat(
                "dd/MM/yyyy");

        String fileNameDate = invoiceFileNameDateFormat.format(new Date());

        String fullPathStatementFileName = invoiceOutPutDir + FILE_NAME_TEMPLATE.replace("[DATE]", fileNameDate);

        SimpleDateFormat zohoDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");

        try {
            List<SparCsvLine> csvLines = new ArrayList<>();

            for (String customerId : splitCustomerIds) {
                List<InvoiceFetch> invoiceFetches = getInvoices(customerId, dateAfter, dateBefore);
                for (InvoiceFetch invoiceFetch : invoiceFetches) {

                    InvoiceDetail invoice = invoiceService.getInvoice(invoiceFetch.getInvoice_id());

                    Map customer_custom_field_hash = invoice.getCustomer_custom_field_hash();
                    String storeCode = (String)customer_custom_field_hash.get("cf_store_code");

                    Date invoiceDate = zohoDateFormat.parse(invoice.getDate());
                    String sparInvoiceDate = scheduleDateFormat.format(invoiceDate);

                    // only add items in draft, once added to schedule mark as sent manually for now
                    if(invoice.getStatus().equals(InvoiceStateEnum.DRAFT.getZohoState())){

                        SparCsvLine sparCsvLine = new SparCsvLine();
                        sparCsvLine.setStoreCode(storeCode);
                        sparCsvLine.setStoreName(invoice.getCustomer_name());
                        sparCsvLine.setDocNo(invoice.getInvoice_number());
                        sparCsvLine.setDate(sparInvoiceDate);

                        BigDecimal totalExcl = invoice.getSub_total();
                        totalExcl = totalExcl.setScale(2, RoundingMode.HALF_UP);

                        BigDecimal totalInclusive = invoice.getTotal();
                        totalInclusive = totalInclusive.setScale(2, RoundingMode.HALF_UP);

                        BigDecimal vatAmount = invoice.getTax_total();
                        vatAmount = vatAmount.setScale(2, RoundingMode.HALF_UP);

                        sparCsvLine.setExcl(totalExcl.toString());
                        sparCsvLine.setVat(vatAmount.toString());
                        sparCsvLine.setIncl(totalInclusive.toString());
                        csvLines.add(sparCsvLine);

                    }

                }
            }


            try (PrintWriter writer = new PrintWriter(
                    Files.newBufferedWriter(Paths.get(fullPathStatementFileName)))) {

                writeScheduleHeader(writer);
                for (SparCsvLine sparCsvLine : csvLines) {

                    writeScheduleLine(writer, sparCsvLine);
                }
            }


        } catch (Throwable t) {
            LOG.error("Can't create spar schedul file:", t);
            t.printStackTrace();
        }

        return fullPathStatementFileName;
    }

    private void writeScheduleLine(PrintWriter writer, SparCsvLine sparCsvLine) {
        writer.print(sparCsvLine.getStoreCode()+",");
        writer.print(sparCsvLine.getStoreName()+",");
        writer.print(sparCsvLine.getDocNo()+",");
        writer.print(sparCsvLine.getDate()+",");
        writer.print(sparCsvLine.getExcl()+",");
        writer.print(sparCsvLine.getVat()+",");
        writer.print(sparCsvLine.getIncl()+",");
        writer.print(",");
        writer.print("");

        writer.println();
    }

    private void writeScheduleHeader(PrintWriter writer) {
        writer.print("Store code,");
        writer.print("Store Name,");
        writer.print("Doc no,");
        writer.print("Date,");
        writer.print("Excl,");
        writer.print("Vat,");
        writer.print("incl,");
        writer.print("Referring Invoice No,");
        writer.print("Referring Claim No");


        writer.println();
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
