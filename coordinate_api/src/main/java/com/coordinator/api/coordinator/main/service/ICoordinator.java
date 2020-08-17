package com.coordinator.api.coordinator.main.service;

import com.coordinate.model.CoordinatorDto;
import com.coordinate.model.CoordinatorPutRequest;

import java.util.UUID;

public interface ICoordinator {
    CoordinatorDto getCoordinator(UUID id);
    void updateArchiveCoordinator(UUID coordinatorId) throws IllegalStateException;
    CoordinatorDto updateCoordinator(UUID coordinatorId, CoordinatorPutRequest coordinatorPutRequest) throws Exception;
}
