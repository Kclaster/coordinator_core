package com.coordinator.api.coordinator.bid.service;

import com.coordinate.model.BidDto;
import com.coordinate.model.BidPostRequest;
import com.coordinate.model.QueryOptions;

import java.util.List;
import java.util.UUID;

public interface IBid {
    List<BidDto> getAllCoordinatorsBids(UUID coordinatorId, QueryOptions queryOptions);
    void createBid(UUID coordinatorId, BidPostRequest bidPostRequest);
}
