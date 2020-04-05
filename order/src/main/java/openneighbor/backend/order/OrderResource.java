package openneighbor.backend.order;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import openneighbor.backend.order.model.Order;
import openneighbor.backend.order.model.Status;

@ApplicationScoped
@Path("api")
public class OrderResource {
    
    @Inject
    private OrderManager manager;
    private AtomicInteger assignOrderId = new AtomicInteger();
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response createOrder(Order order) {
        String orderId = String.format("%04d", assignOrderId.incrementAndGet());
        order.setOrderId(orderId);
        order.setStatus(Status.CREATED);
        manager.addOrder(order);
//        manager.sendNotification(order);
        return Response
                .status(Response.Status.OK)
                .entity(order)
                .build();
        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response getOrdersList() {
        List<Order> ordersList = manager.getOrders();

        return Response
                .status(Response.Status.OK)
                .entity(ordersList)
                .build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{orderId}")
    public Response getOrder(@PathParam("orderId") String orderId) {
        Order order = manager.getOrder(orderId);

        if (order == null) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Order id does not exist.")
                    .build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(order)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("status/{orderId}")
    public Response getStatus(@PathParam("orderId") String orderId) {
        Order order = manager.getOrder(orderId);
        if (order == null) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Order id does not exist.")
                    .build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(order.getStatus())
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("status/{orderId}")
    public Response setStatus(@PathParam("orderId") String orderId, Status newStatus) {
        Order order = manager.getOrder(orderId);
        if (order == null) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Order id does not exist")
                    .build();
        }

        order.setStatus(newStatus);

        return Response
                .status(Response.Status.OK)
                .entity(order.getStatus())
                .build();
    }
}