package outcastfoods.integration.zoho.service;


import org.jboss.logging.Logger;
import outcastfoods.integration.zoho.model.externalapi.Customer;
import outcastfoods.integration.zoho.model.internal.Statement;
import outcastfoods.integration.zoho.model.internal.StatementLine;
import outcastfoods.integration.zoho.model.internal.Transaction;
import outcastfoods.integration.zoho.model.internal.TransactionName;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@ApplicationScoped
public class StatementService {

    private static final Logger LOG = Logger.getLogger(StatementService.class);


    @Inject
    LookupService lookupService;

    @Inject
    BalanceService balanceService;

    @Inject
    PDFGenerator pdfGenerator;


    public Statement createStatement(String parentCustomerName, List<String> childCustomerIds,
                                     String dateAfterStr, String dateBeforeStr
    ){

        Statement statement = new Statement();

        try{
            SimpleDateFormat format = new SimpleDateFormat(
                    "yyyy-MM-dd", Locale.US);

            //total current balance with all payments and credits for a customer with child accounts
            BigDecimal currentBalanceAmountAtEndDate = BigDecimal.ZERO;

            List<Transaction> allTransactions = new ArrayList<>();

            // get overall current balance and total credits and invoices
            for (String childCustomerId : childCustomerIds) {

                Customer customer = lookupService.getCustomer(childCustomerId);

                currentBalanceAmountAtEndDate = currentBalanceAmountAtEndDate.add(balanceService.getBalanceAtDate(customer, dateBeforeStr));

                allTransactions.addAll(balanceService.getAllTransactions(customer, dateAfterStr, dateBeforeStr));

            }

            allTransactions.sort(Comparator.comparing(Transaction::getDate).reversed());

            List<StatementLine> statementLines = new ArrayList<>();

            BigDecimal invoiceAmountsOnSameDay = BigDecimal.ZERO;

            //get opening balance, [minus] all invoice amounts from currenBalance and [add]  PAYMENTS and CREDITS
            BigDecimal lineBalance = currentBalanceAmountAtEndDate;
            for (Transaction transaction : allTransactions) {

               /*
               No need for this
               if(format.format(transaction.getDate()).equals(dateAfterStr)){
                    invoiceAmountsOnSameDay = invoiceAmountsOnSameDay.add(transaction.getAmount());
                }*/

                //lineBalance = lineBalance + transaction.getAmount();
                StatementLine statementLine = new StatementLine();

                statementLine.setBalance("" + lineBalance);
                statementLine.setDate(format.format(transaction.getDate()));
                // work backwards so reverse DR and CR
                if(TransactionName.Invoice.equals(transaction.getTransactionName())){
                    statementLine.setDetails(transaction.getDetails());
                    statementLine.setDebitAmount("" + transaction.getAmount());
                    lineBalance = lineBalance.subtract(transaction.getAmount());
                } else if(TransactionName.Payment.equals(transaction.getTransactionName())){
                    statementLine.setDetails(transaction.getDetails());
                    statementLine.setCreditAmount("" + transaction.getAmount());
                    lineBalance = lineBalance.add(transaction.getAmount());
                } else if(TransactionName.Credit.equals(transaction.getTransactionName())){
                    statementLine.setDetails(transaction.getDetails());
                    statementLine.setCreditAmount("" + transaction.getAmount());
                    lineBalance = lineBalance.add(transaction.getAmount());
                }

                statementLine.setTransactionName(transaction.getTransactionName().toString());
                statementLines.add(statementLine);
            }

            // the final line balance worked out backwards is the opening balance
            BigDecimal openingBalance = lineBalance;

            statement.setContactName(parentCustomerName);
            statement.setOpeningbalance(openingBalance);
            statement.setBalanceDue(currentBalanceAmountAtEndDate);
            statement.setStatementLines(statementLines);
            statement.setFromDate(dateAfterStr);
            statement.setToDate(dateBeforeStr);

            pdfGenerator.toPDF(statement);

        } catch (Throwable t){
            LOG.error("Can't create invoice:", t);
            t.printStackTrace();
        }



        return statement;
    }




   /*

   @Inject
    OidcClients clients;

    @Inject
    OidcClient client;

    volatile Tokens currentTokens;

   @PostConstruct
    public void init() {



        List<String> scope = new ArrayList<>();
        scope.add("ZohoInvoice.invoices.READ");
        OidcClientConfig cfg = new OidcClientConfig();
        cfg.setId("guyclient");
        cfg.setAuthServerUrl("https://accounts.zoho.com/oauth/v2/auth");
        cfg.setClientId("REDACTED_ZOHO_CLIENT_ID");
        cfg.getCredentials().setSecret("REDACTED_ZOHO_CLIENT_SECRET");
        cfg.setTokenPath("https://accounts.zoho.com/oauth/v2/token");
        cfg.setDiscoveryEnabled(false);
        cfg.setScopes(scope);
        Uni<OidcClient> oidcCLient = clients.newClient(cfg);

        OidcClient guyclient = clients.getClient("guyclient");

        currentTokens = guyclient.getTokens().await().indefinitely();
        currentTokens = client.getTokens().await().indefinitely();
    }


    private void refreshTokens(){
        Tokens tokens = currentTokens;
        if (tokens.isAccessTokenExpired()) {
            // Add @Blocking method annotation if this code is used with Reactive RestClient
            tokens = client.refreshTokens(tokens.getRefreshToken()).await().indefinitely();
            currentTokens = tokens;
        }
    }*/
}
