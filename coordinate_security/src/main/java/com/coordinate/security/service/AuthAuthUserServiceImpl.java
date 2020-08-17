package com.coordinate.security.service;

import com.coordinate.model.AuthUserRequest;
import com.coordinate.security.repository.IAuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AuthAuthUserServiceImpl implements IAuthUserService {
    private final IAuthUserRepository iAuthUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthAuthUserServiceImpl(
            IAuthUserRepository iAuthUserRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.iAuthUserRepository = iAuthUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return iAuthUserRepository
                .selectUserByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Username %s not found", username)));

    }

    @Transactional
    @Override
    public UUID registerNewUserAccount(AuthUserRequest authUserRequest)
            throws Exception {
        if (usernameExist(authUserRequest.getUsername())) {
            throw new NullPointerException(
                    "There is an account with that email address: "
                            +  authUserRequest.getUsername());
        }

        authUserRequest.setPassword(passwordEncoder.encode(authUserRequest.getPassword()));
        try {
           return iAuthUserRepository.saveAuthUser(authUserRequest);

        } catch (Exception e) {
            throw e;
        }

    }

    private boolean usernameExist(String username) {
        return iAuthUserRepository.selectUserByUsername(username).isPresent();
    }
}
