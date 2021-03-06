package com.coordinator.api.general.main.mappers;

import com.coordinate.model.BaseDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseEntityToBaseDtoMapper implements RowMapper<BaseDto> {
    public BaseDto mapRow(ResultSet rs, int i) throws SQLException {
        BaseDto baseDto = new BaseDto();
        baseDto.setId(rs.getString("id"));
        return baseDto;
    }
}
