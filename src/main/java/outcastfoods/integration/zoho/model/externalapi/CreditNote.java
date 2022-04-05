package outcastfoods.integration.zoho.model.externalapi;

import java.math.BigDecimal;
import java.util.Date;

public class CreditNote {

    public String creditnote_id;
    public String creditnote_number;
    public String status;
    public String reference_number;
    public String date;
    public BigDecimal total;
    public BigDecimal balance;
    public String customer_id;
    public String customer_name;
    public String currency_id;
    public String currency_code;
    public Date created_time;
    public Date last_modified_time;
    public boolean is_emailed;

    public String getCreditnote_id() {
        return creditnote_id;
    }

    public void setCreditnote_id(String creditnote_id) {
        this.creditnote_id = creditnote_id;
    }

    public String getCreditnote_number() {
        return creditnote_number;
    }

    public void setCreditnote_number(String creditnote_number) {
        this.creditnote_number = creditnote_number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReference_number() {
        return reference_number;
    }

    public void setReference_number(String reference_number) {
        this.reference_number = reference_number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    public Date getLast_modified_time() {
        return last_modified_time;
    }

    public void setLast_modified_time(Date last_modified_time) {
        this.last_modified_time = last_modified_time;
    }

    public boolean isIs_emailed() {
        return is_emailed;
    }

    public void setIs_emailed(boolean is_emailed) {
        this.is_emailed = is_emailed;
    }
}
