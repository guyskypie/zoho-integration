package outcastfoods.integration.zoho.model.internal;

public class PnpCsvLine {

    private String invoiceNumber;
    private String date;
    private String dueDate;
    private String poNumber;
    private String invoiceTotal;
    private String barcode;
    private String vendorProductCode;
    private String articleDescription;
    private String quantity;
    private String taxPercentage;
    private String priceExVat;
    private String priceInclVat;

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(String invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getVendorProductCode() {
        return vendorProductCode;
    }

    public void setVendorProductCode(String vendorProductCode) {
        this.vendorProductCode = vendorProductCode;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(String taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public String getPriceExVat() {
        return priceExVat;
    }

    public void setPriceExVat(String priceExVat) {
        this.priceExVat = priceExVat;
    }

    public String getPriceInclVat() {
        return priceInclVat;
    }

    public void setPriceInclVat(String priceInclVat) {
        this.priceInclVat = priceInclVat;
    }
}
