package com.coordinator.core.repository.core;

import com.coordinator.core.mappers.BidMapper;
import com.coordinator.core.models.BidDto;
import com.coordinator.core.models.QueryOptions;
import com.coordinator.core.repository.SqlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class BidsRepositoryImpl implements IBidsRepository {
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    private static final int DEFAULT_BID_STATUS_ID = 1;

    @Autowired
    public BidsRepositoryImpl(@Qualifier("coreJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<BidDto> getAllCoordinatorsBids(UUID coordinatorId,  QueryOptions queryOptions) {
        NamedParameterJdbcTemplate namedParameters = new NamedParameterJdbcTemplate(jdbcTemplate);
        Map<String, Object> params = Map.of(
                "coordinator_id", coordinatorId,
                "status_id", queryOptions.getStatusId(),
                "default_status_id", DEFAULT_BID_STATUS_ID,
                "query", queryOptions.getQuery(),
                "sortAsc", queryOptions.getIsAscending(),
                "sortField", queryOptions.getSortField()
        );
        String sql = SqlHelper.sql("select-all-coordinators-bids", queryOptions);
        return namedParameters.query(
                sql,
                params,
                new BidMapper()
        );
    }
}
