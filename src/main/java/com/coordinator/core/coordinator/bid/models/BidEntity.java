package com.coordinator.core.coordinator.bid.models;

import com.coordinator.core.coordinator.bid.constants.BidStatusTypes;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.UUID;

@Value.Immutable
public abstract class BidEntity {
    public abstract UUID getId();
    public abstract  UUID getEventId();
    public abstract @Nullable UUID getCoordinatorId();
    public abstract Integer getBidAmount();

    @Value.Default
    public String getMessageToUser() {
        return "";
    }

    @Value.Default
    public int getBidStatusId() {
        return BidStatusTypes.NEW;
    }

}
