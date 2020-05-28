package com.coordinator.core.repository.core;

import com.coordinator.core.models.UserDto;

import java.util.List;

public interface IUserRepository {
    List<UserDto> getAllUsers();
}
