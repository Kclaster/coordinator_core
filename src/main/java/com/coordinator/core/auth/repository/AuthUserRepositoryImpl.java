package com.coordinator.core.auth.repository;

import com.coordinator.core.auth.mappers.AuthUserEntityToDtoMapper;
import com.coordinator.core.auth.models.AuthUserDto;
import com.coordinator.core.auth.models.AuthUserRequest;
import com.coordinator.core.general.helpers.SqlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class AuthUserRepositoryImpl implements IAuthUserRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthUserRepositoryImpl(@Qualifier("coreJdbcTemplate") JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Optional<AuthUserDto> selectApplicationUserByUsername(String username) {
        String sql = SqlHelper.sql("select-auth-user");
        List<AuthUserDto> authUserEntities = jdbcTemplate.query(
                sql,
                new AuthUserEntityToDtoMapper(),
                username
                );
        if (authUserEntities.size() == 1) {
            return Optional.of(authUserEntities.get(0));
        }
        return null;
    }

    @Override
    public UUID saveAuthUser(AuthUserRequest authUserRequest) throws  Exception {
        String sql = SqlHelper.sql("insert-auth-user");
        UUID newAuthUserId = UUID.randomUUID();

        Map<String, Object> params = Map.of(
                "id", newAuthUserId,
                "username", authUserRequest.getUsername(),
                "password", authUserRequest.getPassword(),
                "authUserRoleId", authUserRequest.getRoleId()
        );
        try {
            namedParameterJdbcTemplate.update(sql, params);

            return newAuthUserId;
        } catch (Exception e) {
            throw e;
        }

    }
}
