package com.coordinator.core.models;

import java.util.UUID;

public class BidDto {
    private UUID id;
    private Integer bidStatusId;
    private String bidAmount;
    private String messageToUser;
    private UUID eventId;
    private UUID coordinatorId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getBidStatusId() {
        return bidStatusId;
    }

    public void setBidStatusId(Integer bidStatusId) {
        this.bidStatusId = bidStatusId;
    }

    public String getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(String bidAmount) {
        this.bidAmount = bidAmount;
    }

    public String getMessageToUser() {
        return messageToUser;
    }

    public void setMessageToUser(String messageToUser) {
        this.messageToUser = messageToUser;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public UUID getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(UUID coordinatorId) {
        this.coordinatorId = coordinatorId;
    }
}
