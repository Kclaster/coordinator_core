package com.coordinator.core.services;

import com.coordinator.core.models.UserDto;
import com.coordinator.core.repository.core.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImpl implements IUser {
    @Autowired
    private IUserRepository iUserRepository;

    public List<UserDto> getAllUsers() {
        return iUserRepository.getAllUsers();
    }
}
