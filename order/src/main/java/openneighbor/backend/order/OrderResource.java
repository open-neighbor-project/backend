package openneighbor.backend.order;

import java.util.List;

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

@ApplicationScoped
@Path("/orders")
public class OrderResource {
    
    @Inject
    private OrderManager manager;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response createOrder(@PathParam("Order") Order order) {
        manager.addOrder(order);
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

}

