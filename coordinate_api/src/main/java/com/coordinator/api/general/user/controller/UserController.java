package com.coordinator.api.general.user.controller;

import com.coordinate.model.security.AuthUserRequest;
import com.coordinate.model.security.AuthenticationRequest;
import com.coordinate.security.config.JwtConfig;
import com.coordinate.security.service.IAuthUserService;
import com.coordinate.security.util.JwtUtil;
import com.coordinator.api.general.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final IAuthUserService iAuthUserService;
    private final IUserService iUserService;
    private final JwtUtil jwtUtil;
    private final JwtConfig jwtConfig;

    @Autowired
    public UserController(
            AuthenticationManager authenticationManager,
            IAuthUserService iAuthUserService,
            JwtUtil jwtUtil, JwtConfig jwtConfig,
            IUserService iUserService
    ) {
        this.authenticationManager = authenticationManager;
        this.iAuthUserService = iAuthUserService;
        this.jwtUtil = jwtUtil;
        this.jwtConfig = jwtConfig;
        this.iUserService = iUserService;
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
            iUserService.createRoleId(authUserId, authUserRequest.getUsername(), authUserRequest.getRoleId());


            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/authenticate")
    @PostMapping
    public ResponseEntity<Void> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = iAuthUserService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + jwt);


        return new ResponseEntity<>(responseHeaders, HttpStatus.OK);

    }
}
