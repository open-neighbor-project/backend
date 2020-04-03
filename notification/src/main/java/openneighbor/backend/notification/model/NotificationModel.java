package openneighbor.backend.notification.model;

public class NotificationModel {
    private String sendTo;
    private String title;
    private String message;

    public String getRecipient() {
        return sendTo;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }
}
