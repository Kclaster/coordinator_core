package com.coordinator.core.users.bids.repository;

import com.coordinator.core.general.main.models.QueryOptions;
import com.coordinator.core.users.bids.models.BidDto;

import java.util.List;
import java.util.UUID;

public interface IEventBidsRepository {
    List<BidDto> getAllEventBids(UUID eventId, QueryOptions queryOptions);
}
