package com.coordinator.api.users.event.mappers;

import com.coordinate.model.event.EventPostRequest;
import com.coordinate.model.event.ImmutableEventEntity;

import java.sql.Timestamp;
import java.util.UUID;

public class EventPostRequestToEntityMapper {
    public static ImmutableEventEntity mapEventRequestToEntity(EventPostRequest eventPostRequest) {
        Long eventStartDate = eventPostRequest.getEventStartDate();
        Long eventEndDate = eventPostRequest.getEventEndDate();


        return ImmutableEventEntity.builder()
                .id(UUID.randomUUID())
                .eventStartDate(eventStartDate != null ? new Timestamp(eventStartDate) : null)
                .eventEndDate(eventEndDate != null ? new Timestamp(eventEndDate) : null)
                .eventSize(eventPostRequest.getEventSize())
                .eventTypeId(eventPostRequest.getEventTypeId())
                .desiredServiceId(eventPostRequest.getDesiredServiceId())
                .additionalUserComments(eventPostRequest.getAdditionalUserComments())
                .desiredState(eventPostRequest.getDesiredState())
                .desiredCity(eventPostRequest.getDesiredCity())
                .desiredPostalCode(eventPostRequest.getDesiredPostalCode())
                .coordinatorId(eventPostRequest.getCoordinatorId())
                .build();
    }

}
