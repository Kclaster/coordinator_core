package com.coordinator.core.auth.controller;

import com.coordinator.core.auth.models.AuthUserRequest;
import com.coordinator.core.auth.service.IAuthUser;
import com.coordinator.core.general.models.BaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
            try {
                BaseDto baseDto = iAuthUser.registerNewUserAccount(authUserRequest);

                return ResponseEntity.ok(baseDto);
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        }
}
