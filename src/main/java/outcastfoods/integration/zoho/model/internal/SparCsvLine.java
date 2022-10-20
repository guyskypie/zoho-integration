package outcastfoods.integration.zoho.model.internal;

public class SparCsvLine {

    private String storeCode;
    private String storeName;
    private String docNo;
    private String date;
    private String excl;
    private String vat;
    private String incl;
    private String referringInvoiceNo;
    private String referringClaimNo;

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExcl() {
        return excl;
    }

    public void setExcl(String excl) {
        this.excl = excl;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getIncl() {
        return incl;
    }

    public void setIncl(String incl) {
        this.incl = incl;
    }

    public String getReferringInvoiceNo() {
        return referringInvoiceNo;
    }

    public void setReferringInvoiceNo(String referringInvoiceNo) {
        this.referringInvoiceNo = referringInvoiceNo;
    }

    public String getReferringClaimNo() {
        return referringClaimNo;
    }

    public void setReferringClaimNo(String referringClaimNo) {
        this.referringClaimNo = referringClaimNo;
    }
}
