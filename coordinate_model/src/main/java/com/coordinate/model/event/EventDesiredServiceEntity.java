package com.coordinate.model.event;

import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.UUID;

@Value.Immutable
public abstract class EventDesiredServiceEntity {
    public abstract UUID getId();
    public abstract Boolean getFloral();
    public abstract Boolean getDress();
    public abstract Boolean getPartyAttire();
    public abstract Boolean getCatering();
    public abstract Boolean getSecurity();
    public abstract Boolean getBar();
    public abstract Boolean getPhotographer();
    public abstract Boolean getVideographer();
    public abstract Boolean getMusician();
    public abstract Boolean getCosmetician();
    public abstract Boolean getBabySitter();
    public abstract Boolean getOther();
    public abstract Boolean getBudgetPlanning();
    public abstract Boolean getCleanup();
    public abstract Boolean getSeatingChart();
    public abstract Boolean getScheduling();
    public abstract Boolean getPartyGifts();
    public abstract Boolean getInvitationsAndResponses();
}
