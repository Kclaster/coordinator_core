package com.coordinator.core.auth.repository;

import com.coordinator.core.auth.models.AuthUserDto;
import com.coordinator.core.auth.ApplicationUserRole;
import com.coordinator.core.auth.models.AuthUserRequest;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.coordinator.core.auth.ApplicationUserRole.ADMIN;
import static com.coordinator.core.auth.ApplicationUserRole.USER;

@Repository("fake")
public class FakeIAuthUserRepositoryService implements IAuthUserRepository {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeIAuthUserRepositoryService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Optional<AuthUserDto> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<AuthUserDto> getApplicationUsers() {
            List<AuthUserDto> authUserEntities = Lists.newArrayList(
                    new AuthUserDto(
                            UUID.randomUUID(),
                            "bobIsCool",
                            passwordEncoder.encode("password"),
                            ApplicationUserRole.valueOf(3).get().getGrantedAuthorities(),
                            true,
                            true,
                            true,
                            true
                    ),
                    new AuthUserDto(
                            UUID.randomUUID(),
                            "Chris",
                            passwordEncoder.encode("password"),
                            ADMIN.getGrantedAuthorities(),
                            true,
                            true,
                            true,
                            true
                    ),
                    new AuthUserDto(
                            UUID.randomUUID(),
                            "Bob",
                            passwordEncoder.encode("password"),
                            USER.getGrantedAuthorities(),
                            true,
                            true,
                            true,
                            true
                    )
            );

            return authUserEntities;
    }

    @Override
    public void saveAuthUser(AuthUserRequest authUserRequest) {
    }
}
