package com.coordinator.core.coordinator.bid.mappers;

import com.coordinator.core.coordinator.bid.models.BidPostRequest;
import com.coordinator.core.coordinator.bid.models.ImmutableBidEntity;

import java.util.UUID;

public class BidPostRequestToEntityMapper {
    public static ImmutableBidEntity mapBidRequestToEntity(BidPostRequest bidPostRequest) {

        return ImmutableBidEntity.builder()
                .id(UUID.randomUUID())
                .eventId(UUID.fromString(bidPostRequest.getEventId()))
                .bidAmount(bidPostRequest.getBidAmount())
                .messageToUser(bidPostRequest.getMessageToUser())
                .build();

    }
}
