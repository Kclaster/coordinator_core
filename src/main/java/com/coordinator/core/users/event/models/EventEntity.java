package com.coordinator.core.users.event.models;

import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.UUID;

@Value.Immutable
public abstract class EventEntity {
    public abstract UUID getEventId();
    public abstract @Nullable
    Date getEventDate();
    public abstract Integer getEventSize();
    public abstract Integer getEventTypeId();
    public abstract Integer getDesiredServiceId();
    public abstract String getAdditionalUserComments();
    public abstract @Nullable UUID getVenueId();
    public abstract String getDesiredState();
    public abstract String getDesiredCity();
    public abstract String getDesiredPostalCode();
    public abstract  @Nullable UUID getCoordinatorId();
    public abstract Boolean getIsArchived();
}
