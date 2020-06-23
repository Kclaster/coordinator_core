package com.coordinator.core.services;

import com.coordinator.core.models.EventPostRequest;
import com.coordinator.core.repository.core.IEventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.coordinator.core.utils.GeneralHelper.CastStringToDate;

@Service
public class EventImpl implements IEvent {
    @Autowired
    private IEventsRepository iEventsRepository;

    @Override
    public void createEvent(UUID userId, EventPostRequest eventPostRequest) {
        UUID eventId = UUID.randomUUID();

        eventPostRequest.setEventId(eventId);
        eventPostRequest.setEventDate(CastStringToDate(eventPostRequest.getEventDate()));
        iEventsRepository.createEvent(userId, eventPostRequest);

        // TODO: return event here
    }
}
