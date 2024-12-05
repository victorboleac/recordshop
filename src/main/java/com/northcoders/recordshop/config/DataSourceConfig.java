package com.northcoders.recordshop.config;

import com.northcoders.recordshop.database.DataBaseManager;
import com.northcoders.recordshop.database.H2DataBaseManager;
import com.northcoders.recordshop.database.PostgresDataBaseManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;


//TODO: make it SOLID!!! - DONE
// https://www.baeldung.com/spring-boot-configure-data-source-programmatic

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String postgresUrl;

    @Value("${spring.datasource.username}")
    private String postgresUsername;

    @Value("${spring.datasource.password}")
    private String postgresPassword;

    @Value("${spring.datasource.driver-class-name}")
    private String postgresDriver;

    @Bean
    public DataSource dataSource() {

        DataBaseManager postgresManager = new PostgresDataBaseManager(postgresUrl,postgresUsername,
                postgresPassword, postgresDriver);

        if(postgresManager.isAvailable()) return postgresManager.getDataSource();

        System.out.println("Connecting to H2 inMemory database...");
        DataBaseManager h2Manager = new H2DataBaseManager();
        return h2Manager.getDataSource();
    }
}
