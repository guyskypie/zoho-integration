package outcastfoods.integration.zoho.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import outcastfoods.integration.zoho.exception.PDFGeneratorException;
import outcastfoods.integration.zoho.model.internal.Statement;
import outcastfoods.integration.zoho.model.internal.StatementLine;

import javax.enterprise.context.ApplicationScoped;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@ApplicationScoped
public class PDFGenerator {

    private static final Logger LOG = Logger.getLogger(StatementService.class);

    @ConfigProperty(name = "output.statement.dir")
    String statementOutPutDir;



    @ConfigProperty(name = "running.in.ide")
    boolean runningInIDE;


    static final String FILE_NAME_TEMPLATE = "Statement_Outcastfoods_[COMPANY_NAME]_[DATE].pdf";

    public Object toPDF(Statement statement) throws PDFGeneratorException {

        try{

            //SimpleDateFormat tabDocDateFormat = new SimpleDateFormat(
            //        "yyyyMMdd");
            String fileNameDate = statement.getFromDate() + "-" + statement.getToDate();
            String fileName = FILE_NAME_TEMPLATE.replace("[DATE]", fileNameDate)
                    .replace("[COMPANY_NAME]", statement.getContactName());
            String fullPathStatementFileName = statementOutPutDir + fileName;

            Document document = new Document();
            document.setMargins(20,20,20,20);
            PdfWriter.getInstance(document, new FileOutputStream(fullPathStatementFileName));
            document.open();


            PdfPTable headerOutcastDetails = new PdfPTable(2);

            Image img = null;
            if(runningInIDE){
                File imageFile=new File(getClass().getClassLoader().getResource("META-INF/resources/outcast_logo.jpg").getFile());
                Path path = Paths.get(imageFile.toURI());
                img = Image.getInstance(path.toAbsolutePath().toString());
            } else {
                InputStream logoStream = this.getClass().getClassLoader().getResourceAsStream("META-INF/resources/outcast_logo.jpg");
                img = Image.getInstance(logoStream.readAllBytes());
            }

            img.scaleToFit(150,150);

            PdfPCell imageCell = new PdfPCell(img);
            imageCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerOutcastDetails.addCell(imageCell);

            PdfPCell outcastDetailsCell = new PdfPCell(new Phrase("Outcast Foods\n" +
                    "Unit 4\n" +
                    "121 Cecil Road\n" +
                    "Salt River Cape Town 7925\n" +
                    "South Africa\n" +
                    "Mobile: 0794821647\n" +
                    "Email: accounts@outcastfoods.co.za\n" +
                    "Reg no: 2018/383565/07\n" +
                    "Vat no: 4480295916"));
            outcastDetailsCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            headerOutcastDetails.addCell(outcastDetailsCell);

            document.add(headerOutcastDetails);

            Font bold16 = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
            Font bold12 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

            PdfPTable headerStatementSummaryTable = new PdfPTable(1);

            PdfPCell summaryCell = new PdfPCell(new Phrase("Statement of Accounts", bold16));
            summaryCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            headerStatementSummaryTable.addCell(summaryCell);

            PdfPCell summaryCellDetailsDates =
                    new PdfPCell(new Phrase(statement.getFromDate() + " to " + statement.getToDate()));
            summaryCellDetailsDates.setHorizontalAlignment(Element.ALIGN_RIGHT);
            headerStatementSummaryTable.addCell(summaryCellDetailsDates);
            document.add(headerStatementSummaryTable);

            PdfPTable customerHeaderTable = new PdfPTable(2);

            Phrase companyPhrase = new Phrase();
            companyPhrase.add(new Chunk("REDACTED_CUSTOMER\n", bold12));
            companyPhrase.add(new Chunk("Vat Number: 4660300015\n"));
            companyPhrase.add(new Chunk("20 Section Street\n"));
            companyPhrase.add(new Chunk("Unit c3, first floor\n"));
            companyPhrase.add(new Chunk("Block2, Northgate Island\n"));
            companyPhrase.add(new Chunk("Cape Town\n"));
            PdfPCell companyDetailsCell = new PdfPCell();
            companyDetailsCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            companyDetailsCell.addElement(companyPhrase);
            customerHeaderTable.addCell(companyDetailsCell);

            Phrase summaryPhrase = new Phrase();
            summaryPhrase.add(new Chunk("Opening Balance:" + statement.getOpeningbalance() + "\n"));
            summaryPhrase.add(new Chunk("Balance Due:" + statement.getBalanceDue() + "\n"));

            PdfPCell summaryPhraseCell = new PdfPCell();
            summaryPhraseCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            summaryPhraseCell.addElement(summaryPhrase);
            customerHeaderTable.addCell(summaryPhraseCell);

            document.add(customerHeaderTable);

            float [] relativeWidth = {20,15,35,15,15};
            PdfPTable transactionsTable = new PdfPTable(relativeWidth);
            PdfPCell dateCellHeading  = new PdfPCell();
            PdfPCell typeCellHeading  = new PdfPCell();
            PdfPCell detailCellHeading  = new PdfPCell();
            PdfPCell amountCellHeading  = new PdfPCell();
            PdfPCell balanceCellHeading  = new PdfPCell();

            dateCellHeading.addElement(new Phrase("Date", bold12));
            dateCellHeading.setHorizontalAlignment(Element.ALIGN_LEFT);
            typeCellHeading.addElement(new Phrase("Transaction", bold12));
            typeCellHeading.setHorizontalAlignment(Element.ALIGN_LEFT);
            detailCellHeading.addElement(new Phrase("Details", bold12));
            detailCellHeading.setHorizontalAlignment(Element.ALIGN_LEFT);
            amountCellHeading.addElement(new Phrase("Amount", bold12));
            amountCellHeading.setHorizontalAlignment(Element.ALIGN_LEFT);
            balanceCellHeading.addElement(new Phrase("Balance", bold12));
            balanceCellHeading.setHorizontalAlignment(Element.ALIGN_LEFT);

            transactionsTable.addCell(dateCellHeading);
            transactionsTable.addCell(typeCellHeading);
            transactionsTable.addCell(detailCellHeading);
            transactionsTable.addCell(amountCellHeading);
            transactionsTable.addCell(balanceCellHeading);

            PdfPCell blankCell  = new PdfPCell();
            PdfPCell openBalanceTextCell  = new PdfPCell();
            PdfPCell openBalanceCell  = new PdfPCell();
            PdfPCell openBalDateCell  = new PdfPCell();

            openBalDateCell.addElement(new Phrase(statement.getFromDate()));
            openBalDateCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            openBalanceTextCell.addElement(new Phrase("*** Opening Balance ***"));
            openBalanceTextCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            openBalanceCell.addElement(new Phrase(statement.getOpeningbalance().toString()));
            openBalanceCell.setHorizontalAlignment(Element.ALIGN_LEFT);

            transactionsTable.addCell(openBalDateCell);
            transactionsTable.addCell(openBalanceTextCell);
            transactionsTable.addCell(blankCell);
            transactionsTable.addCell(blankCell);
            transactionsTable.addCell(openBalanceCell);

            List<StatementLine> statementLines = statement.getStatementLines();
            for (StatementLine statementLine : statementLines) {
                PdfPCell dateCell  = new PdfPCell();
                PdfPCell typeCell  = new PdfPCell();
                PdfPCell detailCell  = new PdfPCell();
                PdfPCell amountCell  = new PdfPCell();
                PdfPCell balanceCel  = new PdfPCell();

                dateCell.addElement(new Phrase(statementLine.getDate()));
                dateCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                typeCell.addElement(new Phrase(statementLine.getTransactionName()));
                typeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                detailCell.addElement(new Phrase(statementLine.getDetails()));
                detailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                amountCell.addElement(new Phrase(statementLine.getAmount()));
                amountCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                balanceCel.addElement(new Phrase(statementLine.getBalance()));
                balanceCel.setHorizontalAlignment(Element.ALIGN_LEFT);

                transactionsTable.addCell(dateCell);
                transactionsTable.addCell(typeCell);
                transactionsTable.addCell(detailCell);
                transactionsTable.addCell(amountCell);
                transactionsTable.addCell(balanceCel);
            }

            document.add(transactionsTable);

            PdfPTable finalBalanceDueTable = new PdfPTable(2);
            PdfPCell balanceDueCell = new PdfPCell();
            balanceDueCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            balanceDueCell.addElement(new Phrase("Balance Due           R" + statement.getBalanceDue()));
            finalBalanceDueTable.addCell(new PdfPCell());
            finalBalanceDueTable.addCell(balanceDueCell);

            document.add(finalBalanceDueTable);

            document.close();
        } catch (FileNotFoundException e) {
            LOG.error("cannot access file" ,e);
        } catch (DocumentException e) {
            LOG.error("cannot create pdf" ,e);
        } catch (IOException e) {
            LOG.error("cannot create pdf" ,e);
        }

        return true;
    }

}
