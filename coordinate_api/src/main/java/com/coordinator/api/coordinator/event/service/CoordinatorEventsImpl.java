package com.coordinator.api.coordinator.event.service;

import com.coordinate.model.coordinator.CoordinatorEventDto;
import com.coordinate.model.QueryOptions;
import com.coordinator.api.coordinator.event.repository.ICoordinatorEventsRepository;
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
