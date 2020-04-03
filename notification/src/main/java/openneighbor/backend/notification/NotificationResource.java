package openneighbor.backend.notification;

import openneighbor.backend.notification.model.NotificationModel;
import openneighbor.backend.notification.service.EmailNotificationService;
import openneighbor.backend.notification.service.INotificationService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class NotificationResource {

    @Inject
    public EmailNotificationService notificationService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response notify(NotificationModel notification) {
        try {
            notificationService.notify(notification);
            return Response.status(200).build();
        } catch (Exception e) {
            return Response.status(503).build();
        }
    }
}
