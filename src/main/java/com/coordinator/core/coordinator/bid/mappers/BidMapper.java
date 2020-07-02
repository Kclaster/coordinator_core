package com.coordinator.core.coordinator.bid.mappers;

import com.coordinator.core.coordinator.bid.models.BidDto;
import com.coordinator.core.users.event.models.EventDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class BidMapper implements RowMapper<BidDto> {
    public BidDto mapRow(ResultSet rs, int i) throws SQLException {
        BidDto bidDto = new BidDto();
        bidDto.setId(UUID.fromString(rs.getString("id")));
        bidDto.setBidAmount(rs.getInt("bidAmount"));
        bidDto.setBidStatusId(rs.getInt("bidStatusId"));
        bidDto.setCoordinatorId(UUID.fromString(rs.getString("coordinatorId")));
        bidDto.setMessageToUser(rs.getString("messageToUser"));

        EventDto event = new EventDto();
        event.setDesiredPostalCode(rs.getString("desiredPostalCode"));
        event.setDesiredCity(rs.getString("desiredCity"));
        event.setAdditionalUserComments(rs.getString("additionalUserComments"));
        event.setCoordinatorId(UUID.fromString(rs.getString("coordinatorId")));
        event.setDesiredServiceId(rs.getInt("desiredServiceId"));
        event.setDesiredState(rs.getString("desiredState"));
        event.setEventStartDate(rs.getTimestamp("eventStartDate").toInstant().toEpochMilli());
        event.setEventEndDate(rs.getTimestamp("eventEndDate").toInstant().toEpochMilli());
        event.setEventSize(rs.getInt("eventSize"));
        event.setEventTypeId(rs.getInt("eventTypeId"));
        event.setId(UUID.fromString(rs.getString("id")));
        event.setVenueId(UUID.fromString(rs.getString("venueId")));
        event.setArchived(rs.getBoolean("isArchived"));
        bidDto.setEvent(event);


        return bidDto;
    }
}
