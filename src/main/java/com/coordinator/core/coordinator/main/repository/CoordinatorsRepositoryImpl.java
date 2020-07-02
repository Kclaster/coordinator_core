package com.coordinator.core.coordinator.main.repository;

import com.coordinator.core.coordinator.main.mappers.CoordinatorEntityToDtoMapper;
import com.coordinator.core.coordinator.main.models.CoordinatorDto;
import com.coordinator.core.coordinator.main.models.CoordinatorPutRequest;
import com.coordinator.core.general.helpers.SqlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class CoordinatorsRepositoryImpl implements ICoordinatorsRepository {
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CoordinatorsRepositoryImpl(@Qualifier("coreJdbcTemplate")org.springframework.jdbc.core.JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public CoordinatorDto getCoordinator(UUID coordinatorId) {
        Map<String, Object> params = Map.of(
                "coordinator_id", coordinatorId
        );

        String sql = SqlHelper.sql("select-coordinator");
        return namedParameterJdbcTemplate.queryForObject(
                sql,
                params,
                new CoordinatorEntityToDtoMapper()
        );
    }

    @Override
    public void updateArchiveCoordinator(UUID coordinatorId) throws IllegalStateException {
        Map<String, Object> params = Map.of(
                "element_id", coordinatorId
        );
        String sql = SqlHelper.sqlUpdateTable("archive-element", "coordinators");
        int numberOfRowsUpdated = namedParameterJdbcTemplate.update(
                sql,
                params
        );
        if(numberOfRowsUpdated == 0){
            throw new IllegalStateException("No Coordinator with id " + coordinatorId + " found.");
        }
    }

    @Override
    public void createCoordinator(UUID coordinatorId, String username) {
        String sql = SqlHelper.sql("insert-coordinator");
        Map<String, Object> params = Map.of(
             "coordinatorId", coordinatorId,
             "username", username
        );
        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public void updateCoordinator(UUID coordinatorId, CoordinatorPutRequest coordinatorPutRequest)  throws IllegalStateException {
        String sql = SqlHelper.sql("update-coordinator");
        var params = new HashMap<String, Object>();
        params.put("coordinatorId", coordinatorId);
        params.put("title", coordinatorPutRequest.getTitle());
        params.put("officeState", coordinatorPutRequest.getOfficeState());
        params.put("officeAddress", coordinatorPutRequest.getOfficeAddress());
        params.put("officePostalCode", coordinatorPutRequest.getOfficePostalCode());
        params.put("officeCity", coordinatorPutRequest.getOfficeCity());
        params.put("contactEmail", coordinatorPutRequest.getContactEmail());
        params.put("maximumDistanceToClient", coordinatorPutRequest.getMaximumDistanceToClient());
        params.put("levelOneDefaultBid", coordinatorPutRequest.getLevelOneDefaultBid());
        params.put("levelTwoDefaultBid", coordinatorPutRequest.getLevelTwoDefaultBid());
        params.put("levelThreeDefaultBid", coordinatorPutRequest.getLevelThreeDefaultBid());
        params.put("username", coordinatorPutRequest.getUsername());

        int numberOfRowsUpdated = namedParameterJdbcTemplate.update(sql, params);

        if (numberOfRowsUpdated == 0) {
            throw new IllegalStateException("No coordinator found for id: " + coordinatorId);
        }
    }
}
