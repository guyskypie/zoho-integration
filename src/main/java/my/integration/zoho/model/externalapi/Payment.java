package my.integration.zoho.model.externalapi;


import java.math.BigDecimal;

public class Payment {


    public String payment_id;
    public String payment_number;
    public String invoice_number;
    public String date;
    public String payment_mode;
    public BigDecimal amount;
    public int bcy_amount;

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public String getPayment_number() {
        return payment_number;
    }

    public void setPayment_number(String payment_number) {
        this.payment_number = payment_number;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getBcy_amount() {
        return bcy_amount;
    }

    public void setBcy_amount(int bcy_amount) {
        this.bcy_amount = bcy_amount;
    }


}
