package com.coordinator.core.users.event.repository;

import com.coordinator.core.users.event.models.EventDto;
import com.coordinator.core.users.event.models.ImmutableEventEntity;

import java.util.UUID;

public interface IEventsRepository {
    void createEvent(UUID userId, ImmutableEventEntity eventEntity);

    EventDto getEvent(UUID eventId);

}