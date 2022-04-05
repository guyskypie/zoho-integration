package outcastfoods.integration.zoho.model.externalapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class InvoiceCreate {

    public String customer_id;

    @JsonProperty("date")
    public String invoiceDate;

    public String reference_number;

    @JsonProperty("line_items")
    public List<LineItem> lineItems;

    public String notes;

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getReference_number() {
        return reference_number;
    }

    public void setReference_number(String reference_number) {
        this.reference_number = reference_number;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
