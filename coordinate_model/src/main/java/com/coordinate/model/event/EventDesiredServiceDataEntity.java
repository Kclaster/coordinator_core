package com.coordinate.model.event;

import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public abstract class EventDesiredServiceDataEntity {
    public abstract UUID getId();
    public abstract Integer getServiceTypeId();
}
