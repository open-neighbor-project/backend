package openneighbor.backend.order.model;

public enum Status {
    CREATED,
    PENDING_ASSIGNMENT,
    ASSIGNED,
    IN_PROGRESS,
    DELIVERED,
    PAYMENT_RECEIVED,
    CLOSED
}