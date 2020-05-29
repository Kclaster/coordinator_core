package com.coordinator.core.cofig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {
    @Value("${DB_PASSWORD}")
    private String dbPasowrd;

    @Value("${DB_USERNAME}")
    private String dbUsername;

    @Value("${DB_URL}")
    private String dbUrl;

    @Bean
    @ConfigurationProperties(prefix = "datasource.core")
    public DataSource dataSource() {
        DataSourceBuilder builder = DataSourceBuilder.create().create();
        builder.url(dbUrl);
        builder.password(dbPasowrd);
        builder.username(dbUsername);
        return builder.build();
    }

    @Bean
    public JdbcTemplate coreJdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
