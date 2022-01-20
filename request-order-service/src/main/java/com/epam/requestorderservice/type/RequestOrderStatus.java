package com.epam.requestorderservice.type;

/**
 * Provides an ENUMs of the available order's statuses.
 */
public enum RequestOrderStatus {
    APPROVED_WAITING_FOR_PAYMENT("client.cabinet.approved"),
    WAITING_FOR_APPROVAL("client.cabinet.waiting"),
    REJECTED("client.cabinet.rejected"),
    PAID_AND_BOOKED("client.cabinet.paid"),
    REQUEST_PROCESSED("client.cabinet.processed");
    private String description;

    RequestOrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
