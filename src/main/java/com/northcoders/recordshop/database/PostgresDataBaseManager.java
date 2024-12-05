package com.northcoders.recordshop.database;

import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDataBaseManager implements DataBaseManager {

    private final String url;
    private final String username;
    private final String password;
    private final String driverClassName;

    public PostgresDataBaseManager(String url, String username, String password, String driverClassName) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.driverClassName = driverClassName;
    }

    @Override
    public boolean isAvailable() {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("PostgresSQL DB is connected");
            return true;
        } catch (SQLException e){
            System.out.println("PostgresSQL DB is not connected\nError: " + e.getMessage());
            return false;
        }
    }

    @Override
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .url(this.url)
                .username(this.username)
                .password(this.password)
                .driverClassName(this.driverClassName)
                .build();
    }
}
