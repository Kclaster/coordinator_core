package com.coordinator.api.general.user.controller;

import com.coordinate.model.security.AuthUserRequest;
import com.coordinate.model.security.AuthenticationRequest;
import com.coordinate.model.user.AuthUser;
import com.coordinate.security.config.JwtConfig;
import com.coordinate.security.service.IAuthUserService;
import com.coordinate.security.util.JwtUtil;
import com.coordinator.api.general.user.service.IOperatorService;
import com.coordinator.api.users.main.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthUserController {
    private final AuthenticationManager authenticationManager;
    private final IOperatorService iOperatorService;
    private final JwtUtil jwtUtil;
    private final JwtConfig jwtConfig;
    private final IAuthUserService iAuthUserService;
    private final IUser iUser;

    @Autowired
    public AuthUserController(
            AuthenticationManager authenticationManager,
            IOperatorService iOperatorService,
            JwtUtil jwtUtil, JwtConfig jwtConfig,
            IAuthUserService iAuthUserService,
            IUser iUser
    ) {
        this.authenticationManager = authenticationManager;
        this.iOperatorService = iOperatorService;
        this.jwtUtil = jwtUtil;
        this.jwtConfig = jwtConfig;
        this.iAuthUserService = iAuthUserService;
        this.iUser = iUser;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUserAccount(
            @Valid @RequestBody AuthUserRequest authUserRequest,
            Errors errors) throws NullPointerException {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }
        try {
            var authUserId = iAuthUserService.registerNewUserAccount(authUserRequest);
            iOperatorService.createRoleId(authUserId, authUserRequest.getUsername(), authUserRequest.getRoleId());


            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/authenticate")
    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        // Prove I am who I say I am
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        try {
            final AuthUser authUser = iAuthUserService
                    .loadUserByUsername(authenticationRequest.getUsername());

            var operatorId = iOperatorService.loadOperatorIdByUsername(
                    authUser.getId(),
                    authUser.getRoleId());

            var claims = new HashMap<String, Object>();
            claims.put("operatorId", operatorId.getId());
            claims.put("roleId", authUser.getRoleId());

            // Add new instance of jwt token to custom header
            final String jwt = jwtUtil.generateToken(authUser, claims);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + jwt);

            return new ResponseEntity(responseHeaders, HttpStatus.OK);

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e);
        }

    }
}
