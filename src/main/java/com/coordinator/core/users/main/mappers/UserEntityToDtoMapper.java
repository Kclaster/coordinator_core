package com.coordinator.core.users.main.mappers;

import com.coordinator.core.users.main.models.UserDto;
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
            userDto.setEventId(UUID.fromString(rs.getString("eventId")));
            return userDto;
        }
}
