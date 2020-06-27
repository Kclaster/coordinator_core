package com.coordinator.core.auth.repository;

import com.coordinator.core.auth.mappers.AuthUserEntityToDtoMapper;
import com.coordinator.core.auth.models.AuthUserDto;
import com.coordinator.core.auth.models.AuthUserRequest;
import com.coordinator.core.general.helpers.SqlHelper;
import com.coordinator.core.users.models.ImmutableUserEntity;
import com.coordinator.core.users.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.coordinator.core.users.mappers.UserPostRequestToEntityMapper.mapUserRequestToEntity;

@Repository("postgres")
public class AuthUserRepositoryImpl implements IAuthUserRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;
    private final IUserRepository iUserRepository;

    @Autowired
    public AuthUserRepositoryImpl(@Qualifier("coreJdbcTemplate") JdbcTemplate jdbcTemplate,
                                  NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                                  IUserRepository iUserRepository
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.iUserRepository = iUserRepository;
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
    public void saveAuthUser(AuthUserRequest authUserRequest) throws  Exception {
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
        } catch (Exception e) {
            throw e;
        }

        // If ROLE_USER
        if (authUserRequest.getRoleId() == 3) {
            ImmutableUserEntity immutableUserEntity = mapUserRequestToEntity(newAuthUserId, authUserRequest.getUsername());
            iUserRepository.createUser(immutableUserEntity);
        }
    }
}
