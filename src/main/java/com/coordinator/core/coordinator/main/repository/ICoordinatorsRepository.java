package com.coordinator.core.coordinator.main.repository;

import com.coordinator.core.coordinator.main.models.CoordinatorDto;
import com.coordinator.core.coordinator.main.models.CoordinatorPutRequest;

import java.util.UUID;

public interface ICoordinatorsRepository {
    CoordinatorDto getCoordinator(UUID coordinatorId);
    void updateArchiveCoordinator(UUID coordinatorId) throws IllegalStateException;
    void createCoordinator(UUID coordinatorId, String username);
    void updateCoordinator(UUID coordinatorId, CoordinatorPutRequest coordinatorDto);
}
