package com.coordinator.api.coordinator.event.repository;

import com.coordinate.model.CoordinatorEventDto;
import com.coordinate.model.QueryOptions;

import java.util.List;
import java.util.UUID;

public interface ICoordinatorEventsRepository {
    List<CoordinatorEventDto> getAllCoordinatorEventsByZipCode(UUID coordinatorId, QueryOptions queryOptions);
}
