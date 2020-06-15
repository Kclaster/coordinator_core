package com.coordinator.core.mappers;

import com.coordinator.core.models.ApplicationUser;
import com.coordinator.core.enums.ApplicationUserRole;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthUserMapper implements RowMapper<ApplicationUser> {
    private final PasswordEncoder passwordEncoder;

    public AuthUserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public ApplicationUser mapRow(ResultSet rs, int i) throws SQLException {
        ApplicationUser applicationUser = new ApplicationUser(
                rs.getString("username"),
                passwordEncoder.encode(rs.getString("password")),
                ApplicationUserRole.valueOf(rs.getInt("authUserRoleId")).get().getGrantedAuthorities(),
                rs.getBoolean("isExpired"),
                rs.getBoolean("isLocked"),
                rs.getBoolean("isCredentialsExpired"),
                rs.getBoolean("isEnabled"));

        return applicationUser;
    }
}
