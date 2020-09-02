package com.coordinator.api.general.main.mappers;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowExistsToBooleanMapper implements RowMapper<Boolean> {
    public Boolean mapRow(ResultSet rs, int i) throws SQLException {
        boolean hasRow = rs.getBoolean("hasRow");

        return hasRow;
    }
}
