package com.coordinator.core.repository.core;

import com.coordinator.core.models.CoordinatorDto;
import com.coordinator.core.models.CoordinatorPutRequest;

import java.util.UUID;

public interface ICoordinatorsRepository {
    CoordinatorDto getCoordinator(UUID coordinatorId);
    void updateArchiveCoordinator(UUID coordinatorId) throws IllegalStateException;
    void createCoordinator(UUID coordinatorId, String username);
    void updateCoordinator(UUID coordinatorId, CoordinatorPutRequest coordinatorDto);
}
