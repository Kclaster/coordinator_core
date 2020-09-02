package com.coordinator.api.coordinator.event.service;

import com.coordinate.model.coordinator.CoordinatorEventDto;
import com.coordinate.model.QueryOptions;

import java.util.List;
import java.util.UUID;

public interface ICoordinatorEvents {
    List<CoordinatorEventDto> getAllCoordinatorEventsByZipCode(
            UUID coordinatorId,
            QueryOptions queryOptions
    );
}
