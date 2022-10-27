package outcastfoods.integration.zoho;


import org.acme.rest.client.fruit.FruityViceService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import outcastfoods.integration.zoho.exception.DataException;
import outcastfoods.integration.zoho.model.internal.CustomerDetails;
import outcastfoods.integration.zoho.model.internal.Statement;
import outcastfoods.integration.zoho.service.InvoiceService;
import outcastfoods.integration.zoho.service.PnpZohoInvoiceService;
import outcastfoods.integration.zoho.service.PnpCsvEdiInvoiceService;
import outcastfoods.integration.zoho.service.SparCsvEdiInvoiceService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Path("/invoice")
public class InvoiceResource {


    private static final Logger LOG = Logger.getLogger(InvoiceResource.class);


    @Inject
    PnpCsvEdiInvoiceService pnpCsvEdiInvoiceService;


    @Inject
    SparCsvEdiInvoiceService sparCsvEdiInvoiceService;

    @Inject
    PnpZohoInvoiceService pnpZohoInvoiceService;

    @Inject
    InvoiceService invoiceService;



    @GET
    @Path("/generatezoho/picknpay")
    @Produces(MediaType.APPLICATION_JSON)
    public Object createPicknPayZohoInvoice() throws IOException, InterruptedException {

        try {
            pnpZohoInvoiceService.createInvoicesAndOrderFiles();
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

    @GET
    @Path("/generateschedule/spar")
    @Produces(MediaType.APPLICATION_JSON)
    public Object createSparXCELSchedule(@QueryParam("customerIds") String customerIds,
                                                 @QueryParam("dateFrom") String dateFromIn,
                                                 @QueryParam("dateTo") String dateToIn) throws IOException, InterruptedException {

        String fileName = null;
        try {
            sparCsvEdiInvoiceService.createXcelInvoiceSchedule(customerId,dateFromIn,dateToIn);
        } catch (Throwable t){
            LOG.error("Why:", t);
            t.printStackTrace();
        }
        return fileName;
    }


    /**
     *
     * @param dateFromIn  yyyy-mm-dd
     * @param dateToIn yyyy-mm-dd
     * @param clientIds
     * @param parentCustomerName
     * @return
     */
    @GET
    @Path("/download")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getInvoices(@QueryParam("dateFrom") String dateFromIn, @QueryParam("dateTo") String dateToIn,
                               @QueryParam("customerIdsCsv") String clientIds,
                               @QueryParam("parentCustomerName") String parentCustomerName) throws IOException, DataException {

        String [] customerIdArr = clientIds.split(",");
        List<String> customerIds = Arrays.asList(customerIdArr);
        List<String> invoicesAndDownload = invoiceService.getInvoicesAndDownload(customerIds, parentCustomerName, dateFromIn, dateToIn);

        return invoicesAndDownload;
    }


}
