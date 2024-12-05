package com.northcoders.recordshop.dataConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



// https://www.baeldung.com/spring-boot-configure-data-source-programmatic
// https://www.baeldung.com/spring-value-annotation

@Configuration
public class DataSourceConfig {
    @Value("${spring.datasource.url}")
    private String postgresUrl;
    @Value("${spring.datasource.username}")
    private String postgresUsername;
    @Value("${spring.datasource.password}")
    private String postgresPassword;
    @Value("${spring.datasource.driver-class-name}")
    private String postgresDriverClassName;
    @Value("${spring.datasource.h2.url}")
    private String h2Url;
    @Value("${spring.datasource.h2.username}")
    private String h2Username;
    @Value("${spring.datasource.h2.password}")
    private String h2Password;
    @Value("${spring.datasource.h2.driver-class-name}")
    private String h2DriverClassName;


    @Bean
    public DataSource dataSource() {
        try{
            System.out.println("Trying to connect to local postgres DB");
            Connection connection = DriverManager.getConnection(postgresUrl, postgresUsername, postgresPassword);
            if (connection != null) {
                connection.close();
                System.out.println("Successfully connected to local postgres DB");
                return DataSourceBuilder.create()
                        .url(postgresUrl)
                        .username(postgresUsername)
                        .password(postgresPassword)
                        .driverClassName(postgresDriverClassName)
                        .build();
            }
        }catch (SQLException e) {
            System.out.println("Failed to connect to local postgres DB");
            System.out.println(e.getMessage());
        }
        System.out.println("Trying to connect to H2 inMemory DB...");
        return  DataSourceBuilder.create()
                .url(h2Url)
                .username(h2Username)
                .password(h2Password)
                .driverClassName(h2DriverClassName)
                .build();
    }
}
