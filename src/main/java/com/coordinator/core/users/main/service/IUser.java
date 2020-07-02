package com.coordinator.core.users.main.service;

import com.coordinator.core.users.main.models.UserDto;

import java.util.List;

public interface IUser {
    List<UserDto> getAllUsers();
}
