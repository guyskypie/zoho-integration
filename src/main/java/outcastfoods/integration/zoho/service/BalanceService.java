package outcastfoods.integration.zoho.service;

import outcastfoods.integration.zoho.exception.DataException;
import outcastfoods.integration.zoho.model.externalapi.CreditNote;
import outcastfoods.integration.zoho.model.externalapi.Customer;
import outcastfoods.integration.zoho.model.externalapi.InvoiceFetch;
import outcastfoods.integration.zoho.model.externalapi.Payment;
import outcastfoods.integration.zoho.model.internal.Transaction;
import outcastfoods.integration.zoho.model.internal.TransactionName;
import outcastfoods.integration.zoho.model.internal.TransactionType;
import outcastfoods.integration.zoho.utils.DateUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@ApplicationScoped
public class BalanceService {

    @Inject
    LookupService lookupService;

    @Inject
    InvoiceService invoiceService;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    /**
     * Get all transactions [Invoice, Payment, Credit] between the dates supplied
     * @param customer
     * @param dateAfterStr
     * @param dateBeforeStr
     * @return
     * @throws ParseException
     */
    public List<Transaction> getAllTransactions(Customer customer, String dateAfterStr, String dateBeforeStr) throws DataException, ParseException {
        List<Transaction> invoiceTransactions = new ArrayList<>();
        List<Transaction> paymentTransactions = new ArrayList<>();
        List<Transaction> creditTransactions = new ArrayList<>();

        String dateBeforeInclusive = DateUtils.getDateOneDayAfter(dateBeforeStr);
        String dateAfterInclusive = DateUtils.getDateOneDayBefore(dateAfterStr);

        paymentTransactions.addAll(getPaymentTransactions(dateAfterInclusive, dateBeforeInclusive,  customer));
        creditTransactions.addAll(getCreditTransactions(dateAfterInclusive, dateBeforeInclusive, customer));
        invoiceTransactions.addAll(getInvoiceTransactions(dateAfterInclusive, dateBeforeInclusive, customer));

        List<Transaction> allTransactions = new ArrayList<>();
        allTransactions.addAll(invoiceTransactions);
        allTransactions.addAll(paymentTransactions);
        allTransactions.addAll(creditTransactions);

        return allTransactions;
    }

    public BigDecimal getTotalInvoicedAmountForPeriod(Customer customer,  String dateAfterStr, String dateBefore) throws ParseException, DataException {

        BigDecimal invoicedAmount = BigDecimal.ZERO;

        List<Transaction> invoiceTransactions = getInvoiceTransactions(dateAfterStr, dateBefore, customer);
        for (Transaction invoiceTransaction : invoiceTransactions) {
            invoicedAmount = invoicedAmount.add(invoiceTransaction.getAmount());
        }

        return invoicedAmount;
    }



    public  List<Transaction>  getInvoiceTransactions(String dateAfter, String dateBefore,  Customer customer) throws DataException {

        List<Transaction> invoiceTransactions = new ArrayList<>();
        List<InvoiceFetch> invoiceFetches = invoiceService.getInvoices(customer.getContact_id(), dateAfter, dateBefore);
        for (InvoiceFetch invoiceFetch : invoiceFetches) {

            if(invoiceFetch.getStatus().equals("partially_paid")
                    || !invoiceFetch.getStatus().equals("paid")
                    || !invoiceFetch.getStatus().equals("unpaid")
                    || !invoiceFetch.getStatus().equals("overdue")){
                Date invoiceDate = null;
                try {
                    invoiceDate = dateFormat.parse(invoiceFetch.getDate());
                } catch (ParseException e) {
                    throw new DataException("Could not parse date", e);
                }

                // Invoice transaction
                Transaction invoiceTransaction = new Transaction();
                invoiceTransaction.setAmount(invoiceFetch.getTotal());
                invoiceTransaction.setDate(invoiceDate);
                invoiceTransaction.setTransactionType(TransactionType.DR);
                invoiceTransaction.setTransactionName(TransactionName.Invoice);
                invoiceTransaction.setDetails(customer.getContact_name() + " " + invoiceFetch.getInvoice_number());
                invoiceTransaction.setId(invoiceFetch.getInvoice_number());
                invoiceTransaction.setRef(invoiceFetch.getReference_number());
                invoiceTransactions.add(invoiceTransaction);

            }

        }

        return invoiceTransactions;
    }

    public  List<Transaction>  getCreditTransactions(String dateAfter, String dateBefore, Customer customer) throws DataException {

        List<Transaction> creditTransactions = new ArrayList<>();
        List<CreditNote> creditNotes = invoiceService.getCreditNotes(customer.getContact_id(), dateAfter, dateBefore);
        for (CreditNote creditNote : creditNotes) {

            if(creditNote.getStatus().equals("open") || creditNote.getStatus().equals("closed")){

                Date creditDate = null;
                try {
                    creditDate = dateFormat.parse(creditNote.getDate());
                } catch (ParseException e) {
                    throw new DataException("Could not parse date", e);
                }
                Transaction creditTransaction = new Transaction();
                creditTransaction.setAmount(creditNote.getTotal());
                creditTransaction.setDate(creditDate);
                creditTransaction.setTransactionType(TransactionType.CR);
                creditTransaction.setTransactionName(TransactionName.Credit);
                creditTransaction.setDetails(customer.company_name + " "
                        + TransactionName.Credit.toString() + " "
                        + creditNote.getCreditnote_number());
                creditTransactions.add(creditTransaction);
            }
        }

        return creditTransactions;
    }

    public  List<Transaction> getPaymentTransactions(String dateAfter, String dateBefore,  Customer customer) throws DataException {
        List<Transaction> paymentTransactions = new ArrayList<>();
        List<Payment> payments = invoiceService.getPayments(customer.getContact_name(), dateAfter, dateBefore);
        //List<Payment.Customerpayment> customerPayments = payments.getCustomerpayments();
        for (Payment customerPayment : payments) {

            Date paymentDate = null;
            try {
                paymentDate = dateFormat.parse(customerPayment.getDate());
            } catch (ParseException e) {
                throw new DataException("Could not parse date", e);
            }
            Transaction paymentTransaction = new Transaction();
            paymentTransaction.setAmount(customerPayment.getAmount());
            paymentTransaction.setDate(paymentDate);
            paymentTransaction.setTransactionType(TransactionType.CR);
            paymentTransaction.setTransactionName(TransactionName.Payment);
            paymentTransaction.setDetails(customer.getContact_name() + " " + TransactionName.Payment.toString());
            paymentTransactions.add(paymentTransaction);
        }

        return paymentTransactions;
    }

}
