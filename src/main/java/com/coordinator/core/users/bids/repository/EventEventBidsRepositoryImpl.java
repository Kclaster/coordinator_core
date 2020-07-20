package com.coordinator.core.users.bids.repository;

import com.coordinator.core.general.main.helpers.SqlHelper;
import com.coordinator.core.general.main.models.QueryOptions;
import com.coordinator.core.users.bids.mappers.BidEntityToDtoMapper;
import com.coordinator.core.users.bids.models.BidDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class EventEventBidsRepositoryImpl implements IEventBidsRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public EventEventBidsRepositoryImpl(@Qualifier("coreJdbcTemplate")org.springframework.jdbc.core.JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public List<BidDto> getAllEventBids(UUID eventId, QueryOptions queryOptions) {
        NamedParameterJdbcTemplate namedParameters = new NamedParameterJdbcTemplate(jdbcTemplate);
        Map<String, Object> params = Map.of(
                "eventId", eventId,
                "status_id", queryOptions.getStatusId(),
                "query", queryOptions.getQuery(),
                "sortAsc", queryOptions.getIsAscending(),
                "sortField", queryOptions.getSortField()
        );
        String sql = SqlHelper.sql("select-all-bids-by-event", queryOptions);

        return namedParameters.query(
                sql,
                params,
                new BidEntityToDtoMapper()
        );
    }
}
