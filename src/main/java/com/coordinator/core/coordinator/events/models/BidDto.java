package com.coordinator.core.coordinator.events.models;

import java.util.UUID;

public class BidDto {
    private UUID bidId;
    private Integer bidStatusId;
    private Integer bidAmount;
    private String messageToUser;

    public UUID getBidId() {
        return bidId;
    }

    public void setBidId(UUID bidId) {
        this.bidId = bidId;
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

}
