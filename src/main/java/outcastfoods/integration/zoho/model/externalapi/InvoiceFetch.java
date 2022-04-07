package outcastfoods.integration.zoho.model.externalapi;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Object returned when a list of invoices is looked up
 */
public class InvoiceFetch {


    public String invoice_id;
    public boolean ach_payment_initiated;
    public String zcrm_potential_id;
    public String zcrm_potential_name;
    public String customer_name;
    public String customer_id;
    public String company_name;
    public String status;
    public String color_code;
    public String current_sub_status_id;
    public String current_sub_status;
    public String invoice_number;
    public String reference_number;
    public String date;
    public String due_date;
    public String due_days;
    public String currency_id;
    public String schedule_time;
    public String email;
    public String currency_code;
    public String currency_symbol;
    public String template_type;
    public int no_of_copies;
    public boolean show_no_of_copies;
    public boolean is_viewed_by_client;
    public boolean has_attachment;
    public String client_viewed_time;
    public String invoice_url;
    public String project_name;
    public BillingAddress billing_address;
    public ShippingAddress shipping_address;
    public String country;
    public String phone;
    public String created_by;
    public Date updated_time;
    public String transaction_type;
    public BigDecimal total;
    public BigDecimal balance;
    public Date created_time;
    public Date last_modified_time;
    public boolean is_emailed;
    public int reminders_sent;
    public String last_reminder_sent_date;
    public String payment_expected_date;
    public String last_payment_date;
    public List<Object> custom_fields;
    public CustomFieldHash custom_field_hash;
    public String template_id;
    public String documents;
    public String salesperson_id;
    public String salesperson_name;
    public BigDecimal shipping_charge;
    public BigDecimal adjustment;
    public BigDecimal write_off_amount;
    public BigDecimal exchange_rate;

    public class BillingAddress{
        public String address;
        public String street2;
        public String city;
        public String state;
        public String zipcode;
        public String country;
        public String phone;
        public String fax;
        public String attention;
    }

    public class ShippingAddress{
        public String address;
        public String street2;
        public String city;
        public String state;
        public String zipcode;
        public String country;
        public String phone;
        public String fax;
        public String attention;
    }

    public class CustomFieldHash{
    }


    public String getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(String invoice_id) {
        this.invoice_id = invoice_id;
    }

    public boolean isAch_payment_initiated() {
        return ach_payment_initiated;
    }

    public void setAch_payment_initiated(boolean ach_payment_initiated) {
        this.ach_payment_initiated = ach_payment_initiated;
    }

    public String getZcrm_potential_id() {
        return zcrm_potential_id;
    }

    public void setZcrm_potential_id(String zcrm_potential_id) {
        this.zcrm_potential_id = zcrm_potential_id;
    }

    public String getZcrm_potential_name() {
        return zcrm_potential_name;
    }

    public void setZcrm_potential_name(String zcrm_potential_name) {
        this.zcrm_potential_name = zcrm_potential_name;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getColor_code() {
        return color_code;
    }

    public void setColor_code(String color_code) {
        this.color_code = color_code;
    }

    public String getCurrent_sub_status_id() {
        return current_sub_status_id;
    }

    public void setCurrent_sub_status_id(String current_sub_status_id) {
        this.current_sub_status_id = current_sub_status_id;
    }

    public String getCurrent_sub_status() {
        return current_sub_status;
    }

    public void setCurrent_sub_status(String current_sub_status) {
        this.current_sub_status = current_sub_status;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
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

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getDue_days() {
        return due_days;
    }

    public void setDue_days(String due_days) {
        this.due_days = due_days;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public String getSchedule_time() {
        return schedule_time;
    }

    public void setSchedule_time(String schedule_time) {
        this.schedule_time = schedule_time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getCurrency_symbol() {
        return currency_symbol;
    }

    public void setCurrency_symbol(String currency_symbol) {
        this.currency_symbol = currency_symbol;
    }

    public String getTemplate_type() {
        return template_type;
    }

    public void setTemplate_type(String template_type) {
        this.template_type = template_type;
    }

    public int getNo_of_copies() {
        return no_of_copies;
    }

    public void setNo_of_copies(int no_of_copies) {
        this.no_of_copies = no_of_copies;
    }

    public boolean isShow_no_of_copies() {
        return show_no_of_copies;
    }

    public void setShow_no_of_copies(boolean show_no_of_copies) {
        this.show_no_of_copies = show_no_of_copies;
    }

    public boolean isIs_viewed_by_client() {
        return is_viewed_by_client;
    }

    public void setIs_viewed_by_client(boolean is_viewed_by_client) {
        this.is_viewed_by_client = is_viewed_by_client;
    }

    public boolean isHas_attachment() {
        return has_attachment;
    }

    public void setHas_attachment(boolean has_attachment) {
        this.has_attachment = has_attachment;
    }

    public String getClient_viewed_time() {
        return client_viewed_time;
    }

    public void setClient_viewed_time(String client_viewed_time) {
        this.client_viewed_time = client_viewed_time;
    }

    public String getInvoice_url() {
        return invoice_url;
    }

    public void setInvoice_url(String invoice_url) {
        this.invoice_url = invoice_url;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public BillingAddress getBilling_address() {
        return billing_address;
    }

    public void setBilling_address(BillingAddress billing_address) {
        this.billing_address = billing_address;
    }

    public ShippingAddress getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(ShippingAddress shipping_address) {
        this.shipping_address = shipping_address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Date getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(Date updated_time) {
        this.updated_time = updated_time;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
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

    public int getReminders_sent() {
        return reminders_sent;
    }

    public void setReminders_sent(int reminders_sent) {
        this.reminders_sent = reminders_sent;
    }

    public String getLast_reminder_sent_date() {
        return last_reminder_sent_date;
    }

    public void setLast_reminder_sent_date(String last_reminder_sent_date) {
        this.last_reminder_sent_date = last_reminder_sent_date;
    }

    public String getPayment_expected_date() {
        return payment_expected_date;
    }

    public void setPayment_expected_date(String payment_expected_date) {
        this.payment_expected_date = payment_expected_date;
    }

    public String getLast_payment_date() {
        return last_payment_date;
    }

    public void setLast_payment_date(String last_payment_date) {
        this.last_payment_date = last_payment_date;
    }

    public List<Object> getCustom_fields() {
        return custom_fields;
    }

    public void setCustom_fields(List<Object> custom_fields) {
        this.custom_fields = custom_fields;
    }

    public CustomFieldHash getCustom_field_hash() {
        return custom_field_hash;
    }

    public void setCustom_field_hash(CustomFieldHash custom_field_hash) {
        this.custom_field_hash = custom_field_hash;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getDocuments() {
        return documents;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }

    public String getSalesperson_id() {
        return salesperson_id;
    }

    public void setSalesperson_id(String salesperson_id) {
        this.salesperson_id = salesperson_id;
    }

    public String getSalesperson_name() {
        return salesperson_name;
    }

    public void setSalesperson_name(String salesperson_name) {
        this.salesperson_name = salesperson_name;
    }

    public BigDecimal getShipping_charge() {
        return shipping_charge;
    }

    public void setShipping_charge(BigDecimal shipping_charge) {
        this.shipping_charge = shipping_charge;
    }

    public BigDecimal getAdjustment() {
        return adjustment;
    }

    public void setAdjustment(BigDecimal adjustment) {
        this.adjustment = adjustment;
    }

    public BigDecimal getWrite_off_amount() {
        return write_off_amount;
    }

    public void setWrite_off_amount(BigDecimal write_off_amount) {
        this.write_off_amount = write_off_amount;
    }

    public BigDecimal getExchange_rate() {
        return exchange_rate;
    }

    public void setExchange_rate(BigDecimal exchange_rate) {
        this.exchange_rate = exchange_rate;
    }
}
