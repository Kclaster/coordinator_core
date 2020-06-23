package com.coordinator.core.users.service;

import com.coordinator.core.users.models.UserDto;

import java.util.List;

public interface IUser {
    public List<UserDto> getAllUsers();
}
