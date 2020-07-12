package com.coordinator.core.auth.filters;

import com.coordinator.core.auth.ApplicationUserRole;
import com.coordinator.core.auth.config.JwtConfig;
import com.coordinator.core.auth.models.AuthUserDto;
import com.coordinator.core.auth.models.UsernameAndPasswordAuthenticationRequest;
import com.coordinator.core.coordinator.main.repository.ICoordinatorRepository;
import com.coordinator.core.general.models.BaseDto;
import com.coordinator.core.users.main.repository.IUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class JwtUsernameAndPasswordJwtFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;
    private final IUserRepository iUserRepository;
    private final ICoordinatorRepository iCoordinatorRepository;

    public JwtUsernameAndPasswordJwtFilter(AuthenticationManager authenticationManager,
                                           JwtConfig jwtConfig,
                                           SecretKey secretKey,
                                           IUserRepository iUserRepository,
                                           ICoordinatorRepository iCoordinatorRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
        this.iUserRepository = iUserRepository;
        this.iCoordinatorRepository = iCoordinatorRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            );

            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        AuthUserDto userDetails = (AuthUserDto) authResult.getPrincipal();
        BaseDto userBaseDto = getRoleId(userDetails.getRoleId(), userDetails.getId());
        Integer role = userDetails.getRoleId();

        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("operatorId", userBaseDto.getId())
                .claim("authorities", authResult.getAuthorities())
                .claim("roleId", role)
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                .signWith(secretKey)
                .compact();

        response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + token);
    }

    private BaseDto getRoleId(Integer roleId, UUID authUserId) {
        switch(roleId) {
            case ApplicationUserRole
                    .Constants.ROLE_USER:
                return iUserRepository.getUserFromAuthUserId(authUserId);
            default:
                return iCoordinatorRepository.getCoordinatorFromAuthUserId(authUserId);
        }
    }


}
