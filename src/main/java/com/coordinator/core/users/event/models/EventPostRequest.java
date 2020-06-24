package com.coordinator.core.users.event.models;


import org.immutables.value.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value.Immutable
public class EventPostRequest {
    private UUID id;
    @NotNull
    private Integer eventTypeId;
    @NotNull
    @NotEmpty
    private String desiredState;
    @NotNull
    @NotEmpty
    private String desiredCity;
    @NotNull
    @NotEmpty
    private String desiredPostalCode;
    private String eventDate;
    private UUID venueId;
    private UUID coordinatorId;
    private int desiredServiceId;
    private int eventSize;
    private String additionalUserComments;
    private Boolean isArchived;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(Integer eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public String getDesiredState() {
        return desiredState;
    }

    public void setDesiredState(String desiredState) {
        this.desiredState = desiredState;
    }

    public String getDesiredCity() {
        return desiredCity;
    }

    public void setDesiredCity(String desiredCity) {
        this.desiredCity = desiredCity;
    }

    public String getDesiredPostalCode() {
        return desiredPostalCode;
    }

    public void setDesiredPostalCode(String desiredPostalCode) {
        this.desiredPostalCode = desiredPostalCode;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public UUID getVenueId() {
        return venueId;
    }

    public void setVenueId(UUID venueId) {
        this.venueId = venueId;
    }

    public UUID getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(UUID coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    public int getDesiredServiceId() {
        return desiredServiceId;
    }

    public void setDesiredServiceId(int desiredServiceId) {
        this.desiredServiceId = desiredServiceId;
    }

    public int getEventSize() {
        return eventSize;
    }

    public void setEventSize(int eventSize) {
        this.eventSize = eventSize;
    }

    public String getAdditionalUserComments() {
        return additionalUserComments;
    }

    public void setAdditionalUserComments(String additionalUserComments) {
        this.additionalUserComments = additionalUserComments;
    }

    public Boolean getArchived() {
        return isArchived;
    }

    public void setArchived(Boolean archived) {
        isArchived = archived;
    }
}