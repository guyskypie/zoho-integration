package outcastfoods.integration.zoho;


import org.acme.rest.client.fruit.FruityViceService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import outcastfoods.integration.zoho.service.InvoiceService;
import outcastfoods.integration.zoho.service.StatementService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/invoice")
public class InvoiceResource {


    private static final Logger LOG = Logger.getLogger(InvoiceResource.class);

    @RestClient
    FruityViceService fruityViceService;

    @Inject
    StatementService statementService;

    @Inject
    InvoiceService invoiceService;


    @GET
    @Path("/create/picknpay")
    @Produces(MediaType.APPLICATION_JSON)
    public Object createPicknPayInvoice() throws IOException, InterruptedException {

        try {
            invoiceService.createInvoicesAndOrderFiles();
        } catch (Throwable t){
            LOG.error("Why:", t);
            t.printStackTrace();
        }

        return null;
    }


}
