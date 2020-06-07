package com.coordinator.core.repository.core;

import com.coordinator.core.mappers.CoordinatorMapper;
import com.coordinator.core.models.CoordinatorDto;
import com.coordinator.core.repository.SqlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;

@Repository
public class CoordinatorsRepositoryImpl implements ICoordinatorsRepository {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CoordinatorsRepositoryImpl(@Qualifier("coreJdbcTemplate")org.springframework.jdbc.core.JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CoordinatorDto getCoordinator(UUID coordinatorId) {
        NamedParameterJdbcTemplate namedParameters = new NamedParameterJdbcTemplate(jdbcTemplate);
        Map<String, Object> params = Map.of(
                "coordinator_id", coordinatorId
        );

        String sql = SqlHelper.sql("select-coordinator");
        return namedParameters.queryForObject(
                sql,
                params,
                new CoordinatorMapper()
        );
    }
}
