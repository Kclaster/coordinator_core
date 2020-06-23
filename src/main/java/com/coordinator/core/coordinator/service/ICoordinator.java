package com.coordinator.core.coordinator.service;

import com.coordinator.core.coordinator.models.CoordinatorDto;
import com.coordinator.core.coordinator.models.CoordinatorPutRequest;

import java.util.UUID;

public interface ICoordinator {
    CoordinatorDto getCoordinator(UUID id);
    void updateArchiveCoordinator(UUID coordinatorId) throws IllegalStateException;
    CoordinatorDto createCoordinator(String username);
    CoordinatorDto updateCoordinator(UUID coordinatorId, CoordinatorPutRequest coordinatorPutRequest) throws IllegalStateException;
}
