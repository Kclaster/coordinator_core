package com.coordinator.core.repository.core;

import com.coordinator.core.models.EventPostRequest;
import com.coordinator.core.repository.SqlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;

@Repository
public class EventsRepositoryImpl implements IEventsRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public EventsRepositoryImpl(@Qualifier("coreJdbcTemplate")org.springframework.jdbc.core.JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public void createEvent(UUID userId, EventPostRequest eventPostRequest) {
        String sql = SqlHelper.sql("insert-event");

        Map<String, Object> params = Map.of(
                "eventId", UUID.randomUUID(),
                "eventDate", eventPostRequest.getEventDate(),
                "eventSize", eventPostRequest.getEventSize(),
                "desiredServiceId", eventPostRequest.getDesiredServiceId(),
                "additionalUserComments", eventPostRequest.getAdditionalUserComments(),
                "venueId", eventPostRequest.getVenueId(),
                "desiredState", eventPostRequest.getDesiredState(),
                "desiredCity", eventPostRequest.getDesiredCity(),
                "desiredPostalCode", eventPostRequest.getDesiredPostalCode()
        );
        namedParameterJdbcTemplate.update(sql, params);
    }
}
