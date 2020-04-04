package openneighbor.backend.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import client.NotificationClient;
import openneighbor.backend.order.model.Order;

@ApplicationScoped
public class OrderManager {
    
    private List<Order> orders = Collections.synchronizedList(new ArrayList<>());
    
    @Inject
    @RestClient
    private NotificationClient notificationRestClient;
    
    public void addOrder( Order order ) {
        orders.add(order);
    }
    
    public List<Order> getOrders() {
        return new ArrayList<Order>(orders);
    }
    
    public void sendNotification( Order order ) {
        notificationRestClient.sendNotificaton(order);
    }

}
