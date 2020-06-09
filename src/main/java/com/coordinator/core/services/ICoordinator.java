package com.coordinator.core.services;

import com.coordinator.core.models.CoordinatorDto;
import com.coordinator.core.models.CoordinatorPutRequest;

import java.util.UUID;

public interface ICoordinator {
    CoordinatorDto getCoordinator(UUID id);
    void updateArchiveCoordinator(UUID coordinatorId) throws IllegalStateException;
    CoordinatorDto createCoordinator(String username);
    CoordinatorDto updateCoordinator(UUID coordinatorId, CoordinatorPutRequest coordinatorPutRequest) throws IllegalStateException;
}
