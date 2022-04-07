package outcastfoods.integration.zoho;


import org.acme.rest.client.fruit.FruityViceService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import outcastfoods.integration.zoho.model.internal.Statement;
import outcastfoods.integration.zoho.service.StatementService;
import outcastfoods.integration.zoho.service.TabDelimitedPnPStatementService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
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
                                 @QueryParam("parentCustomerName") String parentCustomerName) {

        String [] customerIdArr = clientIds.split(",");
        List<String> customerIds = Arrays.asList(customerIdArr);

        LocalDate currentDate = LocalDate.now();

        Statement statement = statementService.createStatement(parentCustomerName, customerIds, dateFromIn, dateToIn);

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
