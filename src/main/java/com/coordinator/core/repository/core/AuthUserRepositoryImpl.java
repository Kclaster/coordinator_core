package com.coordinator.core.repository.core;

import com.coordinator.core.mappers.AuthUserMapper;
import com.coordinator.core.models.AuthUser;
import com.coordinator.core.models.AuthUserRequest;
import com.coordinator.core.repository.SqlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class AuthUserRepositoryImpl implements IAuthUserRepository {
    private final PasswordEncoder passwordEncoder;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthUserRepositoryImpl(PasswordEncoder passwordEncoder, @Qualifier("coreJdbcTemplate") JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.passwordEncoder = passwordEncoder;
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Optional<AuthUser> selectApplicationUserByUsername(String username) {
        String sql = SqlHelper.sql("select-auth-user");
        List<AuthUser> authUsers = jdbcTemplate.query(
                sql,
                new AuthUserMapper(passwordEncoder),
                username
                );
        if (authUsers.size() == 1) {
            return Optional.of(authUsers.get(0));
        }
        return null;
    }

    @Override
    public void saveAuthUser(AuthUserRequest authUserRequest) {
        String sql = SqlHelper.sql("insert-auth-user");

        Map<String, Object> params = Map.of(
                "coordinatorId", UUID.randomUUID(),
                "username", authUserRequest.getUsername(),
                "password", authUserRequest.getPassword(),
                "authUserRoleId", authUserRequest.getRoleId()
        );
        namedParameterJdbcTemplate.update(sql, params);

    }
}
