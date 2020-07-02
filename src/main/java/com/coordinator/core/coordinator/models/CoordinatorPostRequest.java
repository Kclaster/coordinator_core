package com.coordinator.core.coordinator.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CoordinatorPostRequest {
    @NotNull(message="Email address is required.")
    @NotEmpty(message="Email address is required.")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
