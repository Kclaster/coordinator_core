package com.coordinator.core.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.coordinator.core.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
//    ADMIN has no roles for now.
    ADMIN(Sets.newHashSet(), 1),
    USER(Sets.newHashSet(EVENT_WRITE, EVENT_READ, COORDINATOR_READ), 2),
    COORDINATOR(Sets.newHashSet(COORDINATOR_READ, COORDINATOR_WRITE, EVENT_READ), 3);

    private final int value;
    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions, int value) {
        this.permissions = permissions;
        this.value = value;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;
    }

    public static Optional<ApplicationUserRole> valueOf(int value) {
        return Arrays.stream(values())
                .filter(role -> role.value == value)
                .findFirst();
    }
}
