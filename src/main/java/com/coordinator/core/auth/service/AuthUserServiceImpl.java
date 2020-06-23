package com.coordinator.core.auth.service;

import com.coordinator.core.auth.models.AuthUserEntity;
import com.coordinator.core.auth.models.AuthUserRequest;
import com.coordinator.core.auth.repository.IAuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;

@Service
public class AuthUserServiceImpl implements IAuthUser {
    @Autowired
    private final PasswordEncoder passwordEncoder;
    private final IAuthUserRepository iAuthUserRepository;

    @Autowired
    public AuthUserServiceImpl(@Qualifier("postgres") IAuthUserRepository iAuthUserRepository, PasswordEncoder passwordEncoder) {
        this.iAuthUserRepository = iAuthUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return iAuthUserRepository
                    .selectApplicationUserByUsername(username)
                    .orElseThrow(() ->
                            new UsernameNotFoundException(String.format("Username %s not found", username)));

    }

    @Transactional
    @Override
    public AuthUserEntity registerNewUserAccount(AuthUserRequest authUserRequest)
            throws ConstraintViolationException {

        if (usernameExist(authUserRequest.getUsername())) {
            throw new NullPointerException(
                    "There is an account with that email address: "
                            +  authUserRequest.getUsername());
        }

        authUserRequest.setPassword(passwordEncoder.encode(authUserRequest.getPassword()));
        iAuthUserRepository.saveAuthUser(authUserRequest);

        return iAuthUserRepository.selectApplicationUserByUsername(authUserRequest.getUsername()).get();
    }

    private boolean usernameExist(String username) {
        return iAuthUserRepository.selectApplicationUserByUsername(username) != null;
    }
}