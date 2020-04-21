package client;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import openneighbor.backend.order.model.Order;

@Path("/notification/api")
@RegisterRestClient(configKey = "NotificationClient", baseUri = "http://localhost:9081")
public interface NotificationClient {

    @POST
    @Path("/notify")
    @Produces(MediaType.APPLICATION_JSON)
    Response sendNotificaton(Order order);

}
