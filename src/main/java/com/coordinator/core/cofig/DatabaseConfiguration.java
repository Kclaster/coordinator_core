package com.coordinator.core.cofig;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {
//    @Value("${DB_PASSWORD}")
//    private String dbPasowrd;
//
//    @Value("${DB_USERNAME}")
//    private String dbUsername;
//
//    @Value("${DB_URL}")
//    private String dbUrl;
//
//    @Bean
//    @ConfigurationProperties(prefix = "datasource.core")
//    public DataSource dataSource() {
//        DataSourceBuilder builder = DataSourceBuilder.create().create();
//        builder.url(dbUrl);
//        builder.password(dbPasowrd);
//        builder.username(dbUsername);
//        return builder.build();
//    }
//
//    @Bean
//    public JdbcTemplate coreJdbcTemplate() {
//        return new JdbcTemplate(dataSource());
//    }
}
