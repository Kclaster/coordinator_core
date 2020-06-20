package com.coordinator.core.repository.core;

import com.coordinator.core.models.AuthUser;
import com.coordinator.core.models.AuthUserRequest;

import java.util.Optional;

public interface IAuthUserRepository {
    public Optional<AuthUser> selectApplicationUserByUsername(String username);
    public void saveAuthUser(AuthUserRequest authUserRequest);
}
