package com.coordinator.core.users.event.service;

import com.coordinator.core.users.event.models.EventPostRequest;

import java.util.UUID;

public interface IEvent {
    public void createEvent(UUID userId, EventPostRequest eventPostRequest);
}