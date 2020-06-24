package com.coordinator.core.coordinator.bid.service;

import com.coordinator.core.coordinator.bid.models.BidDto;
import com.coordinator.core.general.models.QueryOptions;

import java.util.List;
import java.util.UUID;

public interface IBid {
    List<BidDto> getAllCoordinatorsBids(UUID coordinatorId, QueryOptions queryOptions);
}
