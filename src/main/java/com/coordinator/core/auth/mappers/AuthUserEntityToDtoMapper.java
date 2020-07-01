package com.coordinator.core.auth.mappers;

import com.coordinator.core.auth.ApplicationUserRole;
import com.coordinator.core.auth.models.AuthUserDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthUserEntityToDtoMapper implements RowMapper<AuthUserDto> {

    public AuthUserDto mapRow(ResultSet rs, int i) throws SQLException {
        AuthUserDto authUserDto = new AuthUserDto(
                rs.getString("username"),
                rs.getString("password"),
                ApplicationUserRole.valueOf(rs.getInt("authUserRoleId")).get().getGrantedAuthorities(),
                rs.getBoolean("isExpired"),
                rs.getBoolean("isLocked"),
                rs.getBoolean("isCredentialsExpired"),
                rs.getBoolean("isEnabled"));

        return authUserDto;
    }
}
