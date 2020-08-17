package com.coordinator.api.users.main.service;

import com.coordinate.model.UserDto;

import java.util.List;

public interface IUser {
    List<UserDto> getAllUsers();
}
