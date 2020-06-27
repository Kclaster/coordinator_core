package com.coordinator.core.auth.repository;

import com.coordinator.core.auth.models.AuthUserDto;
import com.coordinator.core.auth.models.AuthUserRequest;

import java.util.Optional;

public interface IAuthUserRepository {
    public Optional<AuthUserDto> selectApplicationUserByUsername(String username);
    public void saveAuthUser(AuthUserRequest authUserRequest) throws Exception;
}
