package com.coordinator.api.users.event.mappers;

import com.coordinate.model.event.EventDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

public class EventEntityToDtoMapper implements RowMapper<EventDto> {
    public EventDto mapRow(ResultSet rs, int i) throws SQLException {
        Timestamp eventStartDate = rs.getTimestamp("eventStartDate");
        Timestamp eventEndDate = rs.getTimestamp("eventEndDate");
        String coordinatorId = rs.getString("coordinatorId");
        String venueId = rs.getString("venueId");

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


        return event;
    }
}
