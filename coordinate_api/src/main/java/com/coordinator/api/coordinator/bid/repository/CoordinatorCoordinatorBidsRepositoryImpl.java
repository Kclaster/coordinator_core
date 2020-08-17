package com.coordinator.api.coordinator.bid.repository;

import com.coordinate.model.QueryOptions;
import com.coordinate.model.bids.ImmutableBidEntity;
import com.coordinate.model.coordinator.BidDto;
import com.coordinator.api.coordinator.bid.mappers.BidEntityToDtoMapper;
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
public class CoordinatorCoordinatorBidsRepositoryImpl implements ICoordinatorBidsRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final int DEFAULT_BID_STATUS_ID = 1;

    @Autowired
    public CoordinatorCoordinatorBidsRepositoryImpl(
            @Qualifier("coreJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
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
                new BidEntityToDtoMapper()
        );
    }

    @Override
    public Boolean bidExists(UUID coordinatorId, UUID eventId) {
        Map<String, Object> params = Map.of(
                "coordinatorId", coordinatorId,
                "eventId", eventId
        );
        String sql = SqlHelper.sql("check-exists-bid");

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
    public void createBid(UUID coordinatorId, ImmutableBidEntity bidEntity) {
        String sql = SqlHelper.sql("insert-bid");

        var params = new HashMap<String, Object>();
        params.put("bidId", bidEntity.getId());
        params.put("messageToUser", bidEntity.getMessageToUser());
        params.put("eventId", bidEntity.getEventId());
        params.put("coordinatorId", coordinatorId);
        params.put("bidAmount", bidEntity.getBidAmount());
        params.put("bidStatusId", bidEntity.getBidStatusId());

        namedParameterJdbcTemplate.update(sql, params);
    }
}
