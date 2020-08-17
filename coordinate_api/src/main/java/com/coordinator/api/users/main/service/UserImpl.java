package com.coordinator.api.users.main.service;

import com.coordinate.model.user.UserDto;
import com.coordinator.api.users.main.repository.IUserRepository;
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
