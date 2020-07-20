package com.coordinator.core.coordinator.main.service;

import com.coordinator.core.coordinator.main.models.CoordinatorPutRequest;

import java.util.UUID;

public interface ICoordinatorZipCode {
    void UpdateCoordinatorsZipCode(UUID coordinatorId, CoordinatorPutRequest coordinatorPutRequest) throws Exception;
}
