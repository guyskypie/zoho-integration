package outcastfoods.integration.zoho.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import outcastfoods.integration.zoho.zohoapiclient.ZohoApiClient;
import outcastfoods.integration.zoho.model.externalapi.InvoiceFetch;
import outcastfoods.integration.zoho.model.internal.PicknPayTransactionType;
import outcastfoods.integration.zoho.model.internal.TabDelimitedLine;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@ApplicationScoped
public class TabDelimitedPnPStatementService {

    private static final Logger LOG = Logger.getLogger(TabDelimitedPnPStatementService.class);


    @Inject
    AuthService authService;

    @RestClient
    ZohoApiClient zohoApiClient;

    @ConfigProperty(name = "output.statement.dir.pnp")
    String statementOutPutDir;

    static final String FILE_NAME_TEMPLATE = "pnp1000011382_[DATE]_outcastfoods_Enterprises_Pick_n_Pay_Retailers.txt";

    public Object createStatement(String customerId, String dateBefore) {

        SimpleDateFormat tabDocDateFormat = new SimpleDateFormat(
                "yyyyMMdd");
        String fileNameDate = tabDocDateFormat.format(new Date());
        String fullPathStatementFileName = statementOutPutDir + FILE_NAME_TEMPLATE.replace("[DATE]", fileNameDate);

        try {

            List<TabDelimitedLine> tabDelimitedLines = new ArrayList<>();

            TabDelimitedLine tabDelimitedHeader = new TabDelimitedLine();
            tabDelimitedHeader.setDate("Date");
            tabDelimitedHeader.setTransactionType(PicknPayTransactionType.TransType);
            tabDelimitedHeader.setAmount("Amount");
            tabDelimitedHeader.setReference1("Reference1");
            tabDelimitedHeader.setReference2("Reference2");

            tabDelimitedLines.add(tabDelimitedHeader);

            List<InvoiceFetch> invoiceFetches = getInvoicesByStatus(customerId, "unpaid", dateBefore);
            for (InvoiceFetch invoiceFetch : invoiceFetches) {

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

                tabDelimitedLines.add(tabDelimitedLine);
            }

            try (PrintWriter writer = new PrintWriter(
                    Files.newBufferedWriter(Paths.get(fullPathStatementFileName)))) {

                for (TabDelimitedLine tabDelimitedLine : tabDelimitedLines) {

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

    public List<InvoiceFetch> getInvoicesByStatus(String customerId, String status, String dateBefore) {

        List<InvoiceFetch> invoiceFetches = new ArrayList<>();

        try {
            String token = authService.getAccessToken().getBearerToken();
            HashMap response = (HashMap) zohoApiClient.getInvoicesByStatus(token,
                    customerId,
                    status,
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
