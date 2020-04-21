package openneighbor.backend.order.model;

import java.util.ArrayList;

public class Order {

    private String orderId;
    private String assignedVolunteerId;
    private String customerDetailsId;
    private ArrayList<String> itemList;
    private Status status;

    private String title;
    private String additionalInfo;
    private String budget;
    private String preferredStore;

    public Order(){
        itemList = new ArrayList<String>();
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }
    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getBudget() {
        return budget;
    }
    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getPreferredStore() {
        return preferredStore;
    }
    public void setPreferredStore(String preferredStore) {
        this.preferredStore = preferredStore;
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

    public ArrayList<String> getItemList() {
        return itemList;
    }
    public void setItemList(ArrayList<String> itemList) {
        this.itemList = itemList;
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
