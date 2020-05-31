package com.coordinator.core.mappers;

import com.coordinator.core.models.BidDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class BidMapper implements RowMapper<BidDto> {
    public BidDto mapRow(ResultSet rs, int i) throws SQLException {
        BidDto bidDto = new BidDto();
        bidDto.setId(UUID.fromString(rs.getString("id")));
        bidDto.setBidAmount(rs.getString("bidAmount"));
        bidDto.setBidStatusId(rs.getInt("bidStatusId"));
        bidDto.setCoordinatorId(UUID.fromString(rs.getString("coordinatorId")));
        bidDto.setEventId(UUID.fromString(rs.getString("eventId")));
        bidDto.setMessageToUser(rs.getString("messageToUser"));
        return bidDto;
    }
}
