package com.coordinator.core.auth.service;

import com.coordinator.core.auth.models.AuthUserDto;
import com.coordinator.core.auth.models.AuthUserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAuthUser extends UserDetailsService {
    AuthUserDto registerNewUserAccount(AuthUserRequest authUserRequest) throws NullPointerException;
}
