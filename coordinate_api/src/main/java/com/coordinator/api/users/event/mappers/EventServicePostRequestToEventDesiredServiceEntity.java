package com.coordinator.api.users.event.mappers;

import com.coordinate.model.event.EventServicesPostRequest;
import com.coordinate.model.event.ImmutableEventDesiredServiceEntity;

import java.util.UUID;

public class EventServicePostRequestToEventDesiredServiceEntity {
    public static ImmutableEventDesiredServiceEntity mapEventRequestToDesiredServiceEntity(UUID desiredServiceId, EventServicesPostRequest eventServicesPostRequest) {
        return ImmutableEventDesiredServiceEntity.builder()
                .id(desiredServiceId)
                .floral(eventServicesPostRequest.getShouldProvideFloral())
                .dress(eventServicesPostRequest.getShouldProvideDress())
                .partyAttire(eventServicesPostRequest.getShouldProvidePartyAttire())
                .catering(eventServicesPostRequest.getShouldProvideCatering())
                .security(eventServicesPostRequest.getShouldProvideSecurity())
                .bar(eventServicesPostRequest.getShouldProvideBar())
                .photographer(eventServicesPostRequest.getShouldProvidePhotographer())
                .videographer(eventServicesPostRequest.getShouldProvideVideographer())
                .musician(eventServicesPostRequest.getShouldProvideMusician())
                .cosmetician(eventServicesPostRequest.getShouldProvideCosmetician())
                .babySitter(eventServicesPostRequest.getShouldProvideBabySitter())
                .other(eventServicesPostRequest.getShouldProvideOther())
                .budgetPlanning(eventServicesPostRequest.getShouldProvideBudgetPlanning())
                .cleanup(eventServicesPostRequest.getShouldProvideCleanup())
                .seatingChart(eventServicesPostRequest.getShouldProvideSeatingChart())
                .scheduling(eventServicesPostRequest.getShouldProvideScheduling())
                .partyGifts(eventServicesPostRequest.getShouldProvidePartyGifts())
                .invitationsAndResponses(eventServicesPostRequest.getShouldProvideInvitationsAndResponses())
                .build();
    }
}
