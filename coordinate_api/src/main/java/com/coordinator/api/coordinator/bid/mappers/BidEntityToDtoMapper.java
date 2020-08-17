package com.coordinator.api.coordinator.bid.mappers;

import com.coordinate.model.BidDto;
import com.coordinate.model.EventDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

public class BidEntityToDtoMapper implements RowMapper<BidDto> {
    public BidDto mapRow(ResultSet rs, int i) throws SQLException {
        Timestamp eventStartDate = rs.getTimestamp("eventStartDate");
        Timestamp eventEndDate = rs.getTimestamp("eventEndDate");
        String coordinatorId = rs.getString("coordinatorId");
        String venueId = rs.getString("venueId");

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
        event.setCoordinatorId(coordinatorId != null ? UUID.fromString(coordinatorId) : null);
        event.setDesiredServiceId(rs.getInt("desiredServiceId"));
        event.setDesiredState(rs.getString("desiredState"));
        event.setEventStartDate(eventStartDate != null ? eventStartDate.toInstant().toEpochMilli() : null);
        event.setEventEndDate(eventEndDate != null ? eventEndDate.toInstant().toEpochMilli() : null);
        event.setEventSize(rs.getInt("eventSize"));
        event.setEventTypeId(rs.getInt("eventTypeId"));
        event.setId(UUID.fromString(rs.getString("id")));
        event.setVenueId(venueId != null ? UUID.fromString(venueId) : null);
        event.setArchived(rs.getBoolean("isArchived"));
        bidDto.setEvent(event);


        return bidDto;
    }
}
