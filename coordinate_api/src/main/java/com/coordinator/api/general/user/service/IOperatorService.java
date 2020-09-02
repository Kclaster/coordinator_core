package com.coordinator.api.general.user.service;

import com.coordinate.model.BaseDto;

import java.util.UUID;

public interface IOperatorService {
    void createRoleId(UUID newAuthUserId, String contactEmail, Integer roleId);
    BaseDto loadOperatorIdByUsername(UUID authUserId, Integer roleId);
}
