package com.coordinator.core.users.mappers;

import com.coordinator.core.users.models.ImmutableUserEntity;

import java.util.UUID;

public class UserPostRequestToEntityMapper {
    public static ImmutableUserEntity mapEventRequestToEntity(UUID authUserId, String contactEmail) {
        ImmutableUserEntity userEntity = ImmutableUserEntity.builder()
                .id(UUID.randomUUID())
                .authUserId(authUserId)
                .contactEmail(contactEmail)
                .build();

        return userEntity;
    }
}
