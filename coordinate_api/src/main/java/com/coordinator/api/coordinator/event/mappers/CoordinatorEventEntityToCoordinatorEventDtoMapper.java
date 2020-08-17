package com.coordinator.api.coordinator.event.mappers;

import com.coordinate.model.coordinator.BidDto;
import com.coordinate.model.coordinator.CoordinatorEventDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

public class CoordinatorEventEntityToCoordinatorEventDtoMapper implements RowMapper<CoordinatorEventDto> {
    public CoordinatorEventDto mapRow(ResultSet rs, int i) throws SQLException {
        Timestamp eventStartDate = rs.getTimestamp("eventStartDate");
        Timestamp eventEndDate = rs.getTimestamp("eventEndDate");
        String coordinatorId = rs.getString("coordinatorId");
        String venueId = rs.getString("venueId");
        String bidId = rs.getString("bidId");

        CoordinatorEventDto event = new CoordinatorEventDto();
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
        event.setEventId(UUID.fromString(rs.getString("eventId")));
        event.setVenueId(venueId != null ? UUID.fromString(venueId) : null);

        if (bidId != null) {
            BidDto bidDto = new BidDto();

            bidDto.setBidAmount(rs.getInt("bidAmount"));
            bidDto.setMessageToUser(rs.getString("messageToUser"));
            bidDto.setBidStatusId(rs.getInt("bidStatusId"));
            bidDto.setId(UUID.fromString(rs.getString("bidId")));

            event.setBid(bidDto);
        }

        return event;
    }
}
