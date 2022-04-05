package outcastfoods.integration.zoho.model.externalapi;


import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class InvoiceMultipart {

    @FormParam("JSONString")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public String JSONString;

    public String getJSONString() {
        return JSONString;
    }

    public void setJSONString(String JSONString) {
        this.JSONString = JSONString;
    }
}
