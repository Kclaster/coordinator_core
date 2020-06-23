package com.coordinator.core.repository.core;

import com.coordinator.core.models.EventPostRequest;

import java.util.UUID;

public interface IEventsRepository {
    void createEvent(UUID userId, EventPostRequest eventPostRequest);
}
