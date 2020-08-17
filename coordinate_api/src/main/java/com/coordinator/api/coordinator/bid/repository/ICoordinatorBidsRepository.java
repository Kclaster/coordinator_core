package com.coordinator.api.coordinator.bid.repository;

import com.coordinate.model.BidDto;
import com.coordinate.model.ImmutableBidEntity;
import com.coordinate.model.QueryOptions;

import java.util.List;
import java.util.UUID;

public interface ICoordinatorBidsRepository {
    List<BidDto> getAllCoordinatorsBids(UUID coordinatorId, QueryOptions queryOptions);
    Boolean bidExists(UUID coordinatorId, UUID eventId);
    void createBid(UUID coordinatorId, ImmutableBidEntity bidEntity);

}
