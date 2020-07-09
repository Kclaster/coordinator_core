package com.coordinator.core.users.main.mappers;


import com.coordinator.core.users.main.models.ImmutableUserEntity;

import java.util.UUID;

public class UserPostRequestToEntityMapper {
    public static ImmutableUserEntity mapUserRequestToEntity(UUID authUserId, String contactEmail) {
        ImmutableUserEntity userEntity = ImmutableUserEntity.builder()
                .id(UUID.randomUUID())
                .authUserId(authUserId)
                .contactEmail(contactEmail)
                .build();

        return userEntity;
    }
}
