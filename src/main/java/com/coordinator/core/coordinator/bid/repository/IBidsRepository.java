package com.coordinator.core.coordinator.bid.repository;

import com.coordinator.core.coordinator.bid.models.BidDto;
import com.coordinator.core.general.main.models.QueryOptions;

import java.util.List;
import java.util.UUID;

public interface IBidsRepository {
    List<BidDto> getAllCoordinatorsBids(UUID coordinatorId, QueryOptions queryOptions);
}
