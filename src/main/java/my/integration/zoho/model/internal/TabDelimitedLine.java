package my.integration.zoho.model.internal;

public class TabDelimitedLine {

    private String date;
    private PicknPayTransactionType transactionType;
    private String amount;
    private String reference1;// invoiceNumber or credit note
    private String reference2;//pick n pay PO number

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public PicknPayTransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(PicknPayTransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReference1() {
        return reference1;
    }

    public void setReference1(String reference1) {
        this.reference1 = reference1;
    }

    public String getReference2() {
        return reference2;
    }

    public void setReference2(String reference2) {
        this.reference2 = reference2;
    }
}
