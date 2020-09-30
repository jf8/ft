package com.kyanite.ft.toolbox.service;

import javax.sql.DataSource;

public class DataSourceService {
    private DataSource dataSource;

    public DataSourceService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
