package com.coordinate.model;

import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.UUID;

@Value.Immutable
public abstract class UserEntity {
    public abstract UUID getId();
    public abstract UUID getAuthUserId();

    @Value.Default
    public String getName() {
        return "";
    }

    @Value.Default
    public String getContactEmail() {
        return "";
    }

    @Value.Default
    public Integer getRoleId() {
        return 0;
    }

    @Value.Default
    public String getContactPhoneNumber() {
        return "";
    }
}
