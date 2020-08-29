package com.coordinate.security.service;

import com.coordinate.model.security.AuthUserRequest;
import com.coordinate.model.user.AuthUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface IAuthUserService extends UserDetailsService {
    UUID registerNewUserAccount(AuthUserRequest authUserRequest) throws Exception;

    AuthUser loadUserByUsername(String username);
}
