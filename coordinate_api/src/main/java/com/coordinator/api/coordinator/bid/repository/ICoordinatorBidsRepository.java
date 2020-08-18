package com.coordinator.api.coordinator.bid.repository;

import com.coordinate.model.QueryOptions;
import com.coordinate.model.bids.ImmutableBidEntity;
import com.coordinate.model.coordinator.CoordinatorBidDto;

import java.util.List;
import java.util.UUID;

public interface ICoordinatorBidsRepository {
    List<CoordinatorBidDto> getAllCoordinatorsBids(UUID coordinatorId, QueryOptions queryOptions);
    Boolean bidExists(UUID coordinatorId, UUID eventId);
    void createBid(UUID coordinatorId, ImmutableBidEntity bidEntity);

}
