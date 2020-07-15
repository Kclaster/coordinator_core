package com.coordinator.core.coordinator.main.service;

import com.coordinator.core.coordinator.main.models.CoordinatorDto;
import com.coordinator.core.coordinator.main.models.CoordinatorPutRequest;

import java.util.UUID;

public interface ICoordinator {
    CoordinatorDto getCoordinator(UUID id);
    void updateArchiveCoordinator(UUID coordinatorId) throws IllegalStateException;
    CoordinatorDto updateCoordinator(UUID coordinatorId, CoordinatorPutRequest coordinatorPutRequest) throws Exception;
}
