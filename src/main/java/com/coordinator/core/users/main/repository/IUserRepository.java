package com.coordinator.core.users.main.repository;

import com.coordinator.core.general.main.models.BaseDto;
import com.coordinator.core.users.main.models.ImmutableUserEntity;
import com.coordinator.core.users.main.models.UserDto;

import java.util.List;
import java.util.UUID;

public interface IUserRepository {
        BaseDto getUserFromAuthUserId(UUID authUserId);
        UserDto getUser(UUID userId);
        List<UserDto> getAllUsers();
        void createUser(ImmutableUserEntity immutableUserEntity);
}
