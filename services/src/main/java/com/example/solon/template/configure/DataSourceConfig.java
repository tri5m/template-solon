package com.example.solon.template.configure;

import com.zaxxer.hikari.HikariDataSource;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

import javax.sql.DataSource;

/**
 * @title: DemoConfig
 * @author: trifolium.wang
 * @date: 2024/4/2
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "master", typed = true)
    public DataSource db1(@Inject("${database.master}") HikariDataSource ds) {
        return ds;
    }
}
