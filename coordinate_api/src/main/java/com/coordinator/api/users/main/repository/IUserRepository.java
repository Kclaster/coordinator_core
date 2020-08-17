package com.coordinator.api.users.main.repository;

import com.coordinate.model.BaseDto;
import com.coordinate.model.user.ImmutableUserEntity;
import com.coordinate.model.user.UserDto;

import java.util.List;
import java.util.UUID;

public interface IUserRepository {
        BaseDto getUserFromAuthUserId(UUID authUserId);
        UserDto getUser(UUID userId);
        List<UserDto> getAllUsers();
        void createUser(ImmutableUserEntity immutableUserEntity);
}
