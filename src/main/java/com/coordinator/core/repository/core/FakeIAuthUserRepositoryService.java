package com.coordinator.core.repository.core;

import com.coordinator.core.models.AuthUser;
import com.coordinator.core.enums.ApplicationUserRole;
import com.coordinator.core.models.AuthUserRequest;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.coordinator.core.enums.ApplicationUserRole.ADMIN;
import static com.coordinator.core.enums.ApplicationUserRole.USER;

@Repository("fake")
public class FakeIAuthUserRepositoryService implements IAuthUserRepository {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeIAuthUserRepositoryService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Optional<AuthUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<AuthUser> getApplicationUsers() {
            List<AuthUser> authUsers = Lists.newArrayList(
                    new AuthUser(
                            "bobIsCool",
                            passwordEncoder.encode("password"),
                            ApplicationUserRole.valueOf(3).get().getGrantedAuthorities(),
                            true,
                            true,
                            true,
                            true
                    ),
                    new AuthUser(
                            "Chris",
                            passwordEncoder.encode("password"),
                            ADMIN.getGrantedAuthorities(),
                            true,
                            true,
                            true,
                            true
                    ),
                    new AuthUser(
                            "Bob",
                            passwordEncoder.encode("password"),
                            USER.getGrantedAuthorities(),
                            true,
                            true,
                            true,
                            true
                    )
            );

            return authUsers;
    }

    @Override
    public void saveAuthUser(AuthUserRequest authUserRequest) {
    }
}
