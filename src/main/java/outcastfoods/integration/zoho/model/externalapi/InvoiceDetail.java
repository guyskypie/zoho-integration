package outcastfoods.integration.zoho.model.externalapi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Objec returned when a single invoice is looked up
 */
public class InvoiceDetail {


    // import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */

    public InvoiceDetail() {

    }


    public Object custom_field_hash;
    public Object currency_symbol;
    public Object email;
    public Object customer_custom_field_hash;
    public Object customer_custom_fields;
    public Object offline_created_date_with_time;
    public long invoice_id;
    public boolean ach_payment_initiated;
    public String invoice_number;
    public boolean is_pre_gst;
    public String place_of_supply;
    public String gst_no;
    public String gst_treatment;
    public String date;
    public String status;
    public int payment_terms;
    public String payment_terms_label;
    public String due_date;
    public String payment_expected_date;
    public String last_payment_date;
    public String reference_number;
    public long customer_id;
    public String customer_name;
    public ArrayList<String> contact_persons;
    public long currency_id;
    public String currency_code;
    public int exchange_rate;
    public int discount;
    public boolean is_discount_before_tax;
    public String discount_type;
    public boolean is_inclusive_tax;
    public String recurring_invoice_id;
    public boolean is_viewed_by_client;
    public boolean has_attachment;
    public String client_viewed_time;
    public ArrayList<InvoiceLineItem> line_items;
    public int shipping_charge;
    public int adjustment;
    public String adjustment_description;
    public int sub_total;
    public BigDecimal tax_total;
    public BigDecimal total;
    public ArrayList<Tax> taxes;
    public boolean payment_reminder_enabled;
    public double payment_made;
    public double credits_applied;
    public int tax_amount_withheld;
    public double balance;
    public int write_off_amount;
    public boolean allow_partial_payments;
    public int price_precision;
    public PaymentOptions payment_options;
    public boolean is_emailed;
    public int reminders_sent;
    public String last_reminder_sent_date;
    public String notes;
    public String terms;
    public ArrayList<CustomField> custom_fields;
    public long template_id;
    public String template_name;
    public Date created_time;
    public Date last_modified_time;
    public String attachment_name;
    public boolean can_send_in_mail;
    public String salesperson_id;
    public String salesperson_name;
    public String invoice_url;

    public long getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(long invoice_id) {
        this.invoice_id = invoice_id;
    }

    public boolean isAch_payment_initiated() {
        return ach_payment_initiated;
    }

    public void setAch_payment_initiated(boolean ach_payment_initiated) {
        this.ach_payment_initiated = ach_payment_initiated;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    public boolean isIs_pre_gst() {
        return is_pre_gst;
    }

    public void setIs_pre_gst(boolean is_pre_gst) {
        this.is_pre_gst = is_pre_gst;
    }

    public String getPlace_of_supply() {
        return place_of_supply;
    }

    public void setPlace_of_supply(String place_of_supply) {
        this.place_of_supply = place_of_supply;
    }

    public String getGst_no() {
        return gst_no;
    }

    public void setGst_no(String gst_no) {
        this.gst_no = gst_no;
    }

    public String getGst_treatment() {
        return gst_treatment;
    }

    public void setGst_treatment(String gst_treatment) {
        this.gst_treatment = gst_treatment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPayment_terms() {
        return payment_terms;
    }

    public void setPayment_terms(int payment_terms) {
        this.payment_terms = payment_terms;
    }

    public String getPayment_terms_label() {
        return payment_terms_label;
    }

    public void setPayment_terms_label(String payment_terms_label) {
        this.payment_terms_label = payment_terms_label;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
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

    public String getReference_number() {
        return reference_number;
    }

    public void setReference_number(String reference_number) {
        this.reference_number = reference_number;
    }

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public ArrayList<String> getContact_persons() {
        return contact_persons;
    }

    public void setContact_persons(ArrayList<String> contact_persons) {
        this.contact_persons = contact_persons;
    }

    public long getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(long currency_id) {
        this.currency_id = currency_id;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public int getExchange_rate() {
        return exchange_rate;
    }

    public void setExchange_rate(int exchange_rate) {
        this.exchange_rate = exchange_rate;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean isIs_discount_before_tax() {
        return is_discount_before_tax;
    }

    public void setIs_discount_before_tax(boolean is_discount_before_tax) {
        this.is_discount_before_tax = is_discount_before_tax;
    }

    public String getDiscount_type() {
        return discount_type;
    }

    public void setDiscount_type(String discount_type) {
        this.discount_type = discount_type;
    }

    public boolean isIs_inclusive_tax() {
        return is_inclusive_tax;
    }

    public void setIs_inclusive_tax(boolean is_inclusive_tax) {
        this.is_inclusive_tax = is_inclusive_tax;
    }

    public String getRecurring_invoice_id() {
        return recurring_invoice_id;
    }

    public void setRecurring_invoice_id(String recurring_invoice_id) {
        this.recurring_invoice_id = recurring_invoice_id;
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

    public ArrayList<InvoiceLineItem> getLine_items() {
        return line_items;
    }

    public void setLine_items(ArrayList<InvoiceLineItem> line_items) {
        this.line_items = line_items;
    }

    public int getShipping_charge() {
        return shipping_charge;
    }

    public void setShipping_charge(int shipping_charge) {
        this.shipping_charge = shipping_charge;
    }

    public int getAdjustment() {
        return adjustment;
    }

    public void setAdjustment(int adjustment) {
        this.adjustment = adjustment;
    }

    public String getAdjustment_description() {
        return adjustment_description;
    }

    public void setAdjustment_description(String adjustment_description) {
        this.adjustment_description = adjustment_description;
    }

    public int getSub_total() {
        return sub_total;
    }

    public void setSub_total(int sub_total) {
        this.sub_total = sub_total;
    }

    public BigDecimal getTax_total() {
        return tax_total;
    }

    public void setTax_total(BigDecimal tax_total) {
        this.tax_total = tax_total;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public ArrayList<Tax> getTaxes() {
        return taxes;
    }

    public void setTaxes(ArrayList<Tax> taxes) {
        this.taxes = taxes;
    }

    public boolean isPayment_reminder_enabled() {
        return payment_reminder_enabled;
    }

    public void setPayment_reminder_enabled(boolean payment_reminder_enabled) {
        this.payment_reminder_enabled = payment_reminder_enabled;
    }

    public double getPayment_made() {
        return payment_made;
    }

    public void setPayment_made(double payment_made) {
        this.payment_made = payment_made;
    }

    public double getCredits_applied() {
        return credits_applied;
    }

    public void setCredits_applied(double credits_applied) {
        this.credits_applied = credits_applied;
    }

    public int getTax_amount_withheld() {
        return tax_amount_withheld;
    }

    public void setTax_amount_withheld(int tax_amount_withheld) {
        this.tax_amount_withheld = tax_amount_withheld;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getWrite_off_amount() {
        return write_off_amount;
    }

    public void setWrite_off_amount(int write_off_amount) {
        this.write_off_amount = write_off_amount;
    }

    public boolean isAllow_partial_payments() {
        return allow_partial_payments;
    }

    public void setAllow_partial_payments(boolean allow_partial_payments) {
        this.allow_partial_payments = allow_partial_payments;
    }

    public int getPrice_precision() {
        return price_precision;
    }

    public void setPrice_precision(int price_precision) {
        this.price_precision = price_precision;
    }

    public PaymentOptions getPayment_options() {
        return payment_options;
    }

    public void setPayment_options(PaymentOptions payment_options) {
        this.payment_options = payment_options;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public ArrayList<CustomField> getCustom_fields() {
        return custom_fields;
    }

    public void setCustom_fields(ArrayList<CustomField> custom_fields) {
        this.custom_fields = custom_fields;
    }

    public long getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(long template_id) {
        this.template_id = template_id;
    }

    public String getTemplate_name() {
        return template_name;
    }

    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
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

    public String getAttachment_name() {
        return attachment_name;
    }

    public void setAttachment_name(String attachment_name) {
        this.attachment_name = attachment_name;
    }

    public boolean isCan_send_in_mail() {
        return can_send_in_mail;
    }

    public void setCan_send_in_mail(boolean can_send_in_mail) {
        this.can_send_in_mail = can_send_in_mail;
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

    public String getInvoice_url() {
        return invoice_url;
    }

    public void setInvoice_url(String invoice_url) {
        this.invoice_url = invoice_url;
    }




}



