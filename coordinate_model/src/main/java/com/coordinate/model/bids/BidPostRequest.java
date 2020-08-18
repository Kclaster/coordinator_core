package com.coordinate.model.bids;

import org.immutables.value.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value.Immutable
public class BidPostRequest {
    private UUID id;
    private String messageToUser;
    @NotNull
    @NotEmpty
    private String eventId;
    private String coordinatorId;
    @NotNull
    private Integer bidAmount;

    public BidPostRequest(
            UUID id,
            String messageToUser,
            @NotNull @NotEmpty String eventId,
            String coordinatorId,
            @NotNull Integer bidAmount
    ) {
        this.id = id;
        this.messageToUser = messageToUser;
        this.eventId = eventId;
        this.coordinatorId = coordinatorId;
        this.bidAmount = bidAmount;
    }

    public BidPostRequest() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMessageToUser() {
        return messageToUser;
    }

    public void setMessageToUser(String messageToUser) {
        this.messageToUser = messageToUser;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(String coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    public Integer getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(Integer bidAmount) {
        this.bidAmount = bidAmount;
    }
}
