package openneighbor.backend.notification.service;

import openneighbor.backend.notification.model.NotificationModel;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TextNotificationService implements INotificationService<Integer> {
    public Integer notify(NotificationModel notification) {
        return -1;
    }
}
