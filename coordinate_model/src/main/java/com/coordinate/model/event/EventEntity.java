package com.coordinate.model.event;

import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.UUID;

@Value.Immutable
public abstract class EventEntity {
    public abstract UUID getId();
    public abstract Integer getEventTypeId();
    public abstract String getDesiredState();
    public abstract String getDesiredCity();
    public abstract String getDesiredPostalCode();
    public abstract @Nullable Date getEventStartDate();
    public abstract @Nullable Date getEventEndDate();
    public abstract @Nullable UUID getVenueId();
    public abstract @Nullable UUID getCoordinatorId();

    @Value.Default
    public int getDesiredServiceId() {
        return 0;
    }

    @Value.Default
    public int getEventSize() {
        return 0;
    }

    @Value.Default
    public String getAdditionalUserComments() {
        return "";
    }

    @Value.Default
    public Boolean getIsArchived() {
        return false;
    }
}
