package com.coordinator.api.users.bids.mappers;


import com.coordinate.model.bids.BidDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class BidEntityToDtoMapper implements RowMapper<BidDto> {
    public BidDto mapRow(ResultSet rs, int i) throws SQLException {
        String coordinatorId = rs.getString("coordinatorId");

        BidDto bidDto = new BidDto();
        bidDto.setId(UUID.fromString(rs.getString("id")));
        bidDto.setBidAmount(rs.getInt("bidAmount"));
        bidDto.setBidStatusId(rs.getInt("bidStatusId"));
        bidDto.setCoordinatorId(coordinatorId != null ? UUID.fromString(coordinatorId) : null);
        bidDto.setMessageToUser(rs.getString("messageToUser"));

        return bidDto;
    }
}
