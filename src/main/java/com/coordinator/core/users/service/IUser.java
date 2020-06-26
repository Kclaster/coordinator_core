package com.coordinator.core.users.service;

import com.coordinator.core.users.models.UserDto;

import java.util.List;
import java.util.UUID;

public interface IUser {
    List<UserDto> getAllUsers();

    UserDto postUser(UUID authId, String contactEmail);
}
