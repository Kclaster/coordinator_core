package com.coordinator.api.users.main.mappers;

import com.coordinate.model.user.UserDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserEntityToDtoMapper implements RowMapper<UserDto> {
        public UserDto mapRow(ResultSet rs, int i) throws SQLException {
            UserDto userDto = new UserDto();
            userDto.setId(UUID.fromString(rs.getString("id")));
            userDto.setName(rs.getString("name"));
            userDto.setContactEmail(rs.getString("contactEmail"));
            userDto.setContactPhoneNumber(rs.getString("contactPhoneNumber"));
            return userDto;
        }
}
