package openneighbor.backend.order.model;

import java.util.ArrayList;

public class Order {

    private String orderId;
    private Volunteer assignedVolunteer;
    private Customer customerDetails;
    private ArrayList<Item> itemList;
    private Status status;

    public Order(){
        itemList = new ArrayList<Item>();
    }

    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Volunteer getAssignedVolunteer() {
        return assignedVolunteer;
    }
    public void setAssignedVolunteer(Volunteer assignedVolunteer) {
        this.assignedVolunteer = assignedVolunteer;
    }
    public Customer getCustomerDetails() {
        return customerDetails;
    }
    public void setCustomerDetails(Customer customerDetails) {
        this.customerDetails = customerDetails;
    }
    public ArrayList<Item> getItemList() {
        return itemList;
    }
    public void setItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

}