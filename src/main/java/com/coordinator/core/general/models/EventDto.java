package com.coordinator.core.general.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.UUID;

public class EventDto {
    private UUID id;
    private Instant eventDate;
    private Integer eventSize;
    private Integer eventTypeId;
    private Integer desiredServiceId;
    private String additionalUserComments;
    private UUID venueId;
    private String desiredState;
    private String desiredCity;
    private String desiredPostalCode;
    private UUID coordinatorId;
    private boolean isArchived;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Instant getEventDate() {
        return eventDate;
    }

    public void setEventDate(Instant eventDate) {
        this.eventDate = eventDate;
    }

    public Integer getEventSize() {
        return eventSize;
    }

    public void setEventSize(Integer eventSize) {
        this.eventSize = eventSize;
    }

    public Integer getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(Integer eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public Integer getDesiredServiceId() {
        return desiredServiceId;
    }

    public void setDesiredServiceId(Integer desiredServiceId) {
        this.desiredServiceId = desiredServiceId;
    }

    public String getAdditionalUserComments() {
        return additionalUserComments;
    }

    public void setAdditionalUserComments(String additionalUserComments) {
        this.additionalUserComments = additionalUserComments;
    }

    public UUID getVenueId() {
        return venueId;
    }

    public void setVenueId(UUID venueId) {
        this.venueId = venueId;
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

    public UUID getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(UUID coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    @JsonProperty(value="isArchived")
    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }
}
