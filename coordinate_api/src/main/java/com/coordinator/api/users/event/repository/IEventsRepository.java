package com.coordinator.api.users.event.repository;

import com.coordinate.model.event.EventDto;
import com.coordinate.model.event.ImmutableEventEntity;

import java.util.UUID;

public interface IEventsRepository {
    void createEvent(UUID userId, ImmutableEventEntity eventEntity);
    EventDto getEvent(UUID eventId);
}