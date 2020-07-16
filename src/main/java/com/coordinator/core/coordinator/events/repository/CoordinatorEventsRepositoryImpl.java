package com.coordinator.core.coordinator.events.repository;

import com.coordinator.core.coordinator.events.mappers.CoordinatorEventEntityToCoordinatorEventDtoMapper;
import com.coordinator.core.coordinator.events.models.CoordinatorEventDto;
import com.coordinator.core.general.main.helpers.SqlHelper;
import com.coordinator.core.general.main.models.QueryOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class CoordinatorEventsRepositoryImpl implements ICoordinatorEventsRepository {
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    private static final int DEFAULT_BID_STATUS_ID = 1;


    @Autowired
    public CoordinatorEventsRepositoryImpl(@Qualifier("coreJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CoordinatorEventDto> getAllCoordinatorEventsByZipCode(UUID coordinatorId, QueryOptions queryOptions) {
        NamedParameterJdbcTemplate namedParameters = new NamedParameterJdbcTemplate(jdbcTemplate);

        Map<String, Object> params = Map.of(
                "coordinatorId", coordinatorId,
                "status_id", queryOptions.getStatusId(),
                "default_status_id", DEFAULT_BID_STATUS_ID,
                "query", queryOptions.getQuery(),
                "sortAsc", queryOptions.getIsAscending(),
                "sortField", queryOptions.getSortField()
        );
        String sql = SqlHelper.sql("select-all-events-and-bids-for-coordinator-by-zipcode", queryOptions);
        return namedParameters.query(
                sql,
                params,
                new CoordinatorEventEntityToCoordinatorEventDtoMapper()
        );
    }
}
