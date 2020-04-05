package openneighbor.backend.order.model;

import java.util.ArrayList;

public class Order {

    private String orderId;
    private String assignedVolunteerId;
    private String customerDetailsId;
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

    public String getAssignedVolunteerId() {
        return assignedVolunteerId;
    }
    public String getCustomerDetailsId() {
        return customerDetailsId;
    }
    public void setAssignedVolunteerId(String assignedVolunteerId) {
        this.assignedVolunteerId = assignedVolunteerId;
    }
    public void setCustomerDetailsId(String customerDetailsId) {
        this.customerDetailsId = customerDetailsId;
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
