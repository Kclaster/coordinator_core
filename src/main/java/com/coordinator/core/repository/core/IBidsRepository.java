package com.coordinator.core.repository.core;

import com.coordinator.core.models.BidDto;
import com.coordinator.core.models.QueryOptions;

import java.util.List;
import java.util.UUID;

public interface IBidsRepository {
    List<BidDto> getAllCoordinatorsBids(UUID coordinatorId, QueryOptions queryOptions);
}
