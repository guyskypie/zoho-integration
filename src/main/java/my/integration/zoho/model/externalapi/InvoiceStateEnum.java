package my.integration.zoho.model.externalapi;

public enum InvoiceStateEnum {

    DRAFT("draft"),
    SENT("sent"),
    PAI("paid");

    private String zohoState;

    InvoiceStateEnum(String zohoState) {
        this.zohoState = zohoState;
    }

    public String getZohoState() {
        return zohoState;
    }

    public void setZohoState(String zohoState) {
        this.zohoState = zohoState;
    }
}
