package outcastfoods.integration.zoho;


import org.jboss.logging.Logger;
import outcastfoods.integration.zoho.service.TabDelimitedPnPStatementService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/statement")
public class StatementResource {

    private static final Logger LOG = Logger.getLogger(StatementResource.class);


    @Inject
    TabDelimitedPnPStatementService tabDelimitedPnPStatementService;


    @GET
    @Path("picknpay")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getPicknPayStatement(@QueryParam("customerId") String customerId,
                                       @QueryParam("dateTo") String dateToIn) {



        return  tabDelimitedPnPStatementService.createStatement(customerId, dateToIn);
    }



}
