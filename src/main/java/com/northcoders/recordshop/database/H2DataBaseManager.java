package com.northcoders.recordshop.database;

import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

public class H2DataBaseManager implements DataBaseManager {

    private final String url = "jdbc:h2:mem:record_shop";
    private final String username = "sa";
    private final String password = "";
    private final String driverClassName = "org.h2.Driver";

// NOTE: will never call it
    @Override
    public boolean isAvailable() {
        System.out.println("Is always available");
        return true;
    }

    @Override
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName(driverClassName)
                .build();
    }
}
