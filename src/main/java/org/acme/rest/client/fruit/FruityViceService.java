package org.acme.rest.client.fruit;

import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@RegisterProvider(RestServiceExceptionMapper.class)
@Path("/api/fruit")
@RegisterRestClient
public interface FruityViceService {

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public FruityVice getFruitByName(@PathParam("name") String name);


   /* @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public FruityVice getFruitByNames(    @CookieParam("JSESSIONID") String sessionid);
*/



}
