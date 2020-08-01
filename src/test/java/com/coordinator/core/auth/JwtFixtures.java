package com.coordinator.core.auth;

import io.jsonwebtoken.Jwts;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.UUID;

public class JwtFixtures {

    private JwtFixtures() { throw new UnsupportedOperationException("Cannot instantiate this class"); }

    public static String validToken(Integer roleId) {
        return token(roleId, tomorrow());
    }

    public static String  expiredToken() { return token(0, yesterday()); }

    public static String token(Integer roleId, Long expirationDate) {
        return Jwts.builder()
                .setSubject("foo")
                .claim("operatorId", UUID.randomUUID().toString())
                .claim("authorities", ApplicationUserRole.valueOf(roleId).get().getGrantedAuthorities())
                .claim("roleId", roleId)
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now()
                        .plusDays(expirationDate)))
//                .signWith(secretKey)
                .compact();
    }

    private static Long tomorrow() {
        return Instant.now().plus(Period.ofDays(1)).toEpochMilli();
    }

    private static Long yesterday() {
        return Instant.now().minus(Period.ofDays(1)).toEpochMilli();
    }
}
