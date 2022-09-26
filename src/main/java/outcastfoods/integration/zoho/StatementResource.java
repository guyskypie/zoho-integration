package outcastfoods.integration.zoho;


import org.acme.rest.client.fruit.FruityViceService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import outcastfoods.integration.zoho.model.internal.CustomerDetails;
import outcastfoods.integration.zoho.model.internal.Statement;
import outcastfoods.integration.zoho.service.StatementService;
import outcastfoods.integration.zoho.service.TabDelimitedPnPStatementService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@Path("/statement")
public class StatementResource {

    @RestClient
    FruityViceService fruityViceService;

    @Inject
    StatementService statementService;

    @Inject
    TabDelimitedPnPStatementService tabDelimitedPnPStatementService;


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


}
