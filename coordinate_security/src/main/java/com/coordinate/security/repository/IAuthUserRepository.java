package com.coordinate.security.repository;


import com.coordinate.model.security.AuthUserRequest;
import com.coordinate.model.user.User;

import java.util.Optional;
import java.util.UUID;

public interface IAuthUserRepository {
    Optional<User> selectUserByUsername(String username);

    UUID saveAuthUser(AuthUserRequest authUserRequest) throws Exception;
}
