package outcastfoods.integration.zoho.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import outcastfoods.integration.zoho.zohoapiclient.ZohoApiClient;
import outcastfoods.integration.zoho.model.externalapi.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class LookupService {

    private static final Logger LOG = Logger.getLogger(LookupService.class);

    @Inject
    AuthService authService;

    @RestClient
    ZohoApiClient zohoApiClient;


    /** Not used now that otehr functions removed but could be useful
     *
     * @param customerId
     * @return
     */
    @Deprecated
    public Customer getCustomer(String customerId){

        Customer customer = null;

        try{
            HashMap response = (HashMap) zohoApiClient.getContact(authService.getAccessToken().getBearerToken(), customerId);
            Map contactMap = (Map)response.get("contact");

            ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            customer = mapper.convertValue(contactMap, Customer.class);

        } catch (Throwable t){
            LOG.error("Can't get customer:", t);

        }

        return customer;
    }


}
