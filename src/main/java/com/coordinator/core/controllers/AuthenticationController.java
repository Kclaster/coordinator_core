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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    @Autowired
    private IAuthUser iAuthUser;

    @PostMapping("/register")
    public ResponseEntity<AuthUserRequest> registerUserAccount(
            @RequestBody @Valid AuthUserRequest authUserRequest,
            HttpServletRequest request, Errors errors) {

        try {
            AuthUser registered = iAuthUser.registerNewUserAccount(authUserRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();        }

        return ResponseEntity.ok(authUserRequest);
    }


}
