package com.coordinator.core.mappers;

import com.coordinator.core.models.BidDto;
import com.coordinator.core.models.EventDto;
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

        EventDto event = new EventDto();
        event.setDesiredPostalCode(rs.getString("desiredPostalCode"));
        event.setDesiredCity(rs.getString("desiredCity"));
        event.setAdditionalUserComments(rs.getString("additionalUserComments"));
        event.setCoordinatorId(UUID.fromString(rs.getString("coordinatorId")));
        event.setDesiredServiceId(rs.getInt("desiredServiceId"));
        event.setDesiredState(rs.getString("desiredState"));
        event.setEventDate(rs.getTimestamp("eventDate").toInstant());
        event.setEventSize(rs.getInt("eventSize"));
        event.setEventTypeId(rs.getInt("eventTypeId"));
        event.setId(UUID.fromString(rs.getString("id")));
        event.setVenueId(UUID.fromString(rs.getString("venueId")));
        bidDto.setEvent(event);


        return bidDto;
    }
}
