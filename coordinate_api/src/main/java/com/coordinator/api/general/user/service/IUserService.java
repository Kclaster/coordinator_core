package com.coordinator.api.general.user.service;

import java.util.UUID;

public interface IUserService {
    void createRoleId(UUID newAuthUserId, String contactEmail, Integer roleId);
}
