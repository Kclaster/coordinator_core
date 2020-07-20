package com.coordinator.core.users.main.repository;

import com.coordinator.core.general.main.helpers.SqlHelper;
import com.coordinator.core.general.main.mappers.BaseEntityToBaseDtoMapper;
import com.coordinator.core.general.main.models.BaseDto;
import com.coordinator.core.users.main.mappers.UserEntityToDtoMapper;
import com.coordinator.core.users.main.models.ImmutableUserEntity;
import com.coordinator.core.users.main.models.UserDto;
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
public class UserRepositoryImpl implements IUserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Autowired
    public UserRepositoryImpl(@Qualifier("coreJdbcTemplate") JdbcTemplate jdbcTemplate,
                              NamedParameterJdbcTemplate namedParameterJdbcTemplate
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public UserDto getUser(UUID userId) {
        Map<String, Object> params = Map.of(
                "userId", userId
        );

        String sql = SqlHelper.sql("select-user");
        return namedParameterJdbcTemplate.queryForObject(
                sql,
                params,
                new UserEntityToDtoMapper()
        );
    }

    @Override
    public BaseDto getUserFromAuthUserId(UUID authUserId) {
        Map<String, Object> params = Map.of(
                "authUserId", authUserId
        );

        String sql = SqlHelper.sql("select-user-from-auth-user-id");
        return namedParameterJdbcTemplate.queryForObject(
                sql,
                params,
                new BaseEntityToBaseDtoMapper()
        );
    }

    @Override
    public List<UserDto> getAllUsers() {

        return jdbcTemplate.query(
                SqlHelper.sql(
                        "select-all-users"),
                        new UserEntityToDtoMapper()
        );
    }

    @Override
    public void createUser(ImmutableUserEntity immutableUserEntity) {
        String sql = SqlHelper.sql("insert-user");

        var params = new HashMap<String, Object>();
        params.put("userId", immutableUserEntity.getId());
        params.put("authUserId", immutableUserEntity.getAuthUserId());
        params.put("contactEmail", immutableUserEntity.getContactEmail());

        namedParameterJdbcTemplate.update(sql, params);
    }
}
