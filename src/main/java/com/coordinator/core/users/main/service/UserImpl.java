package com.coordinator.core.users.main.service;

import com.coordinator.core.users.main.models.UserDto;
import com.coordinator.core.users.main.repository.IUserRepository;
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
