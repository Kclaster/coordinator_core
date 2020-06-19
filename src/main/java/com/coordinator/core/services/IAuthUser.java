package com.coordinator.core.services;

import com.coordinator.core.models.AuthUser;
import com.coordinator.core.models.AuthUserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAuthUser extends UserDetailsService {
    AuthUser registerNewUserAccount(AuthUserRequest authUserRequest) throws Exception;
}
