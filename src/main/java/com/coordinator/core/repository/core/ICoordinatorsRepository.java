package com.coordinator.core.repository.core;

import com.coordinator.core.models.CoordinatorDto;

import java.util.UUID;

public interface ICoordinatorsRepository {
    CoordinatorDto getCoordinator(UUID coordinatorId);
}
