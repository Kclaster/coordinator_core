package com.coordinate.model.user;

import com.coordinate.model.security.GrantedAuthority;
import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.coordinate.model.security.GrantedAuthority.*;

public enum UserRole {
//    ADMIN has no roles for now.
    ADMIN(Sets.newHashSet(), Constants.ROLE_ADMIN),
    USER(Sets.newHashSet(EVENT_WRITE, EVENT_READ, COORDINATOR_READ), Constants.ROLE_COORDINATOR),
    COORDINATOR(Sets.newHashSet(COORDINATOR_READ, COORDINATOR_WRITE, EVENT_READ), Constants.ROLE_USER);

    private final int value;
    private final Set<GrantedAuthority> permissions;

    public static class Constants {
        public static final int ROLE_ADMIN = 1;
        public static final int ROLE_USER = 2;
        public static final int ROLE_COORDINATOR =3;
    }


    UserRole(Set<GrantedAuthority> permissions, int value) {
        this.permissions = permissions;
        this.value = value;
    }

    public Set<GrantedAuthority> getPermissions() {
        return permissions;
    }

    public Integer getValue() {
        return value;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;
    }

    public static Optional<UserRole> valueOf(int value) {
        return Arrays.stream(values())
                .filter(role -> role.value == value)
                .findFirst();
    }
}
