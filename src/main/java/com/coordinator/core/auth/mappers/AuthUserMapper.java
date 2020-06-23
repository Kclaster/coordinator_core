package com.coordinator.core.auth.mappers;

import com.coordinator.core.auth.ApplicationUserRole;
import com.coordinator.core.auth.models.AuthUserEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthUserMapper implements RowMapper<AuthUserEntity> {

    public AuthUserEntity mapRow(ResultSet rs, int i) throws SQLException {
        AuthUserEntity authUserEntity = new AuthUserEntity(
                rs.getString("username"),
                rs.getString("password"),
                ApplicationUserRole.valueOf(rs.getInt("authUserRoleId")).get().getGrantedAuthorities(),
                rs.getBoolean("isExpired"),
                rs.getBoolean("isLocked"),
                rs.getBoolean("isCredentialsExpired"),
                rs.getBoolean("isEnabled"));

        return authUserEntity;
    }
}
