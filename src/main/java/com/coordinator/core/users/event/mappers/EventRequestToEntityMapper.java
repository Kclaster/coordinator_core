package com.coordinator.core.users.event.mappers;

import com.coordinator.core.users.event.models.EventPostRequest;
import com.coordinator.core.users.event.models.ImmutableEventEntity;

import java.util.UUID;

import static com.coordinator.core.general.helpers.GeneralHelper.CastStringToDate;

public class EventRequestToEntityMapper {
    public static ImmutableEventEntity mapEventRequestToEntity(EventPostRequest eventPostRequest) {
        ImmutableEventEntity eventEntity = ImmutableEventEntity.builder()
                .id(UUID.randomUUID())
                .eventDate(CastStringToDate(eventPostRequest.getEventDate()))
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
