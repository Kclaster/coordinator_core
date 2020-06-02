package com.coordinator.core.repository.core;

import com.coordinator.core.mappers.UserMapper;
import com.coordinator.core.models.UserDto;
import com.coordinator.core.repository.SqlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements IUserRepository {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(@Qualifier("coreJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserDto> getAllUsers() {

        return jdbcTemplate.query(
                SqlHelper.sql(
                        "select-all-users"),
                        new UserMapper()
        );
    }
}
