package com.coordinator.core.coordinator.repository;

import com.coordinator.core.coordinator.models.CoordinatorDto;
import com.coordinator.core.coordinator.models.CoordinatorPutRequest;

import java.util.UUID;

public interface ICoordinatorsRepository {
    CoordinatorDto getCoordinator(UUID coordinatorId);
    void updateArchiveCoordinator(UUID coordinatorId) throws IllegalStateException;
    void createCoordinator(UUID coordinatorId, String username);
    void updateCoordinator(UUID coordinatorId, CoordinatorPutRequest coordinatorDto);
}
