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
    public Response notify(OrderModel order) {
        String title;
        String message;

        switch (order.status) {
            case CREATED:
                title = "Order created";
                message = "Your order has been created and is pending assignment.";
                break;

            case ASSIGNED:
                title = "Volunteer assigned";
                message = "Your order has been assigned to a volunteer: " + order.volunteerId;
                break;

            case IN_PROGRESS:
                title = "Order in progress";
                message = "Your volunteer is purchasing your order";
                break;

            case DELIVERED:
                title = "Order delivered to your doorstep";
                message = "Your order has been delivered outside your door by your volunteer: " + order.customerId;
                break;

            default:
                return Response.status(500).entity("Cannot notify").build();
        }

        NotificationModel notification = new NotificationModel(order.customerId, title, message);
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
