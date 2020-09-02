package com.coordinator.api.users.bids.repository;

import com.coordinate.model.QueryOptions;
import com.coordinate.model.bids.BidDto;

import java.util.List;
import java.util.UUID;

public interface IEventBidsRepository {
    List<BidDto> getAllEventBids(UUID eventId, QueryOptions queryOptions);
}
