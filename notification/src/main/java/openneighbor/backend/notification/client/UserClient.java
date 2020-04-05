package openneighbor.backend.notification.client;

import openneighbor.backend.notification.model.PersonModel;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(configKey="systemClient", baseUri="http://user-service:9080/user")
@RegisterProvider(UnknownUriExceptionMapper.class)
@Path("api")
public interface UserClient extends AutoCloseable {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("info")
    public PersonModel getUser(int personId);
}