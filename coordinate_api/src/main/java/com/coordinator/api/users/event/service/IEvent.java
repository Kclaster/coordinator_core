package com.coordinator.api.users.event.service;

import com.coordinate.model.event.EventDto;
import com.coordinate.model.event.EventPostRequest;
import com.coordinate.model.event.EventServicesPostRequest;

import java.util.UUID;

public interface IEvent {
    EventDto createEvent(UUID userId, EventPostRequest eventPostRequest);
    void createEventService(UUID eventId, EventServicesPostRequest eventServicesPostRequest);
    EventDto getEvent(UUID userID);
}