package outcastfoods.integration.zoho;


import org.acme.rest.client.fruit.FruityViceService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import outcastfoods.integration.zoho.service.InvoiceService;
import outcastfoods.integration.zoho.service.PnpCsvEdiInvoiceService;
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
    PnpCsvEdiInvoiceService pnpCsvEdiInvoiceService;

    @Inject
    InvoiceService invoiceService;


    @GET
    @Path("/generatezoho/picknpay")
    @Produces(MediaType.APPLICATION_JSON)
    public Object createPicknPayZohoInvoice() throws IOException, InterruptedException {

        try {
            invoiceService.createInvoicesAndOrderFiles();
        } catch (Throwable t){
            LOG.error("Why:", t);
            t.printStackTrace();
        }

        return null;
    }


    @GET
    @Path("/generatecsvportal/picknpay")
    @Produces(MediaType.APPLICATION_JSON)
    public Object createPicknPayCSVPortalInvoice(@QueryParam("customerId") String customerId,
                                                 @QueryParam("dateFrom") String dateFromIn,
                                                 @QueryParam("dateTo") String dateToIn) throws IOException, InterruptedException {

        try {
            pnpCsvEdiInvoiceService.createCsvInvoiceFile(customerId,dateFromIn,dateToIn);
        } catch (Throwable t){
            LOG.error("Why:", t);
            t.printStackTrace();
        }

        return null;
    }


}
