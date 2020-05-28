package com.coordinator.core.cofig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "datasource.core")
    public DataSource dataSource() {
        DataSourceBuilder builder = DataSourceBuilder.create().create();
        builder.url("jdbc:postgresql://localhost:5432/coordinate");
        builder.password("Elijah@2018");
        builder.username("postgres");
        System.out.println("My custom data source bean has been initialized and set");
        return builder.build();
    }

    @Bean
    public JdbcTemplate coreJdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
