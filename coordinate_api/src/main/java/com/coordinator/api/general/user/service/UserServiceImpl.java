package com.coordinator.api.general.user.service;

import com.coordinate.model.ImmutableCoordinatorEntity;
import com.coordinate.model.ImmutableUserEntity;
import com.coordinate.model.UserRole;
import com.coordinator.api.coordinator.main.repository.ICoordinatorRepository;
import com.coordinator.api.users.main.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.coordinator.api.coordinator.main.mappers.CoordinatorPostRequestToEntityMapper.mapCoordinatorRequestToEntity;
import static com.coordinator.api.users.main.mappers.UserPostRequestToEntityMapper.mapUserRequestToEntity;

@Service
public class UserServiceImpl implements IUserService {
    private final IUserRepository iUserRepository;
    private final ICoordinatorRepository iCoordinatorRepository;

    @Autowired
    public UserServiceImpl(
            IUserRepository iUserRepository,
            ICoordinatorRepository iCoordinatorRepository
    ) {
        this.iUserRepository = iUserRepository;
        this.iCoordinatorRepository = iCoordinatorRepository;
    }

    @Override
    public void createRoleId(UUID newAuthUserId, String contactEmail, Integer roleId) {
        switch (roleId) {
            case UserRole
                    .Constants.ROLE_USER:
                ImmutableUserEntity immutableUserEntity = mapUserRequestToEntity(newAuthUserId, contactEmail);
                iUserRepository.createUser(immutableUserEntity);
                break;
            case UserRole
                    .Constants.ROLE_COORDINATOR:
                ImmutableCoordinatorEntity immutableCoordinatorEntity = mapCoordinatorRequestToEntity(newAuthUserId, contactEmail);
                iCoordinatorRepository.createCoordinator(immutableCoordinatorEntity);
                break;
            case UserRole
                    .Constants.ROLE_ADMIN:
                break;
        }
    }
}
