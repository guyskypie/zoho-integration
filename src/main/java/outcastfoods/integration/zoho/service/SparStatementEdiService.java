package outcastfoods.integration.zoho.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import outcastfoods.integration.zoho.mapping.OutcastMockDBConstants;
import outcastfoods.integration.zoho.model.externalapi.InvoiceDetail;
import outcastfoods.integration.zoho.model.externalapi.InvoiceFetch;
import outcastfoods.integration.zoho.model.externalapi.InvoiceStateEnum;
import outcastfoods.integration.zoho.model.internal.SparCsvLine;
import outcastfoods.integration.zoho.model.internal.StatementLine;
import outcastfoods.integration.zoho.zohoapiclient.ZohoApiClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

@ApplicationScoped
public class SparStatementEdiService {

    private static final Logger LOG = Logger.getLogger(SparStatementEdiService.class);

    @Inject
    AuthService authService;

    @RestClient
    ZohoApiClient zohoApiClient;

    @Inject
    InvoiceService invoiceService;

    @ConfigProperty(name = "output.statement.dir.spar")
    String invoiceOutPutDir;

    static final String FILE_NAME_TEMPLATE = "OutcastFoods_203149_statement_[DATE].xlsx";


    public String createXcelStatement(String customerIds, String dateAfter, String dateBefore) {


        SimpleDateFormat invoiceFileNameDateFormat = new SimpleDateFormat(
                "ddMMyyyy");

        SimpleDateFormat scheduleDateFormat = new SimpleDateFormat(
                "dd/MM/yyyy");

        String fileNameDate = invoiceFileNameDateFormat.format(new Date());

        String fullPathStatementFileName = invoiceOutPutDir + FILE_NAME_TEMPLATE.replace("[DATE]", fileNameDate);

        List<StatementLine> statementLines = getStatementLines(customerIds, dateAfter, dateBefore);

        Workbook xcelWorkbook = createFileAndHeaders(dateAfter, dateBefore);
        Sheet sheet = xcelWorkbook.getSheetAt(0);
        int rowPos = 2;
        for (StatementLine statementLine : statementLines) {

            addXcelStatementLine(xcelWorkbook, sheet, rowPos, statementLine);

            rowPos ++;
        }

        try (FileOutputStream outputStream = new FileOutputStream(fullPathStatementFileName)) {

            xcelWorkbook.write(outputStream);
            xcelWorkbook.close();
        } catch (Throwable t) {
            LOG.error("Can't get spar statement lines:", t);
            t.printStackTrace();
        }

        return fullPathStatementFileName;
    }

    private void addXcelStatementLine(Workbook xcelWorkbook, Sheet sheet, int rowPos, StatementLine statementLine) {
        CellStyle style = xcelWorkbook.createCellStyle();
        style.setWrapText(true);

        Row row = sheet.createRow(rowPos);
        Cell cell = row.createCell(0);
        cell.setCellValue(statementLine.getDate());
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue(statementLine.getDetails());
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue(statementLine.getTransactionName());
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue(statementLine.getAmount());
        cell.setCellStyle(style);
    }

    private List<StatementLine>  getStatementLines(String customerIds, String dateAfter, String dateBefore) {
        String[] splitCustomerIds = customerIds.split(",");

        SimpleDateFormat scheduleDateFormat = new SimpleDateFormat(
                "dd/MM/yyyy");

        SimpleDateFormat zohoDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        List<StatementLine> statementLines = new ArrayList<>();
        try {


            for (String customerId : splitCustomerIds) {
                List<InvoiceFetch> invoiceFetches = getInvoices(customerId, dateAfter, dateBefore);
                BigDecimal balance = BigDecimal.ZERO;
                for (InvoiceFetch invoiceFetch : invoiceFetches) {

                    InvoiceDetail invoice = invoiceService.getInvoice(invoiceFetch.getInvoice_id());

                    Map customer_custom_field_hash = invoice.getCustomer_custom_field_hash();

                    // only add items in draft, once added to schedule mark as sent manually for now
                    if(invoice.getStatus().equals(InvoiceStateEnum.SENT.getZohoState())){

                        String storeCode = (String)customer_custom_field_hash.get("cf_store_code");

                        Date invoiceDate = zohoDateFormat.parse(invoice.getDate());
                        String sparInvoiceDate = scheduleDateFormat.format(invoiceDate);

                        balance = balance.add(invoice.getTotal());

                        StatementLine statementLine = new StatementLine();
                        statementLine.setBalance(balance.setScale(2, RoundingMode.HALF_UP).toString());
                        statementLine.setDate(sparInvoiceDate);
                        statementLine.setTransactionName(invoice.getCustomer_name() + "-" + storeCode);
                        statementLine.setDetails(invoice.getInvoice_number());
                        statementLine.setDebitAmount(invoice.getTotal().setScale(2, RoundingMode.HALF_UP).toString());
                        statementLines.add(statementLine);

                    }
                }
            }

        } catch (Throwable t) {
            LOG.error("Can't get spar statement lines:", t);
            t.printStackTrace();
        }
        return statementLines;
    }

    private Workbook createFileAndHeaders(String dateAfter, String dateBefore) {

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Statement");
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 6000);
        sheet.setColumnWidth(2, 6000);
        sheet.setColumnWidth(3, 6000);

        Row companyHeader = sheet.createRow(0);
        Row columnHeader = sheet.createRow(1);
        Row companyAddressRow = sheet.createRow(2);


        CellStyle headerStyleCompanyInfo = workbook.createCellStyle();
        headerStyleCompanyInfo.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyleCompanyInfo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyleCompanyInfo.setWrapText(true);

        CellStyle headerStyleColumns = workbook.createCellStyle();
        headerStyleColumns.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyleColumns.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont companyInfoFont = ((XSSFWorkbook) workbook).createFont();
        companyInfoFont.setFontName("Arial");
        companyInfoFont.setFontHeightInPoints((short) 18);
        companyInfoFont.setBold(true);
        headerStyleCompanyInfo.setFont(companyInfoFont);

        XSSFFont columnFont = ((XSSFWorkbook) workbook).createFont();
        columnFont.setFontName("Arial");
        columnFont.setFontHeightInPoints((short) 16);
        columnFont.setBold(true);
        headerStyleColumns.setFont(columnFont);

        // company info
        Cell companyInfoCell = companyHeader.createCell(0);
        companyInfoCell.setCellValue("Outcast Foods");
        companyInfoCell.setCellStyle(headerStyleCompanyInfo);

        companyInfoCell = companyHeader.createCell(1);
        companyInfoCell.setCellValue("Statement of Accounts");
        companyInfoCell.setCellStyle(headerStyleCompanyInfo);

        companyInfoCell = companyHeader.createCell(2);
        companyInfoCell.setCellValue(dateAfter + " to " +dateBefore);
        companyInfoCell.setCellStyle(headerStyleCompanyInfo);

        companyInfoCell = companyHeader.createCell(3);
        companyInfoCell.setCellValue("REDACTED_CUSTOMER");
        companyInfoCell.setCellStyle(headerStyleCompanyInfo);

        //add company address
        Cell cell = companyAddressRow.createCell(0);
        cell.setCellValue(OutcastMockDBConstants.OUTAST_FOODS_ADDRESS);
        cell.setCellStyle(workbook.createCellStyle());

        //column headings
        Cell headerCell = columnHeader.createCell(0);
        headerCell.setCellValue("Date");
        headerCell.setCellStyle(headerStyleColumns);

        headerCell = columnHeader.createCell(1);
        headerCell.setCellValue("Invoice No");
        headerCell.setCellStyle(headerStyleColumns);

        headerCell = columnHeader.createCell(2);
        headerCell.setCellValue("Store Detail");
        headerCell.setCellStyle(headerStyleColumns);

        headerCell = columnHeader.createCell(3);
        headerCell.setCellValue("amount");
        headerCell.setCellStyle(headerStyleColumns);

        return workbook;
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
