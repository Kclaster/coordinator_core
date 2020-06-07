package com.coordinator.core.mappers;

import com.coordinator.core.models.CoordinatorDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CoordinatorMapper implements RowMapper<CoordinatorDto> {
    public CoordinatorDto mapRow(ResultSet rs, int i) throws SQLException {
        CoordinatorDto coordinatorDto = new CoordinatorDto();
        coordinatorDto.setId(UUID.fromString(rs.getString("id")));
        coordinatorDto.setContactEmail(rs.getString("contactEmail"));
        coordinatorDto.setLevelOneDefaultBid(rs.getInt("levelOneDefaultBid"));
        coordinatorDto.setLevelTwoDefaultBid(rs.getInt("levelTwoDefaultBid"));
        coordinatorDto.setLevelThreeDefaultBid(rs.getInt("levelThreeDefaultBid"));
        coordinatorDto.setContactEmail(rs.getString("contactEmail"));
        coordinatorDto.setMaxDistanceToClient(rs.getInt("maxDistanceToClient"));
        coordinatorDto.setOfficeAddress(rs.getString("officeAddress"));
        coordinatorDto.setTitle(rs.getString("title"));

        return coordinatorDto;
    }
}
