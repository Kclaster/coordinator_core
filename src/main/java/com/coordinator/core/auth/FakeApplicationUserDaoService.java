package com.coordinator.core.auth;

import com.coordinator.core.security.ApplicationUserRole;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.coordinator.core.security.ApplicationUserRole.ADMIN;
import static com.coordinator.core.security.ApplicationUserRole.USER;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    // TODO: this seem unperformant with a large pool of auth_users
    // TODO: password should be encoded in database
    private List<ApplicationUser> getApplicationUsers() {
            List<ApplicationUser> applicationUsers = Lists.newArrayList(
                    new ApplicationUser(
                            "bobIsCool",
                            passwordEncoder.encode("password"),
                            ApplicationUserRole.valueOf(3).get().getGrantedAuthorities(),
                            true,
                            true,
                            true,
                            true
                    ),
                    new ApplicationUser(
                            "Chris",
                            passwordEncoder.encode("password"),
                            ADMIN.getGrantedAuthorities(),
                            true,
                            true,
                            true,
                            true
                    ),
                    new ApplicationUser(
                            "Bob",
                            passwordEncoder.encode("password"),
                            USER.getGrantedAuthorities(),
                            true,
                            true,
                            true,
                            true
                    )
            );

            return applicationUsers;
    }
}
