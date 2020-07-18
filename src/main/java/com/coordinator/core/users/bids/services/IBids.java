package com.coordinator.core.users.bids.services;

import com.coordinator.core.general.main.models.QueryOptions;
import com.coordinator.core.users.bids.models.BidDto;

import java.util.List;
import java.util.UUID;

public interface IBids {
    List<BidDto> getAllBidsByEvent(
            UUID eventId,
            QueryOptions queryOptions
    );
}
