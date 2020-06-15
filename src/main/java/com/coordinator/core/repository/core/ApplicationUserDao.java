package com.coordinator.core.repository.core;

import com.coordinator.core.models.ApplicationUser;

import java.util.Optional;

public interface ApplicationUserDao {
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
