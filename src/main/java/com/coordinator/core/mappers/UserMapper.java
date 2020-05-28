package com.coordinator.core.mappers;

import com.coordinator.core.models.UserDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserMapper implements RowMapper<UserDto> {
        public UserDto mapRow(ResultSet rs, int i) throws SQLException {
            UserDto userDto = new UserDto();
            userDto.setId(UUID.fromString(rs.getString("id")));
            userDto.setFirstName(rs.getString("firstName"));
            userDto.setLastName(rs.getString("lastName"));
            return userDto;
        }
}
