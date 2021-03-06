package com.coordinate.model.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class UserPostRequest {
    private UUID id;
    @NotNull(message="Email address is required.")
    @NotEmpty(message="Email address is required.")
    private String contactEmail;
    private UUID authUserId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public UUID getAuthUserId() {
        return authUserId;
    }

    public void setAuthUserId(UUID authUserId) {
        this.authUserId = authUserId;
    }
}
