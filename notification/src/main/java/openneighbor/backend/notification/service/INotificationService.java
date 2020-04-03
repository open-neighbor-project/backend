package openneighbor.backend.notification.service;

import openneighbor.backend.notification.model.NotificationModel;

public interface INotificationService<T> {
    T notify(NotificationModel notification) throws Exception;
}