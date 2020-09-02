package com.coordinator.api.coordinator.main.repository;

import com.coordinate.model.BaseDto;
import com.coordinate.model.coordinator.CoordinatorDto;
import com.coordinate.model.coordinator.CoordinatorPutRequest;
import com.coordinate.model.coordinator.ImmutableCoordinatorEntity;

import java.util.UUID;

public interface ICoordinatorRepository {
    CoordinatorDto getCoordinator(UUID coordinatorId);
    void updateArchiveCoordinator(UUID coordinatorId) throws IllegalStateException;
    void createCoordinator(ImmutableCoordinatorEntity immutableCoordinatorEntity);
    void updateCoordinator(UUID coordinatorId, CoordinatorPutRequest coordinatorDto);
    BaseDto getCoordinatorFromAuthUserId(UUID authUserId);
    void createCoordinatorsZipCodes(
            UUID joinTableId,
            UUID zipCodeId,
            UUID coordinatorId
    );
    void deleteCoordinatorsZipCodes(UUID coordinatorId);
}
