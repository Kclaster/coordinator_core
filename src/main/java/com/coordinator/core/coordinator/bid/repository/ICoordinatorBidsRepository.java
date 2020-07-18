package com.coordinator.core.coordinator.bid.repository;

import com.coordinator.core.coordinator.bid.models.BidDto;
import com.coordinator.core.coordinator.bid.models.ImmutableBidEntity;
import com.coordinator.core.general.main.models.QueryOptions;

import java.util.List;
import java.util.UUID;

public interface ICoordinatorBidsRepository {
    List<BidDto> getAllCoordinatorsBids(UUID coordinatorId, QueryOptions queryOptions);
    Boolean bidExists(UUID coordinatorId, UUID eventId);
    void createBid(UUID coordinatorId, ImmutableBidEntity bidEntity);

}
