package com.coordinator.api.users.event.service;

import com.coordinate.model.event.EventDto;
import com.coordinate.model.event.EventPostRequest;

import java.util.UUID;

public interface IEvent {
    EventDto createEvent(UUID userId, EventPostRequest eventPostRequest);

    EventDto getEvent(UUID userID);
}