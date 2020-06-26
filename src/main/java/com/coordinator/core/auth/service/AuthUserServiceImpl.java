package com.coordinator.core.auth.service;

import com.coordinator.core.auth.models.AuthUserRequest;
import com.coordinator.core.auth.repository.IAuthUserRepository;
import com.coordinator.core.general.mappers.BaseEntityToDtoMapper;
import com.coordinator.core.general.models.BaseDto;
import com.coordinator.core.users.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AuthUserServiceImpl implements IAuthUser {
    @Autowired
    private final PasswordEncoder passwordEncoder;
    private final IAuthUserRepository iAuthUserRepository;
    private final IUser iUser;

    @Autowired
    public AuthUserServiceImpl(@Qualifier("postgres") IAuthUserRepository iAuthUserRepository,
                               IUser iUser,
                               PasswordEncoder passwordEncoder
    ) {
        this.iAuthUserRepository = iAuthUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.iUser = iUser;
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
    public BaseDto registerNewUserAccount(AuthUserRequest authUserRequest)
            throws Exception {

        if (usernameExist(authUserRequest.getUsername())) {
            throw new NullPointerException(
                    "There is an account with that email address: "
                            +  authUserRequest.getUsername());
        }

        authUserRequest.setPassword(passwordEncoder.encode(authUserRequest.getPassword()));
        try {
            UUID newAuthUserId = iAuthUserRepository.saveAuthUser(authUserRequest);

            // TODO: 3 should be USER enum... why is this so hard
            if (authUserRequest.getRoleId() == 3) {
                iUser.postUser(newAuthUserId, authUserRequest.getUsername());
            }

           return BaseEntityToDtoMapper.mapEntityToDto(newAuthUserId);
        } catch (Exception e) {
            throw e;
        }
    }

    private boolean usernameExist(String username) {
        return iAuthUserRepository.selectApplicationUserByUsername(username) != null;
    }
}