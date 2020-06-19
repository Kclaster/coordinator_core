package com.coordinator.core.services;

import com.coordinator.core.models.AuthUser;
import com.coordinator.core.models.AuthUserRequest;
import com.coordinator.core.repository.core.IAuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthUserServiceImpl implements IAuthUser {
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
    public AuthUser registerNewUserAccount(AuthUserRequest authUserRequest)
            throws Exception {

        if (usernameExist(authUserRequest.getUsername())) {
            throw new Exception(
                    "There is an account with that email address: "
                            +  authUserRequest.getUsername());
        }
        iAuthUserRepository.saveAuthUser(authUserRequest);

        return iAuthUserRepository.selectApplicationUserByUsername("kyleIsCool").get();
    }

    private boolean usernameExist(String username) {
        return iAuthUserRepository.selectApplicationUserByUsername(username) != null;
    }
}