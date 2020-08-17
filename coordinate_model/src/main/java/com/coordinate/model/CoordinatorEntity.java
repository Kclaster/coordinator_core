package com.coordinate.model;

import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public abstract class CoordinatorEntity {
    public abstract UUID getId();
    public abstract UUID getAuthUserId();

    @Value.Default
    public String getTitle() {
        return "";
    }

    @Value.Default
    public String getOfficeState() {
        return "";
    }

    @Value.Default
    public String getOfficeCity() {
        return "";
    }

    @Value.Default
    public String getOfficeAddress() {
        return "";
    }

    @Value.Default
    public String getOfficePostalCode() {
        return "";
    }

    @Value.Default
    public String getContactEmail() {
        return "";
    }

    @Value.Default
    public Integer getMaximumDistanceToClient() {
        return 0;
    }

    @Value.Default
    public Integer getLevelOneDefaultBid() {
        return 0;
    }

    @Value.Default
    public Integer getLevelTwoDefaultBid() {
        return 0;
    }

    @Value.Default
    public Integer getLevelThreeDefaultBid() {
        return 0;
    }


}
