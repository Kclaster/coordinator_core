package com.coordinator.api.users.event.repository;

import com.coordinate.model.event.*;
import com.coordinator.api.general.main.helpers.SqlHelper;
import com.coordinator.api.users.event.mappers.EventEntityToDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

;

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
        List<EventDto> events = namedParameterJdbcTemplate.query(
                sql,
                params,
                new EventEntityToDtoMapper()
        );

        if (events.size() == 1) {
            return events.get(0);
        }

        return null;
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
        params.put("coordinatorId", eventEntity.getCoordinatorId());
        params.put("userId", userId);

        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public void addServicesToEvents(UUID eventId, UUID desiredServiceId) {
        String sql = SqlHelper.sql("update-event-add-event-desired-service");

        var params = new HashMap<String, Object>();
        params.put("eventId", eventId);
        params.put("eventDesiredServiceDataId", desiredServiceId);

        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public void addEventDesiredServiceServiceDatePlace(UUID eventDesiredServiceId, UUID serviceDatePlaceId) {
        String sql = SqlHelper.sql("insert-event-desired-service-service-date-place");

        var params = new HashMap<String, Object>();
        params.put("id", UUID.randomUUID());
        params.put("eventDesiredServiceId", eventDesiredServiceId);
        params.put("serviceDatePlaceId", serviceDatePlaceId);

        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public void createEventDesiredService(UUID eventId, ImmutableEventDesiredServiceEntity eventServicesPostRequest) {
        String sql = SqlHelper.sql("insert-event-desired-service");

        var params = new HashMap<String, Object>();
        params.put("eventDesiredServiceId", eventServicesPostRequest.getId());
        params.put("floral", eventServicesPostRequest.getFloral());
        params.put("dress", eventServicesPostRequest.getDress());
        params.put("partyAttire", eventServicesPostRequest.getPartyAttire());
        params.put("catering", eventServicesPostRequest.getCatering());
        params.put("security", eventServicesPostRequest.getSecurity());
        params.put("bar", eventServicesPostRequest.getBar());
        params.put("photographer", eventServicesPostRequest.getPhotographer());
        params.put("videographer", eventServicesPostRequest.getVideographer());
        params.put("musician", eventServicesPostRequest.getMusician());
        params.put("cosmetician", eventServicesPostRequest.getCosmetician());
        params.put("babySitter", eventServicesPostRequest.getBabySitter());
        params.put("other", eventServicesPostRequest.getOther());
        params.put("budgetPlanning", eventServicesPostRequest.getBudgetPlanning());
        params.put("cleanup", eventServicesPostRequest.getCleanup());
        params.put("seatingChart", eventServicesPostRequest.getSeatingChart());
        params.put("scheduling", eventServicesPostRequest.getScheduling());
        params.put("partyGifts", eventServicesPostRequest.getPartyGifts());
        params.put("invitationsAndResponses", eventServicesPostRequest.getInvitationsAndResponses());
        params.put("eventId", eventId);


        namedParameterJdbcTemplate.update(sql, params);
    }

    public void createEventDesiredServiceData(UUID eventDesiredServiceDataID, Integer serviceTypeId) {
        String sql = SqlHelper.sql("insert-event-desired-service-data");

        var params = new HashMap<String, Object>();
        params.put("id", eventDesiredServiceDataID);
        params.put("serviceTypeId", serviceTypeId);

        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public void createServiceDatePlace(UUID serviceDatePlaceId, ImmutableServiceDatePlaceEntity serviceDatePlaceEntity) {
        String sql = SqlHelper.sql("insert-service-date-place");

        var params = new HashMap<String, Object>();
        params.put("serviceDatePlaceId", serviceDatePlaceId);
        params.put("address", serviceDatePlaceEntity.getAddress());
        params.put("zipCode", serviceDatePlaceEntity.getZipCode());
        params.put("title", serviceDatePlaceEntity.getTitle());
        params.put("contactPhoneNumber", serviceDatePlaceEntity.getContactPhoneNumber());
        params.put("serviceDate", serviceDatePlaceEntity.getServiceDate());
        params.put("isSelected", serviceDatePlaceEntity.getIsSelected());
        params.put("serviceTypeId", serviceDatePlaceEntity.getServiceTypeId());

        namedParameterJdbcTemplate.update(sql, params);
    }
}