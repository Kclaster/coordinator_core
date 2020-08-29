package com.coordinate.security.mapper;


import com.coordinate.model.user.AuthUser;
import com.coordinate.model.user.UserRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class AuthUserMapper implements RowMapper<AuthUser> {
    public AuthUser mapRow(ResultSet rs, int i) throws SQLException {
        Optional<UserRole> roleId = UserRole.valueOf(rs.getInt("authUserRoleId"));

        return new AuthUser(
                UUID.fromString(rs.getString("id")),
                rs.getString("username"),
                rs.getString("password"),
                roleId.map(UserRole::getGrantedAuthorities).orElse(null),
                rs.getBoolean("isExpired"),
                rs.getBoolean("isLocked"),
                rs.getBoolean("isCredentialsExpired"),
                rs.getBoolean("isEnabled"),
                roleId.get().getValue());
    }
}