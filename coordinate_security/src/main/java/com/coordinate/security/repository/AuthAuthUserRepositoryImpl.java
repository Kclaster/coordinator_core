package com.coordinate.security.repository;

import com.coordinate.model.helpers.SqlHelper;
import com.coordinate.model.security.AuthUserRequest;
import com.coordinate.model.user.AuthUser;
import com.coordinate.security.mapper.AuthUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AuthAuthUserRepositoryImpl implements IAuthUserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public AuthAuthUserRepositoryImpl(@Qualifier("coreJdbcTemplate") JdbcTemplate jdbcTemplate,
                                      NamedParameterJdbcTemplate namedParameterJdbcTemplate
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Optional<AuthUser> selectUserByUsername(String username) {
        String sql = SqlHelper.sql("select-auth-user");
        try {
            List<AuthUser> authAuthUserEntities = jdbcTemplate.query(
                    sql,
                    new AuthUserMapper(),
                    username
            );

            if (authAuthUserEntities.size() == 1) {
                return Optional.of(authAuthUserEntities.get(0));
            }
            return Optional.empty();
        } catch (Exception e) {
            System.out.println(e);
        }

        return Optional.empty();
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
