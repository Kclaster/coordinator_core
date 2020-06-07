package com.coordinator.core.services;

import com.coordinator.core.models.CoordinatorDto;

import java.util.UUID;

public interface ICoordinator {
    public CoordinatorDto getCoordinator(UUID id);
}
