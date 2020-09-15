package com.coordinator.api.users.event.repository;

import com.coordinate.model.event.EventDto;
import com.coordinate.model.event.ImmutableEventDesiredServiceEntity;
import com.coordinate.model.event.ImmutableEventEntity;
import com.coordinate.model.event.ImmutableServiceDatePlaceEntity;

import java.util.UUID;

public interface IEventsRepository {
    void createEvent(UUID userId, ImmutableEventEntity eventEntity);

    EventDto getEvent(UUID eventId);

    void addServicesToEvents(UUID eventId, UUID desiredServiceId);

    void createEventDesiredService(UUID eventId, ImmutableEventDesiredServiceEntity eventServicesPostRequest);

    void addEventDesiredServiceServiceDatePlace(UUID eventDesiredServiceId, UUID serviceDatePlaceId);

    void createServiceDatePlace(UUID serviceDatePlaceId, ImmutableServiceDatePlaceEntity serviceDatePlaceEntity);

    void createEventDesiredServiceData(UUID eventDesiredServiceDataID, Integer serviceTypeId);
}