package com.coordinator.api.coordinator.bid.mappers;

import com.coordinate.model.bids.BidPostRequest;
import com.coordinate.model.bids.ImmutableBidEntity;

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
