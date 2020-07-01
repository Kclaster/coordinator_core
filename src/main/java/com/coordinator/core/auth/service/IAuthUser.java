package com.coordinator.core.auth.service;

import com.coordinator.core.auth.models.AuthUserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAuthUser extends UserDetailsService {
    void registerNewUserAccount(AuthUserRequest authUserRequest) throws Exception;
}
