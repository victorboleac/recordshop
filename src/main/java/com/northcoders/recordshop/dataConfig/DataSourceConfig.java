package com.northcoders.recordshop.dataConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//TODO: make it SOLID!!
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


        String h2Url = "jdbc:h2:mem:record_shop";
        String h2Username = "sa";
        String h2Password = "";
        String h2Driver = "org.h2.Driver";

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
                        .driverClassName(postgresDriver)
                        .build();
            }
        }catch (SQLException e) {
            System.out.println("Failed to connect to local postgres DB");
            System.out.println(e.getMessage());
        }
        System.out.println("Trying to connect to H2 inMemory DB...");
        DataSource dbConnection = DataSourceBuilder.create()
                .url(h2Url)
                .username(h2Username)
                .password(h2Password)
                .driverClassName(h2Driver)
                .build();
        System.out.println("Successfully connected to H2 inMemory DB");
        return dbConnection;
    }
}
