package com.coordinator.core.users.event.mappers;

import com.coordinator.core.users.event.models.EventPostRequest;
import com.coordinator.core.users.event.models.ImmutableEventEntity;

import java.sql.Timestamp;
import java.util.UUID;

public class EventPostRequestToEntityMapper {
    public static ImmutableEventEntity mapEventRequestToEntity(EventPostRequest eventPostRequest) {
        ImmutableEventEntity eventEntity = ImmutableEventEntity.builder()
                .id(UUID.randomUUID())
                .eventStartDate(new Timestamp(eventPostRequest.getEventStartDate()))
                .eventEndDate(new Timestamp(eventPostRequest.getEventEndDate()))
                .eventSize(eventPostRequest.getEventSize())
                .eventTypeId(eventPostRequest.getEventTypeId())
                .desiredServiceId(eventPostRequest.getDesiredServiceId())
                .additionalUserComments(eventPostRequest.getAdditionalUserComments())
                .desiredState(eventPostRequest.getDesiredState())
                .desiredCity(eventPostRequest.getDesiredCity())
                .desiredPostalCode(eventPostRequest.getDesiredPostalCode())
                .venueId(eventPostRequest.getVenueId())
                .coordinatorId(eventPostRequest.getCoordinatorId())
                .build();

        return eventEntity;
    }

}
