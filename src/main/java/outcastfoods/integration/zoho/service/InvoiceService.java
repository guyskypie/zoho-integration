package outcastfoods.integration.zoho.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections4.ListUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import outcastfoods.integration.zoho.exception.DataException;
import outcastfoods.integration.zoho.mapping.ProductMapping;
import outcastfoods.integration.zoho.mapping.RegionMapping;
import outcastfoods.integration.zoho.mapping.StoreInfoMapping;
import outcastfoods.integration.zoho.model.externalapi.*;
import outcastfoods.integration.zoho.model.internal.InvoiceInfo;
import outcastfoods.integration.zoho.model.internal.Order;
import outcastfoods.integration.zoho.model.internal.OrderCsvLine;
import outcastfoods.integration.zoho.utils.CsvUtils;
import outcastfoods.integration.zoho.utils.DateUtils;
import outcastfoods.integration.zoho.zohoapiclient.CreateInvoiceClient;
import outcastfoods.integration.zoho.zohoapiclient.ZohoApiClient;

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
public class InvoiceService {

    private static final Logger LOG = Logger.getLogger(InvoiceService.class);

    @Inject
    AuthService authService;

    @RestClient
    ZohoApiClient zohoApiClient;

    @Inject
    CreateInvoiceClient createInvoiceClient;


    @ConfigProperty(name = "output.invoice.dir")
    String invoiceOutPutDir;

    static final String INVOICE_FILE_NAME_TEMPLATE = "invoices-[CUSTOMER_NAME]-[DATE]-doc[NUM].pdf";

    public List<String> getInvoicesAndDownload(List<String> zohoCustomerIds, String customerName,
                                                  String dateAfter, String dateBefore) throws IOException, DataException {
        //List<InvoiceFetch> invoices = new ArrayList<>();
        List<String> invoiceIds = new ArrayList<>();
        for (String zohoCustomerId : zohoCustomerIds) {
            List<InvoiceFetch> invoices =getInvoices(zohoCustomerId, dateAfter, dateBefore);

            for (InvoiceFetch invoice : invoices) {
                invoiceIds.add(invoice.getInvoice_id());
            }
        }


        int count = 1;
        List<List<String>> invoiceIdsSplitLists = ListUtils.partition(invoiceIds, 25);
        for (List<String> invoiceIdsPerFile : invoiceIdsSplitLists) {

            String csvInvoiceNumbers = String.join(",", invoiceIdsPerFile);

            SimpleDateFormat pdfFileDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String fileNameDate = pdfFileDateFormat.format(new Date());
            String fullPathStatementFileName = invoiceOutPutDir
                    + INVOICE_FILE_NAME_TEMPLATE.replace("[DATE]", fileNameDate)
                    .replace("[CUSTOMER_NAME]", customerName)
                    .replace("[NUM]", count + "");

            createInvoiceClient.downloadInvoicePdfs(new File(fullPathStatementFileName), csvInvoiceNumbers);

            count ++;
        }

        return invoiceIds;
    }



    public List<InvoiceFetch> getInvoices(String customerId, String dateAfter, String dateBefore ) throws DataException{

        List<InvoiceFetch> invoiceFetches = new ArrayList<>();

        try{
            HashMap response = (HashMap) zohoApiClient.getInvoices(authService.getAccessToken().getBearerToken(), customerId, dateAfter, dateBefore);
            List<Map> invoicesMaps = (List<Map>)response.get("invoices");

            for (Map invoicesMap : invoicesMaps) {
                ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
                final InvoiceFetch invoiceFetch = mapper.convertValue(invoicesMap, InvoiceFetch.class);
                invoiceFetches.add(invoiceFetch);
            }


        } catch (Throwable t){
            LOG.error("Can't get invoices:", t);
            throw new DataException("Can't get invoices",t);
        }

        return invoiceFetches;
    }

    public InvoiceDetail getInvoice(String id ) throws DataException {

        try{
            HashMap response = (HashMap) zohoApiClient.getInvoice(authService.getAccessToken().getBearerToken(), id);
            Map invoicesMap = (Map)response.get("invoice");

            ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            InvoiceDetail invoiceDetail = mapper.convertValue(invoicesMap, InvoiceDetail.class);

            return invoiceDetail;
        } catch (Throwable t){
            LOG.error("Can't get invoice:", t);
            throw new DataException("Can't get invoice",t);
        }


    }

    public List<Payment> getPayments(String customerName, String dateAfter, String dateBefore ) throws DataException{

        List<Payment> payments = new ArrayList<>();
        try{
            //HashMap response = (HashMap) zohoInvoiceService.getPayments(authService.getAccessToken().getBearerToken(),
            HashMap response = (HashMap) zohoApiClient.getPayments(authService.getAccessToken().getBearerToken(),
                    customerName, dateAfter, dateBefore);
            ArrayList paymentsList = (ArrayList)response.get("customerpayments");

            ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            for (Object o : paymentsList) {
                payments.add(mapper.convertValue(o, Payment.class));
            }


        } catch (Throwable t){
            LOG.error("Can't get customer:", t);
            throw new DataException("Can't get customer",t);
        }

        return payments;
    }




    public  List<CreditNote> getCreditNotes(String customerId, String dateAfter, String dateBefore) throws DataException{

        List<CreditNote> creditNotes = new ArrayList<>();

        try{

            //HashMap response = (HashMap) zohoInvoiceService.getPayments(authService.getAccessToken().getBearerToken(),
            HashMap response = (HashMap) zohoApiClient.getCreditNotes(authService.getAccessToken().getBearerToken(),
                    customerId, dateAfter, dateBefore, "open");

            ArrayList creditNotesList = (ArrayList)response.get("creditnotes");

            ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            for (Object o : creditNotesList) {
                creditNotes.add(mapper.convertValue(o, CreditNote.class));
            }


        } catch (Throwable t){
            LOG.error("Can't get credit notes:", t);
            throw new DataException("Can't get credit notes:",t);
        }

        return creditNotes;
    }
}
