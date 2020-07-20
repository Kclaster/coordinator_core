package com.coordinator.core.auth.repository;

import com.coordinator.core.auth.ApplicationUserRole;
import com.coordinator.core.auth.mappers.AuthUserEntityToDtoMapper;
import com.coordinator.core.auth.models.AuthUserDto;
import com.coordinator.core.auth.models.AuthUserRequest;
import com.coordinator.core.coordinator.main.models.ImmutableCoordinatorEntity;
import com.coordinator.core.coordinator.main.repository.ICoordinatorRepository;
import com.coordinator.core.general.main.helpers.SqlHelper;
import com.coordinator.core.users.main.models.ImmutableUserEntity;
import com.coordinator.core.users.main.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static com.coordinator.core.coordinator.main.mappers.CoordinatorPostRequestToEntityMapper.mapCoordinatorRequestToEntity;
import static com.coordinator.core.users.main.mappers.UserPostRequestToEntityMapper.mapUserRequestToEntity;

@Repository("postgres")
public class AuthUserRepositoryImpl implements IAuthUserRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;
    private final IUserRepository iUserRepository;
    private final ICoordinatorRepository iCoordinatorRepository;

    @Autowired
    public AuthUserRepositoryImpl(@Qualifier("coreJdbcTemplate") JdbcTemplate jdbcTemplate,
                                  NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                                  IUserRepository iUserRepository,
                                  ICoordinatorRepository iCoordinatorRepository
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.iUserRepository = iUserRepository;
        this.iCoordinatorRepository = iCoordinatorRepository;
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

        createRoleId(newAuthUserId, authUserRequest.getUsername(), authUserRequest.getRoleId());

    }

    private void createRoleId(UUID newAuthUserId, String contactEmail, Integer roleId) {
        switch (roleId) {
            case ApplicationUserRole
                    .Constants.ROLE_USER:
                ImmutableUserEntity immutableUserEntity = mapUserRequestToEntity(newAuthUserId, contactEmail);
                iUserRepository.createUser(immutableUserEntity);
                break;
            case ApplicationUserRole
                    .Constants.ROLE_COORDINATOR:
                ImmutableCoordinatorEntity immutableCoordinatorEntity = mapCoordinatorRequestToEntity(newAuthUserId, contactEmail);
                iCoordinatorRepository.createCoordinator(immutableCoordinatorEntity);
                break;
            case ApplicationUserRole
                    .Constants.ROLE_ADMIN:
                break;
        }
    }
}
