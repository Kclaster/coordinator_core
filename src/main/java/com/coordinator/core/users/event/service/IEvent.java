package com.coordinator.core.users.event.service;

import com.coordinator.core.users.event.models.EventDto;
import com.coordinator.core.users.event.models.EventPostRequest;

import java.util.UUID;

public interface IEvent {
    EventDto createEvent(UUID userId, EventPostRequest eventPostRequest);

    EventDto getEvent(UUID userID);
}