package com.coordinator.core.services;

import com.coordinator.core.models.UserDto;

import java.util.List;

public interface IUser {
    public List<UserDto> getAllUsers();
}
