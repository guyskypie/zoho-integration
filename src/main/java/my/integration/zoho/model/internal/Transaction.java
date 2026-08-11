package my.integration.zoho.model.internal;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {

    private Date date;
    private TransactionType transactionType;
    private TransactionName transactionName;
    private String details;
    private String ref;
    private String id;
    private BigDecimal amount;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public TransactionName getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(TransactionName transactionName) {
        this.transactionName = transactionName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
