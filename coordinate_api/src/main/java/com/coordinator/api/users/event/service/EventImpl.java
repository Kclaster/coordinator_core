package com.coordinator.api.users.event.service;

import com.coordinate.model.event.EventDto;
import com.coordinate.model.event.EventPostRequest;
import com.coordinate.model.event.ImmutableEventEntity;
import com.coordinator.api.users.event.repository.IEventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.coordinator.api.users.event.mappers.EventPostRequestToEntityMapper.mapEventRequestToEntity;

@Service
public class EventImpl implements IEvent {
    @Autowired
    private IEventsRepository iEventsRepository;

    @Override
    public EventDto getEvent(UUID userID) {
        return iEventsRepository.getEvent(userID);
    }

    @Override
    public EventDto createEvent(UUID userId, EventPostRequest eventPostRequest) {
        boolean eventExists = iEventsRepository.getEvent(userId) != null;
        if (eventExists) {
            throw new NullPointerException(
                    "An event has already been registered for: "
                            +  userId);
        }

        ImmutableEventEntity eventEntity = mapEventRequestToEntity(eventPostRequest);

        iEventsRepository.createEvent(userId, eventEntity);

        return iEventsRepository.getEvent(userId);
    }
}