package com.coordinator.core.users.repository;

import com.coordinator.core.general.models.BaseDto;
import com.coordinator.core.users.models.ImmutableUserEntity;
import com.coordinator.core.users.models.UserDto;

import java.util.List;
import java.util.UUID;

public interface IUserRepository {
        BaseDto getUserFromAuthUserId(UUID authUserId);
        UserDto getUser(UUID userId);
        List<UserDto> getAllUsers();
        void createUser(ImmutableUserEntity immutableUserEntity);
}
