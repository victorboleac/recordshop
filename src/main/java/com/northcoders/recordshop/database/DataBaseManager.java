package com.northcoders.recordshop.database;

import javax.sql.DataSource;

public interface DataBaseManager {
    boolean isAvailable();
    DataSource getDataSource();
}
