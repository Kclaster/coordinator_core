package com.coordinate.security.service;

import com.coordinate.model.AuthUserRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface IAuthUserService extends UserDetailsService {
    UUID registerNewUserAccount(AuthUserRequest authUserRequest) throws Exception;

    UserDetails loadUserByUsername(String username);
}
