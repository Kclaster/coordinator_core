package com.coordinator.api.general.zipcodes.repository;

import com.coordinator.api.general.main.helpers.SqlHelper;
import com.coordinator.api.general.main.mappers.RowExistsToBooleanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class ZipCodeRepositoryImpl implements IZipCodeRepository {
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ZipCodeRepositoryImpl(@Qualifier("coreJdbcTemplate")org.springframework.jdbc.core.JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }


    @Override
    public Boolean zipCodeExists(String zipcode) {
        Map<String, Object> params = Map.of(
                "zipCode", zipcode
        );
        String sql = SqlHelper.sql("check-exists-zip-code");

        List<Boolean> hasRow = namedParameterJdbcTemplate.query(
                sql,
                params,
                new RowExistsToBooleanMapper()
        );

        if (hasRow.size() == 1) {
            return hasRow.get(0);
        }

        return false;
    }

    @Override
    public void createZipCode(UUID zipCodeId, String zipCode) {
        String sql = SqlHelper.sql("insert-zip-code");

        var params = new HashMap<String, Object>();
        params.put("zipCodeId", zipCodeId);
        params.put("zipCode", zipCode);

        namedParameterJdbcTemplate.update(sql, params);
    }
}
