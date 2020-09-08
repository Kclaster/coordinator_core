package com.coordinate.model.event;

import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.UUID;

@Value.Immutable
public abstract class ServiceDatePlaceEntity {
    public abstract UUID getId();
    public abstract @Nullable String getAddress();
    public abstract @Nullable String getZipCode();
    public abstract @Nullable String getTitle();
    public abstract @Nullable String getContactPhoneNumber();
    public abstract @Nullable Date getServiceDate();
    public abstract Integer getServiceTypeId();

    @Value.Default
    public boolean getIsSelected() {
        return false;
    }
}
