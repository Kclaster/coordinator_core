package com.coordinator.core.users.main.controller;

import com.coordinator.core.users.main.models.UserDto;
import com.coordinator.core.users.main.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_COORDINATOR, ROLE_USER')")
public class UserController {
    @Autowired
    private IUser iUser;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(iUser.getAllUsers());
    }
}
