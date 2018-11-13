package com.topjet.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by zhaocj on 2017/7/15.
 */
@Configuration
public class PayMybatisConfig {

    private static Logger logger = Logger.getLogger(PayMybatisConfig.class);

    @Bean(name = "paydatasource")
    @Qualifier("paydatasource")
    @ConfigurationProperties(prefix = "spring.paydatasource")
    public DataSource systemdataSource() {
        logger.info("-------------------- paydatasource init ---------------------");
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "payJdbcTemplate")
    public JdbcTemplate payJdbcTemplate(@Qualifier("paydatasource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
