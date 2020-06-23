package com.coordinator.core.auth.controller;

import com.coordinator.core.auth.models.AuthUserEntity;
import com.coordinator.core.auth.models.AuthUserRequest;
import com.coordinator.core.auth.service.IAuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    @Autowired
    private IAuthUser iAuthUser;

    @PostMapping("/register")
    public ResponseEntity<?> registerUserAccount(
            @Valid @RequestBody AuthUserRequest authUserRequest,
            Errors errors) throws NullPointerException {
            if (errors.hasErrors()) {
                return ResponseEntity.badRequest().body(errors.getFieldError());
            }
            AuthUserEntity registered = iAuthUser.registerNewUserAccount(authUserRequest);
            URI location = URI.create(String.format("api/v1/auth/login", registered.getUsername()));

            return ResponseEntity.created(location).build();
        }
}
