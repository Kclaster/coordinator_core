package com.coordinator.api.coordinator.main.mappers;


import com.coordinate.model.ImmutableCoordinatorEntity;

import java.util.UUID;

public class CoordinatorPostRequestToEntityMapper {
    public static ImmutableCoordinatorEntity mapCoordinatorRequestToEntity(UUID authUserId, String contactEmail) {
        ImmutableCoordinatorEntity userEntity = ImmutableCoordinatorEntity.builder()
                .id(UUID.randomUUID())
                .authUserId(authUserId)
                .contactEmail(contactEmail)
                .build();

        return userEntity;
    }
}
