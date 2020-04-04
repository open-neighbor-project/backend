package openneighbor.backend.notification.model;

public class NotificationModel {
    public String sendTo;
    public String title;
    public String message;

    public NotificationModel() {}

    public NotificationModel(String sendTo, String title, String message) {
        this.sendTo = sendTo;
        this.title = title;
        this.message = message;
    }
}
