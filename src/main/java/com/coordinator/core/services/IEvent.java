package com.coordinator.core.services;

import com.coordinator.core.models.EventPostRequest;

import java.util.UUID;

public interface IEvent {
    public void createEvent(UUID userId, EventPostRequest eventPostRequest);
}
