package com.coordinator.core.auth.service;

import com.coordinator.core.auth.models.AuthUserEntity;
import com.coordinator.core.auth.models.AuthUserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAuthUser extends UserDetailsService {
    AuthUserEntity registerNewUserAccount(AuthUserRequest authUserRequest) throws NullPointerException;
}
