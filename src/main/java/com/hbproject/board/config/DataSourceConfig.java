package com.hbproject.board.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    //@Value 는 단일값 주입이기때문에 여러값을 받아올때는 @ConfigurationProperties을 이용해야한다.
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    @Bean
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }
}
