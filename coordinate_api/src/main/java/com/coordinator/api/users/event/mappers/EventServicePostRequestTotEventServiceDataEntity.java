package com.coordinator.api.users.event.mappers;

import com.coordinate.model.event.EventServicesPostRequest;
import com.coordinate.model.event.ImmutableEventDesiredServiceDataEntity;

import java.sql.Timestamp;
import java.util.UUID;

public class EventServicePostRequestTotEventServiceDataEntity {
    public static ImmutableEventDesiredServiceDataEntity mapServicePostToServiceDataEntity(UUID eventServiceDataId, EventServicesPostRequest eventServicesPostRequest) {
        return ImmutableEventDesiredServiceDataEntity.builder()
                .id(eventServiceDataId)
                .build();
    }
}
