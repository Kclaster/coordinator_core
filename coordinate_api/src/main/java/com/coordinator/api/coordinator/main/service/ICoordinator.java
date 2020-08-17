package com.coordinator.api.coordinator.main.service;

import com.coordinate.model.coordinator.CoordinatorDto;
import com.coordinate.model.coordinator.CoordinatorPutRequest;

import java.util.UUID;

public interface ICoordinator {
    CoordinatorDto getCoordinator(UUID id);
    void updateArchiveCoordinator(UUID coordinatorId) throws IllegalStateException;
    CoordinatorDto updateCoordinator(UUID coordinatorId, CoordinatorPutRequest coordinatorPutRequest) throws Exception;
}
