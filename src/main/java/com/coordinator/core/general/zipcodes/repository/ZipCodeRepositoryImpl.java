package com.coordinator.core.general.zipcodes.repository;

import com.coordinator.core.general.main.helpers.SqlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Map;

public class ZipCodeRepositoryImpl {
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ZipCodeRepositoryImpl(@Qualifier("coreJdbcTemplate")org.springframework.jdbc.core.JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }


    public Boolean zipCodeExists(String zipcode) {
        Map<String, Object> params = Map.of(
                "zipCode", zipcode
        );
        String sql = SqlHelper.sql("check-exists-zip-code");

        return namedParameterJdbcTemplate.query(
                sql,
                params,
                new 
        )

    }
}
