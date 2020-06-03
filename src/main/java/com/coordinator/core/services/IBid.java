package com.coordinator.core.services;

import com.coordinator.core.models.BidDto;
import com.coordinator.core.models.QueryOptions;

import java.util.List;
import java.util.UUID;

public interface IBid {
    List<BidDto> getAllCoordinatorsBids(UUID coordinatorId, QueryOptions queryOptions);
}
