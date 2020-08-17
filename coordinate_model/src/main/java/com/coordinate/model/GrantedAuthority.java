package com.coordinate.model;

public enum GrantedAuthority {
    COORDINATOR_READ("coordinator:read"),
    COORDINATOR_WRITE("coordinator:write"),
    EVENT_READ("event:read"),
    EVENT_WRITE("event:write");

    private final String permission;

    GrantedAuthority(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
