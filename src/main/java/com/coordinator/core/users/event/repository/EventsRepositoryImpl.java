package com.coordinator.core.users.event.repository;

import com.coordinator.core.general.helpers.SqlHelper;
import com.coordinator.core.users.event.models.ImmutableEventEntity;
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
    public void createEvent(UUID userId, ImmutableEventEntity eventEntity) {
        String sql = SqlHelper.sql("insert-event");

        Map<String, Object> params = Map.of(
                "eventId", eventEntity.getEventId(),
                "eventDate", eventEntity.getEventDate(),
                "eventSize", eventEntity.getEventSize(),
                "eventTypeId", eventEntity.getEventTypeId(),
                "desiredServiceId", eventEntity.getDesiredServiceId(),
                "additionalUserComments", eventEntity.getAdditionalUserComments(),
                "desiredState", eventEntity.getDesiredState(),
                "desiredCity", eventEntity.getDesiredCity(),
                "desiredPostalCode", eventEntity.getDesiredPostalCode(),
                "venueId", eventEntity.getVenueId()
        );
        namedParameterJdbcTemplate.update(sql, params);
    }
}