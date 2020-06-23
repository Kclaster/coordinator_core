package com.coordinator.core.auth.repository;

import com.coordinator.core.auth.models.AuthUserEntity;
import com.coordinator.core.auth.models.AuthUserRequest;

import java.util.Optional;

public interface IAuthUserRepository {
    public Optional<AuthUserEntity> selectApplicationUserByUsername(String username);
    public void saveAuthUser(AuthUserRequest authUserRequest);
}
