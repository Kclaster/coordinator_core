package com.coordinator.core.services;

import com.coordinator.core.models.CoordinatorDto;
import com.coordinator.core.repository.core.ICoordinatorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CoordinatorImpl implements ICoordinator {
    @Autowired
    private ICoordinatorsRepository iCoordinatorsRepository;

    @Override
    public CoordinatorDto getCoordinator(UUID coordinatorId) {
        return iCoordinatorsRepository.getCoordinator(coordinatorId);
    }

}
