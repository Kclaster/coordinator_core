package com.coordinator.core.general.mappers;

import com.coordinator.core.general.models.BaseDto;

import java.util.UUID;

public class BaseEntityToDtoMapper {
    public static BaseDto mapEntityToDto(UUID id) {
        BaseDto baseDto = new BaseDto();
        baseDto.setId(id.toString());

        return baseDto;
    };
}
