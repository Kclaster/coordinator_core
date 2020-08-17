package com.coordinate.model.coordinator;

import com.coordinate.model.event.EventDto;

import java.util.UUID;

public class BidDto {
    private EventDto event;
    private UUID id;
    private Integer bidStatusId;
    private Integer bidAmount;
    private String messageToUser;
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

    public Integer getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(Integer bidAmount) {
        this.bidAmount = bidAmount;
    }

    public String getMessageToUser() {
        return messageToUser;
    }

    public void setMessageToUser(String messageToUser) {
        this.messageToUser = messageToUser;
    }

    public UUID getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(UUID coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    public EventDto getEvent() {
        return event;
    }

    public void setEvent(EventDto event) {
        this.event = event;
    }
}
