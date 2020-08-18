package com.coordinator.api.coordinator.bid.service;

import com.coordinate.model.coordinator.CoordinatorBidDto;
import com.coordinate.model.bids.BidPostRequest;
import com.coordinate.model.QueryOptions;

import java.util.List;
import java.util.UUID;

public interface ICoordinatorBid {
    List<CoordinatorBidDto> getAllCoordinatorsBids(UUID coordinatorId, QueryOptions queryOptions);
    void createBid(UUID coordinatorId, BidPostRequest bidPostRequest);
}
