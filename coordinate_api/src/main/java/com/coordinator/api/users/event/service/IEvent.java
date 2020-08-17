package com.coordinator.api.users.event.service;

import com.coordinate.model.EventDto;
import com.coordinate.model.EventPostRequest;

import java.util.UUID;

public interface IEvent {
    EventDto createEvent(UUID userId, EventPostRequest eventPostRequest);

    EventDto getEvent(UUID userID);
}