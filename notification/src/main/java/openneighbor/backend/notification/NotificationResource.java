package openneighbor.backend.notification;

import com.google.api.services.gmail.model.Message;
import openneighbor.backend.notification.model.NotificationModel;
import openneighbor.backend.notification.model.OrderModel;
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
    public Response notifyOrderAssigned(OrderModel order) {
        NotificationModel notification = new NotificationModel(order.customerId.toString(),
                "Volunteer assigned",
                "Your order has been assigned to a volunteer: " + order.volunteerId.toString());
        return _notify(notification);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("orderPurchased")
    public Response notifyOrderPurchased(OrderModel order) {
        NotificationModel notification = new NotificationModel(order.customerId.toString(),
                "Order has been purchased",
                "Your order has been purchased by your volunteer: " + order.volunteerId.toString());
        return _notify(notification);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("orderDelivered")
    public Response notifyOrderDelivered(OrderModel order) {
        NotificationModel notification = new NotificationModel(order.customerId.toString(),
                "Order delivered to your doorstep",
                "Your order has been delivered outside your door by your volunteer: " + order.volunteerId.toString());
        return _notify(notification);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("orderReceived")
    public Response notifyOrderReceived(OrderModel order) {
        NotificationModel notification = new NotificationModel(order.volunteerId.toString(),
                "Order received by user",
                "Your order has been delivered outside your door by your volunteer: " + order.customerId.toString());
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
