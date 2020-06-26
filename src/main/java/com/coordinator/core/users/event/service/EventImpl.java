package com.coordinator.core.users.event.service;

import com.coordinator.core.users.event.models.EventDto;
import com.coordinator.core.users.event.models.EventPostRequest;
import com.coordinator.core.users.event.models.ImmutableEventEntity;
import com.coordinator.core.users.event.repository.IEventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.coordinator.core.users.event.mappers.EventPostRequestToEntityMapper.mapEventRequestToEntity;

@Service
public class EventImpl implements IEvent {
    @Autowired
    private IEventsRepository iEventsRepository;

    @Override
    public EventDto getEvent(UUID userID) {
        return iEventsRepository.getEvent(userID);
    }

    @Override
    public void createEvent(UUID userId, EventPostRequest eventPostRequest) {
        ImmutableEventEntity eventEntity = mapEventRequestToEntity(eventPostRequest);

        iEventsRepository.createEvent(userId, eventEntity);

        // TODO: return event here
    }
}