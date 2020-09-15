package com.coordinator.api.users.event.service;

import com.coordinate.model.event.*;
import com.coordinator.api.users.event.mappers.EventServicePostRequestToEventDesiredServiceEntity;
import com.coordinator.api.users.event.mappers.ServiceDatePlaceToServiceDatePlaceEntity;
import com.coordinator.api.users.event.repository.IEventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        // TODO: serviceTypeId is not being populated in the service_date_place
        var eventDesiredServiceEntity = EventServicePostRequestToEventDesiredServiceEntity.mapEventRequestToDesiredServiceEntity(eventServicesPostRequest);

        eventServicesPostRequest.getServiceDatePlace().forEach(floralServiceDatePlace -> saveServiceDatePlace(eventId, floralServiceDatePlace));

        iEventsRepository.createEventDesiredService(eventId, eventDesiredServiceEntity);
    }

    private void saveServiceDatePlace(UUID eventId, ServiceDatePlace serviceDatePlace) {
        var serviceDatePlaceId = UUID.randomUUID();
        var eventDesiredServiceDataId = UUID.randomUUID();
        var serviceDatePlaceEntity = ServiceDatePlaceToServiceDatePlaceEntity.mapServiceDatePlaceToServiceDatePlaceEntity(serviceDatePlace);

        iEventsRepository.createServiceDatePlace(serviceDatePlaceId, serviceDatePlaceEntity);

        iEventsRepository.createEventDesiredServiceData(eventDesiredServiceDataId, serviceDatePlaceEntity.getServiceTypeId());

        iEventsRepository.addServicesToEvents(eventId, eventDesiredServiceDataId);

        iEventsRepository.addEventDesiredServiceServiceDatePlace(eventDesiredServiceDataId, serviceDatePlaceId);
    }

}
