package com.coordinator.api.users.event.service;

import com.coordinate.model.event.*;
import com.coordinator.api.users.event.mappers.EventServicePostRequestToEventDesiredServiceEntity;
import com.coordinator.api.users.event.mappers.ServiceDatePlaceToServiceDatePlaceEntity;
import com.coordinator.api.users.event.repository.IEventsRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

import static com.coordinator.api.users.event.mappers.EventPostRequestToEntityMapper.mapEventRequestToEntity;

@Service
public class EventImpl implements IEvent {
    @Autowired
    private IEventsRepository iEventsRepository;

    @Override
    public EventDto getEvent(UUID userID) {
        return iEventsRepository.getEvent(userID);
    }

    @Override
    public EventDto createEvent(UUID userId, EventPostRequest eventPostRequest) {
        boolean eventExists = iEventsRepository.getEvent(userId) != null;
        if (eventExists) {
            throw new NullPointerException(
                    "An event has already been registered for: "
                            +  userId);
        }

        ImmutableEventEntity eventEntity = mapEventRequestToEntity(eventPostRequest);

        iEventsRepository.createEvent(userId, eventEntity);

        return iEventsRepository.getEvent(userId);
    }

    @Override
    public void createEventService(UUID eventId, EventServicesPostRequest eventServicesPostRequest) {
        var desiredServiceId = UUID.randomUUID();
        var eventDesiredServiceEntity = EventServicePostRequestToEventDesiredServiceEntity.mapEventRequestToDesiredServiceEntity(desiredServiceId, eventServicesPostRequest);

        iEventsRepository.createEventDesiredService(eventDesiredServiceEntity);

        eventServicesPostRequest.getServiceDatePlace().forEach(floralServiceDatePlace -> saveServiceDatePlace(desiredServiceId, floralServiceDatePlace));

        iEventsRepository.addServicesToEvents(eventId, desiredServiceId);

    }

    private void saveServiceDatePlace(UUID desiredServiceId, ServiceDatePlace serviceDatePlace) {
        var serviceDatePlaceId = UUID.randomUUID();
        var eventDesiredServiceDataId = UUID.randomUUID();
        var serviceDatePlaceEntity = ServiceDatePlaceToServiceDatePlaceEntity.mapServiceDatePlaceToServiceDatePlaceEntity(desiredServiceId, serviceDatePlace);

        iEventsRepository.createServiceDatePlace(serviceDatePlaceId, serviceDatePlaceEntity);

        iEventsRepository.createEventDesiredServiceData(eventDesiredServiceDataId, serviceDatePlaceEntity.getServiceTypeId());

        iEventsRepository.addEventDesiredServiceServiceDatePlace(eventDesiredServiceDataId, serviceDatePlaceId);
    }




}
