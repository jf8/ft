package com.kyanite.ft.config;

import com.kyanite.ft.toolbox.service.DataSourceService;
import io.github.jhipster.config.JHipsterConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Configuration
@EnableJpaRepositories("com.kyanite.ft.repository")
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@EnableTransactionManagement
public class DatabaseConfiguration {

    private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

    @Autowired
    Environment env;

    @Bean
    @ConfigurationProperties("app.datasource.conf")
    public DataSourceService confDataSourceService() {
        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        dataSourceProperties.setUsername(env.getProperty("app.datasource.conf.username"));
        dataSourceProperties.setPassword(env.getProperty("app.datasource.conf.password"));
        dataSourceProperties.setUrl(env.getProperty("app.datasource.conf.url"));
        dataSourceProperties.setType(com.zaxxer.hikari.HikariDataSource.class);
        DataSource dataSource =dataSourceProperties.initializeDataSourceBuilder().build();
        DataSourceService dataSourceService = new DataSourceService(dataSource);
        return  dataSourceService;
    }

}
