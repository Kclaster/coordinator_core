package com.coordinator.core.security;

public enum ApplicationUserPermission {
    COORDINATOR_READ("coordinator:read"),
    COORDINATOR_WRITE("coordinator:write"),
    EVENT_READ("event:read"),
    EVENT_WRITE("event:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
