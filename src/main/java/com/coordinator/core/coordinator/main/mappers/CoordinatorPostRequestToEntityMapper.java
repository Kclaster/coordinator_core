package com.coordinator.core.coordinator.main.mappers;

import com.coordinator.core.coordinator.main.models.ImmutableCoordinatorEntity;

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
