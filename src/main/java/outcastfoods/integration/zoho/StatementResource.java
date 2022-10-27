package outcastfoods.integration.zoho;


import org.jboss.logging.Logger;
import outcastfoods.integration.zoho.model.internal.CustomerDetails;
import outcastfoods.integration.zoho.model.internal.Statement;
import outcastfoods.integration.zoho.service.SparStatementEdiService;
import outcastfoods.integration.zoho.service.StatementService;
import outcastfoods.integration.zoho.service.TabDelimitedPnPStatementService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Path("/statement")
public class StatementResource {

    private static final Logger LOG = Logger.getLogger(StatementResource.class);


    @Inject
    StatementService statementService;

    @Inject
    TabDelimitedPnPStatementService tabDelimitedPnPStatementService;

    @Inject
    SparStatementEdiService sparStatementEdiService;


    /**
     *
     * @param dateFromIn  yyyy-mm-dd
     * @param dateToIn yyyy-mm-dd
     * @param clientIds
     * @param parentCustomerName
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object getStatement(@QueryParam("dateFrom") String dateFromIn, @QueryParam("dateTo") String dateToIn,
                                 @QueryParam("customerIdsCsv") String clientIds,
                                 @QueryParam("parentCustomerName") String parentCustomerName,
                                 @QueryParam("vatNumber") String vatNumber,
                                 @QueryParam("address1") String address1,
                                 @QueryParam("address2") String address2,
                                 @QueryParam("address3") String address3,
                                 @QueryParam("address4") String address4,
                                 @QueryParam("dateForBalanceDue") String dateForBalanceDue) {

        String [] customerIdArr = clientIds.split(",");
        List<String> customerIds = Arrays.asList(customerIdArr);

        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setName(parentCustomerName);
        customerDetails.setVatNumber(vatNumber);
        customerDetails.setAddress1(address1);
        customerDetails.setAddress2(address2);
        customerDetails.setAddress3(address3);
        customerDetails.setAddress4(address4);

        Statement statement = statementService.createStatement(customerDetails, customerIds, dateFromIn, dateToIn, dateForBalanceDue);

        return statement;
    }


    @GET
    @Path("picknpay")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getPicknPayStatement(@QueryParam("customerId") String customerId,
                                       @QueryParam("dateTo") String dateToIn) {



        return  tabDelimitedPnPStatementService.createStatement(customerId, dateToIn);
    }

    @GET
    @Path("/spar")
    @Produces(MediaType.APPLICATION_JSON)
    public Object createSparXCELSchedule(@QueryParam("customerIds") String customerIds,
                                         @QueryParam("dateFrom") String dateFromIn,
                                         @QueryParam("dateTo") String dateToIn) throws IOException, InterruptedException {

        String fileName = null;
        try {
            fileName = sparStatementEdiService.createXcelStatement(customerIds,dateFromIn,dateToIn);
        } catch (Throwable t){
            LOG.error("Why:", t);
            t.printStackTrace();
        }
        return fileName;
    }



}
