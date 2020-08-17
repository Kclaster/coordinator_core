package com.coordinator.api.coordinator.main.service;

import com.coordinate.model.coordinator.CoordinatorPutRequest;

import java.util.UUID;

public interface ICoordinatorZipCode {
    void UpdateCoordinatorsZipCode(UUID coordinatorId, CoordinatorPutRequest coordinatorPutRequest) throws Exception;
}
