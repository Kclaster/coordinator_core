package com.coordinator.core.coordinator.events.service;

import com.coordinator.core.coordinator.events.models.CoordinatorEventDto;
import com.coordinator.core.general.main.models.QueryOptions;

import java.util.List;
import java.util.UUID;

public interface ICoordinatorEvents {
    List<CoordinatorEventDto> getAllCoordinatorEventsByZipCode(
            UUID coordinatorId,
            QueryOptions queryOptions
    );
}
