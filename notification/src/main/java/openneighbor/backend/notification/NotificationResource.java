package openneighbor.backend.notification;

import com.google.api.services.gmail.model.Message;
import openneighbor.backend.notification.model.NotificationModel;
import openneighbor.backend.notification.service.EmailNotificationService;
import openneighbor.backend.notification.service.INotificationService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("api")
public class NotificationResource {

    @Inject
    private EmailNotificationService notificationService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("notify")
    public Response notify(NotificationModel notification) {
        return _notify(notification);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("orderAssigned")
    public Response notifyOrderAssigned(String user, String volunteerDetails) {
        NotificationModel notification = new NotificationModel(user,
                "Volunteer assigned",
                "Your order has been assigned to a volunteer: " + volunteerDetails);
        return _notify(notification);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("orderPurchased")
    public Response notifyOrderPurchased(String user, String volunteerDetails) {
        NotificationModel notification = new NotificationModel(user,
                "Order has been purchased",
                "Your order has been purchased by your volunteer: " + volunteerDetails);
        return _notify(notification);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("orderDelivered")
    public Response notifyOrderDelivered(String user, String volunteerDetails) {
        NotificationModel notification = new NotificationModel(user,
                "Order delivered to your doorstep",
                "Your order has been delivered outside your door by your volunteer: " + volunteerDetails);
        return _notify(notification);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("orderReceived")
    public Response notifyOrderReceived(String user, String userDetails) {
        NotificationModel notification = new NotificationModel(user,
                "Order received by user",
                "Your order has been delivered outside your door by your volunteer: " + userDetails);
        return _notify(notification);
    }

    private Response _notify(NotificationModel notification) {
        try {
            Message sentMessage = notificationService.notify(notification);
            return Response
                    .status(200)
                    .entity(sentMessage)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(500)
                    .entity("Something went wrong :(")
                    .build();
        }
    }
}
