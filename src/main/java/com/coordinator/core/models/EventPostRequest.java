package com.coordinator.core.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class EventPostRequest {
    private UUID eventId;

    private String eventDate;

    private Integer eventSize;

    @NotNull
    private Integer eventTypeId;

    private Integer desiredServiceId;

    private String additionalUserComments;

    private UUID venueId;

    @NotNull
    @NotEmpty
    private String desiredState;

    @NotNull
    @NotEmpty
    private String desiredCity;

    @NotNull
    @NotEmpty
    private String desiredPostalCode;

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
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
}
