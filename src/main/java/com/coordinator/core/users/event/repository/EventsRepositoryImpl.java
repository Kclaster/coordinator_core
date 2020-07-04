package com.coordinator.core.users.event.repository;

import com.coordinator.core.general.helpers.SqlHelper;
import com.coordinator.core.users.event.mappers.EventEntityToDtoMapper;
import com.coordinator.core.users.event.models.EventDto;
import com.coordinator.core.users.event.models.ImmutableEventEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
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
    public EventDto getEvent(UUID userId) {
        Map<String, Object> params = Map.of(
                "userId", userId
        );

        String sql = SqlHelper.sql("select-event");
        return namedParameterJdbcTemplate.queryForObject(
                sql,
                params,
                new EventEntityToDtoMapper()
        );
    }

    @Override
    public void createEvent(UUID userId, ImmutableEventEntity eventEntity) {
        String sql = SqlHelper.sql("insert-event");

        var params = new HashMap<String, Object>();
        params.put("eventId", eventEntity.getId());
        params.put("eventStartDate", eventEntity.getEventStartDate());
        params.put("eventEndDate", eventEntity.getEventEndDate());
        params.put("eventSize", eventEntity.getEventSize());
        params.put("eventTypeId", eventEntity.getEventTypeId());
        params.put("desiredServiceId", eventEntity.getDesiredServiceId());
        params.put("additionalUserComments", eventEntity.getAdditionalUserComments());
        params.put("desiredState", eventEntity.getDesiredState());
        params.put("desiredCity", eventEntity.getDesiredCity());
        params.put("desiredPostalCode", eventEntity.getDesiredPostalCode());
        params.put("venueId", eventEntity.getVenueId());
        params.put("coordinatorId", eventEntity.getCoordinatorId());
        params.put("userId", userId);

        namedParameterJdbcTemplate.update(sql, params);
    }
}