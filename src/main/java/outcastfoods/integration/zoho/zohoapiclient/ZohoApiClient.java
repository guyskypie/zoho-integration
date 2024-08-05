package outcastfoods.integration.zoho.zohoapiclient;

import org.acme.rest.client.fruit.RestServiceExceptionMapper;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import outcastfoods.integration.zoho.model.externalapi.InvoiceMultipart;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@RegisterProvider(RestServiceExceptionMapper.class)
@Path("/api/v3")
@RegisterRestClient
public interface ZohoApiClient {

    //https://books.zoho.com/api/v3/invoices?customer_id=REDACTED_ID&date_after=2021-08-01&date_before=2021-08-31
    @GET
    @Path("/invoices")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getInvoices(@HeaderParam("Authorization") String bearerToken,
                                     @QueryParam("customer_id") String customerId,
                                     @QueryParam("date_after") String dateAfter,
                                     @QueryParam("date_before") String dateBefore);

    //https://books.zoho.com/api/v3/invoices?customer_id=REDACTED_ID&date_after=2021-08-01&date_before=2021-08-31
    @GET
    @Path("/invoices/{invoiceId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getInvoice(@HeaderParam("Authorization") String bearerToken,
                              @PathParam("invoiceId") String invoiceId);

    @GET
    @Path("/invoices")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getInvoicesByStatus(@HeaderParam("Authorization") String bearerToken,
                              @QueryParam("customer_id") String customerId,
                              @QueryParam("status") String status,
                              @QueryParam("date_before") String dateBefore);

    @GET
    @Path("/invoices/pdf")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object getInvoices(@HeaderParam("Authorization") String bearerToken,
                              @QueryParam("customer_id") String customerId,
                              @QueryParam("invoice_ids") String invoiceIds);


    //https://books.zoho.com/api/v3/invoices
    @POST
    @Path("/invoices")
    @Produces(MediaType.APPLICATION_FORM_URLENCODED)
    @Consumes(MediaType.APPLICATION_JSON)
    public Object createInvoice(@HeaderParam("Authorization") String bearerToken,
                                @MultipartForm InvoiceMultipart data);

    @GET
    @Path("/contacts/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getContact(@HeaderParam("Authorization") String bearerToken,
                             @PathParam("customerId") String customerId);


    @GET
    @Path("/creditnotes")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getCreditNotes(@HeaderParam("Authorization") String bearerToken,
                             @QueryParam("customer_id") String customerId,
                             @QueryParam("date_after") String dateAfter,
                             @QueryParam("date_before") String dateBefore,
                             @QueryParam("status") String status);

    @GET
    @Path("/customerpayments")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getPayments(@HeaderParam("Authorization") String bearerToken,
                                 @QueryParam("customer_name") String customerName,
                                 @QueryParam("date_after") String dateAfter,
                                 @QueryParam("date_before") String dateBefore);

    @GET
    @Path("/customerpayments")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getPaymentsNoDate(@HeaderParam("Authorization") String bearerToken,
                              @QueryParam("customer_name") String customerName);





}
