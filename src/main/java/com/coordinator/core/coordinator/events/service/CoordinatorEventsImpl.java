package com.coordinator.core.coordinator.events.service;

import com.coordinator.core.coordinator.events.models.CoordinatorEventDto;
import com.coordinator.core.coordinator.events.repository.ICoordinatorEventsRepository;
import com.coordinator.core.general.main.models.QueryOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CoordinatorEventsImpl implements ICoordinatorEvents {
    @Autowired
    private ICoordinatorEventsRepository iCoordinatorEventsRepository;

    @Override
    public List<CoordinatorEventDto> getAllCoordinatorEventsByZipCode(
            UUID coordinatorId,
            QueryOptions queryOptions
    ) {
        return iCoordinatorEventsRepository.getAllCoordinatorEventsByZipCode(coordinatorId, queryOptions);
    }
}
