package com.coordinator.core.repository.core;

import com.coordinator.core.mappers.AuthUserMapper;
import com.coordinator.core.models.ApplicationUser;
import com.coordinator.core.repository.SqlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("postgres")
public class ApplicationUserDaoService implements ApplicationUserDao {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ApplicationUserDaoService(PasswordEncoder passwordEncoder, @Qualifier("coreJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.passwordEncoder = passwordEncoder;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        System.out.println(username);
        String sql = SqlHelper.sql("select-auth-user");
        ApplicationUser applicationUser = jdbcTemplate.queryForObject(
                sql,
                new AuthUserMapper(passwordEncoder),
                username
                );

        return Optional.of(applicationUser);
    }
}
