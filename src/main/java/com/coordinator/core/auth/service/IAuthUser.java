package com.coordinator.core.auth.service;

import com.coordinator.core.auth.models.AuthUserRequest;
import com.coordinator.core.general.models.BaseDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAuthUser extends UserDetailsService {
    BaseDto registerNewUserAccount(AuthUserRequest authUserRequest) throws Exception;
}
