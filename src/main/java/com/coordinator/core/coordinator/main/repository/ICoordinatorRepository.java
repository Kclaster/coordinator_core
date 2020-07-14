package com.coordinator.core.coordinator.main.repository;

import com.coordinator.core.coordinator.main.models.CoordinatorDto;
import com.coordinator.core.coordinator.main.models.CoordinatorPutRequest;
import com.coordinator.core.coordinator.main.models.ImmutableCoordinatorEntity;
import com.coordinator.core.general.main.models.BaseDto;

import java.util.UUID;

public interface ICoordinatorRepository {
    CoordinatorDto getCoordinator(UUID coordinatorId);
    void updateArchiveCoordinator(UUID coordinatorId) throws IllegalStateException;
    void createCoordinator(ImmutableCoordinatorEntity immutableCoordinatorEntity);
    void updateCoordinator(UUID coordinatorId, CoordinatorPutRequest coordinatorDto);
    BaseDto getCoordinatorFromAuthUserId(UUID authUserId);
}
