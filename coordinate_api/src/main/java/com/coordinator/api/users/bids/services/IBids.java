package com.coordinator.api.users.bids.services;

import com.coordinate.model.QueryOptions;
import com.coordinate.model.bids.BidDto;

import java.util.List;
import java.util.UUID;

public interface IBids {
    List<BidDto> getAllBidsByEvent(
            UUID eventId,
            QueryOptions queryOptions
    );
}
