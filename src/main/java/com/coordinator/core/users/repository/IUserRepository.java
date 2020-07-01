package com.coordinator.core.users.repository;

import com.coordinator.core.users.models.UserDto;

import java.util.List;

public interface IUserRepository {
    List<UserDto> getAllUsers();
}
