package com.coordinator.core.mappers;

import com.coordinator.core.enums.ApplicationUserRole;
import com.coordinator.core.models.AuthUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthUserMapper implements RowMapper<AuthUser> {

    public AuthUser mapRow(ResultSet rs, int i) throws SQLException {
        AuthUser authUser = new AuthUser(
                rs.getString("username"),
                rs.getString("password"),
                ApplicationUserRole.valueOf(rs.getInt("authUserRoleId")).get().getGrantedAuthorities(),
                rs.getBoolean("isExpired"),
                rs.getBoolean("isLocked"),
                rs.getBoolean("isCredentialsExpired"),
                rs.getBoolean("isEnabled"));

        return authUser;
    }
}
