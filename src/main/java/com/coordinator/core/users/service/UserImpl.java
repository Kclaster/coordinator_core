package com.coordinator.core.users.service;

import com.coordinator.core.users.models.ImmutableUserEntity;
import com.coordinator.core.users.models.UserDto;
import com.coordinator.core.users.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.coordinator.core.users.mappers.UserPostRequestToEntityMapper.mapEventRequestToEntity;

@Service
public class UserImpl implements IUser {
    @Autowired
    private IUserRepository iUserRepository;

    public List<UserDto> getAllUsers() {
        return iUserRepository.getAllUsers();
    }

    // This will only ever be called by AuthUserService.
    public UserDto postUser(UUID authId, String contactEmail) {
        ImmutableUserEntity userEntity = mapEventRequestToEntity(authId, contactEmail);
        iUserRepository.createUser(userEntity);

        return iUserRepository.getUser(userEntity.getId());
    }

}
