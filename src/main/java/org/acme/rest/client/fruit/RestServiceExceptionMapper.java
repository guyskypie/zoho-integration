package org.acme.rest.client.fruit;

import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import javax.ws.rs.core.Response;

public class RestServiceExceptionMapper  implements ResponseExceptionMapper<Exception> {

    @Override
    public Exception toThrowable(Response r) {

        return new Exception(r.getStatus() + " - " + r.readEntity(String.class));
    }
}
