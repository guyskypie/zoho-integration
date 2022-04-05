package outcastfoods.integration.zoho.model.externalapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Customer {


    public String contact_id;
    public String contact_name;
    public String company_name;
    public String first_name;
    public String last_name;
    public String designation;
    public String department;
    public String website;
    public String language_code;
    public String language_code_formatted;
    public String contact_salutation;
    public String email;
    public String phone;
    public String mobile;
    public String portal_status;
    public boolean is_client_review_asked;
    public boolean has_transaction;
    public String contact_type;
    public String customer_sub_type;
    public String owner_id;
    public String owner_name;
    public String source;
    public List<Object> documents;
    public String twitter;
    public String facebook;
    public boolean is_crm_customer;
    public boolean is_linked_with_zohocrm;
    public String primary_contact_id;
    public String zcrm_account_id;
    public String zcrm_contact_id;
    public String crm_owner_id;
    public int payment_terms;
    public String payment_terms_label;
    public BigDecimal credit_limit_exceeded_amount;
    public String currency_id;
    public String currency_code;
    public String currency_symbol;
    public int price_precision;
    public BigDecimal outstanding_receivable_amount;
    public BigDecimal outstanding_receivable_amount_bcy;
    public BigDecimal unused_credits_receivable_amount;
    public BigDecimal unused_credits_receivable_amount_bcy;
    public BigDecimal unused_retainer_payments;
    public String status;
    public boolean payment_reminder_enabled;
    public boolean is_sms_enabled;
    public boolean is_consent_agreed;
    public String consent_date;
    public boolean is_client_review_settings_enabled;

    public String cf_vat_number;
    public String cf_vat_number_unformatted;
    public CustomFieldHash custom_field_hash;
    public String tax_id;
    public String tax_name;
    public String tax_percentage;
    public String trader_name;
    public String legal_name;
    public String contact_category;
    public String sales_channel;
    public boolean ach_supported;
    public int portal_receipt_count;
    public BillingAddress billing_address;
    public ShippingAddress shipping_address;
    public List<Object> addresses;
    public String pricebook_id;
    public String pricebook_name;
    public DefaultTemplates default_templates;
    public boolean associated_with_square;
    public List<Object> cards;
    public List<Object> checks;
    public List<Object> bank_accounts;
    public List<Object> vpa_list;
    public String notes;
    public Date created_time;
    public Date last_modified_time;
    public String zohopeople_client_id;

    public String getContact_id() {
        return contact_id;
    }

    public void setContact_id(String contact_id) {
        this.contact_id = contact_id;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLanguage_code() {
        return language_code;
    }

    public void setLanguage_code(String language_code) {
        this.language_code = language_code;
    }

    public String getLanguage_code_formatted() {
        return language_code_formatted;
    }

    public void setLanguage_code_formatted(String language_code_formatted) {
        this.language_code_formatted = language_code_formatted;
    }

    public String getContact_salutation() {
        return contact_salutation;
    }

    public void setContact_salutation(String contact_salutation) {
        this.contact_salutation = contact_salutation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPortal_status() {
        return portal_status;
    }

    public void setPortal_status(String portal_status) {
        this.portal_status = portal_status;
    }

    public boolean isIs_client_review_asked() {
        return is_client_review_asked;
    }

    public void setIs_client_review_asked(boolean is_client_review_asked) {
        this.is_client_review_asked = is_client_review_asked;
    }

    public boolean isHas_transaction() {
        return has_transaction;
    }

    public void setHas_transaction(boolean has_transaction) {
        this.has_transaction = has_transaction;
    }

    public String getContact_type() {
        return contact_type;
    }

    public void setContact_type(String contact_type) {
        this.contact_type = contact_type;
    }

    public String getCustomer_sub_type() {
        return customer_sub_type;
    }

    public void setCustomer_sub_type(String customer_sub_type) {
        this.customer_sub_type = customer_sub_type;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<Object> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Object> documents) {
        this.documents = documents;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public boolean isIs_crm_customer() {
        return is_crm_customer;
    }

    public void setIs_crm_customer(boolean is_crm_customer) {
        this.is_crm_customer = is_crm_customer;
    }

    public boolean isIs_linked_with_zohocrm() {
        return is_linked_with_zohocrm;
    }

    public void setIs_linked_with_zohocrm(boolean is_linked_with_zohocrm) {
        this.is_linked_with_zohocrm = is_linked_with_zohocrm;
    }

    public String getPrimary_contact_id() {
        return primary_contact_id;
    }

    public void setPrimary_contact_id(String primary_contact_id) {
        this.primary_contact_id = primary_contact_id;
    }

    public String getZcrm_account_id() {
        return zcrm_account_id;
    }

    public void setZcrm_account_id(String zcrm_account_id) {
        this.zcrm_account_id = zcrm_account_id;
    }

    public String getZcrm_contact_id() {
        return zcrm_contact_id;
    }

    public void setZcrm_contact_id(String zcrm_contact_id) {
        this.zcrm_contact_id = zcrm_contact_id;
    }

    public String getCrm_owner_id() {
        return crm_owner_id;
    }

    public void setCrm_owner_id(String crm_owner_id) {
        this.crm_owner_id = crm_owner_id;
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

    public BigDecimal getCredit_limit_exceeded_amount() {
        return credit_limit_exceeded_amount;
    }

    public void setCredit_limit_exceeded_amount(BigDecimal credit_limit_exceeded_amount) {
        this.credit_limit_exceeded_amount = credit_limit_exceeded_amount;
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

    public String getCurrency_symbol() {
        return currency_symbol;
    }

    public void setCurrency_symbol(String currency_symbol) {
        this.currency_symbol = currency_symbol;
    }

    public int getPrice_precision() {
        return price_precision;
    }

    public void setPrice_precision(int price_precision) {
        this.price_precision = price_precision;
    }

    public BigDecimal getOutstanding_receivable_amount() {
        return outstanding_receivable_amount;
    }

    public void setOutstanding_receivable_amount(BigDecimal outstanding_receivable_amount) {
        this.outstanding_receivable_amount = outstanding_receivable_amount;
    }

    public BigDecimal getOutstanding_receivable_amount_bcy() {
        return outstanding_receivable_amount_bcy;
    }

    public void setOutstanding_receivable_amount_bcy(BigDecimal outstanding_receivable_amount_bcy) {
        this.outstanding_receivable_amount_bcy = outstanding_receivable_amount_bcy;
    }

    public BigDecimal getUnused_credits_receivable_amount() {
        return unused_credits_receivable_amount;
    }

    public void setUnused_credits_receivable_amount(BigDecimal unused_credits_receivable_amount) {
        this.unused_credits_receivable_amount = unused_credits_receivable_amount;
    }

    public BigDecimal getUnused_credits_receivable_amount_bcy() {
        return unused_credits_receivable_amount_bcy;
    }

    public void setUnused_credits_receivable_amount_bcy(BigDecimal unused_credits_receivable_amount_bcy) {
        this.unused_credits_receivable_amount_bcy = unused_credits_receivable_amount_bcy;
    }

    public BigDecimal getUnused_retainer_payments() {
        return unused_retainer_payments;
    }

    public void setUnused_retainer_payments(BigDecimal unused_retainer_payments) {
        this.unused_retainer_payments = unused_retainer_payments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isPayment_reminder_enabled() {
        return payment_reminder_enabled;
    }

    public void setPayment_reminder_enabled(boolean payment_reminder_enabled) {
        this.payment_reminder_enabled = payment_reminder_enabled;
    }

    public boolean isIs_sms_enabled() {
        return is_sms_enabled;
    }

    public void setIs_sms_enabled(boolean is_sms_enabled) {
        this.is_sms_enabled = is_sms_enabled;
    }

    public boolean isIs_consent_agreed() {
        return is_consent_agreed;
    }

    public void setIs_consent_agreed(boolean is_consent_agreed) {
        this.is_consent_agreed = is_consent_agreed;
    }

    public String getConsent_date() {
        return consent_date;
    }

    public void setConsent_date(String consent_date) {
        this.consent_date = consent_date;
    }

    public boolean isIs_client_review_settings_enabled() {
        return is_client_review_settings_enabled;
    }

    public void setIs_client_review_settings_enabled(boolean is_client_review_settings_enabled) {
        this.is_client_review_settings_enabled = is_client_review_settings_enabled;
    }

    public String getCf_vat_number() {
        return cf_vat_number;
    }

    public void setCf_vat_number(String cf_vat_number) {
        this.cf_vat_number = cf_vat_number;
    }

    public String getCf_vat_number_unformatted() {
        return cf_vat_number_unformatted;
    }

    public void setCf_vat_number_unformatted(String cf_vat_number_unformatted) {
        this.cf_vat_number_unformatted = cf_vat_number_unformatted;
    }

    public CustomFieldHash getCustom_field_hash() {
        return custom_field_hash;
    }

    public void setCustom_field_hash(CustomFieldHash custom_field_hash) {
        this.custom_field_hash = custom_field_hash;
    }

    public String getTax_id() {
        return tax_id;
    }

    public void setTax_id(String tax_id) {
        this.tax_id = tax_id;
    }

    public String getTax_name() {
        return tax_name;
    }

    public void setTax_name(String tax_name) {
        this.tax_name = tax_name;
    }

    public String getTax_percentage() {
        return tax_percentage;
    }

    public void setTax_percentage(String tax_percentage) {
        this.tax_percentage = tax_percentage;
    }

    public String getTrader_name() {
        return trader_name;
    }

    public void setTrader_name(String trader_name) {
        this.trader_name = trader_name;
    }

    public String getLegal_name() {
        return legal_name;
    }

    public void setLegal_name(String legal_name) {
        this.legal_name = legal_name;
    }

    public String getContact_category() {
        return contact_category;
    }

    public void setContact_category(String contact_category) {
        this.contact_category = contact_category;
    }

    public String getSales_channel() {
        return sales_channel;
    }

    public void setSales_channel(String sales_channel) {
        this.sales_channel = sales_channel;
    }

    public boolean isAch_supported() {
        return ach_supported;
    }

    public void setAch_supported(boolean ach_supported) {
        this.ach_supported = ach_supported;
    }

    public int getPortal_receipt_count() {
        return portal_receipt_count;
    }

    public void setPortal_receipt_count(int portal_receipt_count) {
        this.portal_receipt_count = portal_receipt_count;
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

    public List<Object> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Object> addresses) {
        this.addresses = addresses;
    }

    public String getPricebook_id() {
        return pricebook_id;
    }

    public void setPricebook_id(String pricebook_id) {
        this.pricebook_id = pricebook_id;
    }

    public String getPricebook_name() {
        return pricebook_name;
    }

    public void setPricebook_name(String pricebook_name) {
        this.pricebook_name = pricebook_name;
    }

    public DefaultTemplates getDefault_templates() {
        return default_templates;
    }

    public void setDefault_templates(DefaultTemplates default_templates) {
        this.default_templates = default_templates;
    }

    public boolean isAssociated_with_square() {
        return associated_with_square;
    }

    public void setAssociated_with_square(boolean associated_with_square) {
        this.associated_with_square = associated_with_square;
    }

    public List<Object> getCards() {
        return cards;
    }

    public void setCards(List<Object> cards) {
        this.cards = cards;
    }

    public List<Object> getChecks() {
        return checks;
    }

    public void setChecks(List<Object> checks) {
        this.checks = checks;
    }

    public List<Object> getBank_accounts() {
        return bank_accounts;
    }

    public void setBank_accounts(List<Object> bank_accounts) {
        this.bank_accounts = bank_accounts;
    }

    public List<Object> getVpa_list() {
        return vpa_list;
    }

    public void setVpa_list(List<Object> vpa_list) {
        this.vpa_list = vpa_list;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public String getZohopeople_client_id() {
        return zohopeople_client_id;
    }

    public void setZohopeople_client_id(String zohopeople_client_id) {
        this.zohopeople_client_id = zohopeople_client_id;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class CustomField{

        public CustomField() {
        }

        public String customfield_id;
        public boolean show_in_store;
        public boolean show_in_portal;
        public boolean is_active;
        public int index;
        public String label;
        public boolean show_on_pdf;
        public boolean edit_on_portal;
        public boolean edit_on_store;
        public boolean show_in_all_pdf;
        public String value_formatted;
        public String search_entity;
        public String data_type;
        public String placeholder;
        public String value;
        public boolean is_dependent_field;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class CustomFieldHash{

        public CustomFieldHash() {
        }

        public String cf_vat_number;
        public String cf_vat_number_unformatted;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class BillingAddress{
        public BillingAddress() {
        }

        public String address_id;
        public String attention;
        public String address;
        public String street2;
        public String city;
        public String state_code;
        public String state;
        public String zip;
        public String country;
        public String country_code;
        public String phone;
        public String fax;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class ShippingAddress{
        public ShippingAddress() {
        }

        public String address_id;
        public String attention;
        public String address;
        public String street2;
        public String city;
        public String state_code;
        public String state;
        public String zip;
        public String country;
        public String country_code;
        public String phone;
        public String fax;
        public String latitude;
        public String longitude;


    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public class DefaultTemplates{

        public DefaultTemplates() {
        }

        public String invoice_template_id;
        public String invoice_template_name;
        public String bill_template_id;
        public String bill_template_name;
        public String estimate_template_id;
        public String estimate_template_name;
        public String creditnote_template_id;
        public String creditnote_template_name;
        public String paymentthankyou_template_id;
        public String paymentthankyou_template_name;
        public String invoice_email_template_id;
        public String invoice_email_template_name;
        public String estimate_email_template_id;
        public String estimate_email_template_name;
        public String creditnote_email_template_id;
        public String creditnote_email_template_name;
        public String paymentthankyou_email_template_id;
        public String paymentthankyou_email_template_name;
        public String payment_remittance_email_template_id;
        public String payment_remittance_email_template_name;

        public String getInvoice_template_id() {
            return invoice_template_id;
        }

        public void setInvoice_template_id(String invoice_template_id) {
            this.invoice_template_id = invoice_template_id;
        }

        public String getInvoice_template_name() {
            return invoice_template_name;
        }

        public void setInvoice_template_name(String invoice_template_name) {
            this.invoice_template_name = invoice_template_name;
        }

        public String getBill_template_id() {
            return bill_template_id;
        }

        public void setBill_template_id(String bill_template_id) {
            this.bill_template_id = bill_template_id;
        }

        public String getBill_template_name() {
            return bill_template_name;
        }

        public void setBill_template_name(String bill_template_name) {
            this.bill_template_name = bill_template_name;
        }

        public String getEstimate_template_id() {
            return estimate_template_id;
        }

        public void setEstimate_template_id(String estimate_template_id) {
            this.estimate_template_id = estimate_template_id;
        }

        public String getEstimate_template_name() {
            return estimate_template_name;
        }

        public void setEstimate_template_name(String estimate_template_name) {
            this.estimate_template_name = estimate_template_name;
        }

        public String getCreditnote_template_id() {
            return creditnote_template_id;
        }

        public void setCreditnote_template_id(String creditnote_template_id) {
            this.creditnote_template_id = creditnote_template_id;
        }

        public String getCreditnote_template_name() {
            return creditnote_template_name;
        }

        public void setCreditnote_template_name(String creditnote_template_name) {
            this.creditnote_template_name = creditnote_template_name;
        }

        public String getPaymentthankyou_template_id() {
            return paymentthankyou_template_id;
        }

        public void setPaymentthankyou_template_id(String paymentthankyou_template_id) {
            this.paymentthankyou_template_id = paymentthankyou_template_id;
        }

        public String getPaymentthankyou_template_name() {
            return paymentthankyou_template_name;
        }

        public void setPaymentthankyou_template_name(String paymentthankyou_template_name) {
            this.paymentthankyou_template_name = paymentthankyou_template_name;
        }

        public String getInvoice_email_template_id() {
            return invoice_email_template_id;
        }

        public void setInvoice_email_template_id(String invoice_email_template_id) {
            this.invoice_email_template_id = invoice_email_template_id;
        }

        public String getInvoice_email_template_name() {
            return invoice_email_template_name;
        }

        public void setInvoice_email_template_name(String invoice_email_template_name) {
            this.invoice_email_template_name = invoice_email_template_name;
        }

        public String getEstimate_email_template_id() {
            return estimate_email_template_id;
        }

        public void setEstimate_email_template_id(String estimate_email_template_id) {
            this.estimate_email_template_id = estimate_email_template_id;
        }

        public String getEstimate_email_template_name() {
            return estimate_email_template_name;
        }

        public void setEstimate_email_template_name(String estimate_email_template_name) {
            this.estimate_email_template_name = estimate_email_template_name;
        }

        public String getCreditnote_email_template_id() {
            return creditnote_email_template_id;
        }

        public void setCreditnote_email_template_id(String creditnote_email_template_id) {
            this.creditnote_email_template_id = creditnote_email_template_id;
        }

        public String getCreditnote_email_template_name() {
            return creditnote_email_template_name;
        }

        public void setCreditnote_email_template_name(String creditnote_email_template_name) {
            this.creditnote_email_template_name = creditnote_email_template_name;
        }

        public String getPaymentthankyou_email_template_id() {
            return paymentthankyou_email_template_id;
        }

        public void setPaymentthankyou_email_template_id(String paymentthankyou_email_template_id) {
            this.paymentthankyou_email_template_id = paymentthankyou_email_template_id;
        }

        public String getPaymentthankyou_email_template_name() {
            return paymentthankyou_email_template_name;
        }

        public void setPaymentthankyou_email_template_name(String paymentthankyou_email_template_name) {
            this.paymentthankyou_email_template_name = paymentthankyou_email_template_name;
        }

        public String getPayment_remittance_email_template_id() {
            return payment_remittance_email_template_id;
        }

        public void setPayment_remittance_email_template_id(String payment_remittance_email_template_id) {
            this.payment_remittance_email_template_id = payment_remittance_email_template_id;
        }

        public String getPayment_remittance_email_template_name() {
            return payment_remittance_email_template_name;
        }

        public void setPayment_remittance_email_template_name(String payment_remittance_email_template_name) {
            this.payment_remittance_email_template_name = payment_remittance_email_template_name;
        }
    }






}
