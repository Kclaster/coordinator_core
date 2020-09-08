package com.coordinator.api.users.event.mappers;

import com.coordinate.model.event.ImmutableServiceDatePlaceEntity;
import com.coordinate.model.event.ServiceDatePlace;

import java.sql.Timestamp;
import java.util.UUID;

public class ServiceDatePlaceToServiceDatePlaceEntity {
    public static ImmutableServiceDatePlaceEntity mapServiceDatePlaceToServiceDatePlaceEntity(UUID desiredServiceId, ServiceDatePlace serviceDatePlace) {
        var serviceDate = serviceDatePlace.getServiceDate();

        return ImmutableServiceDatePlaceEntity.builder()
                .id(desiredServiceId)
                .address(serviceDatePlace.getAddress())
                .zipCode(serviceDatePlace.getZipCode())
                .title(serviceDatePlace.getTitle())
                .contactPhoneNumber(serviceDatePlace.getContactPhoneNumber())
                .serviceDate(serviceDate != null ? new Timestamp(serviceDate) : null)
                .serviceTypeId(serviceDatePlace.getServiceTypeId())
            .build();
}
}
