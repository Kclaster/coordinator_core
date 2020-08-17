package com.coordinator.api.users.main.mappers;

import com.coordinate.model.ImmutableUserEntity;

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
