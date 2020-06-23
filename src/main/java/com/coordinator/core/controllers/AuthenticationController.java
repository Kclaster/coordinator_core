package com.coordinator.core.controllers;

import com.coordinator.core.models.AuthUser;
import com.coordinator.core.models.AuthUserRequest;
import com.coordinator.core.services.IAuthUser;
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
            AuthUser registered = iAuthUser.registerNewUserAccount(authUserRequest);
            URI location = URI.create("api/v1/auth/login");

            return ResponseEntity.created(location).build();
        }
}
