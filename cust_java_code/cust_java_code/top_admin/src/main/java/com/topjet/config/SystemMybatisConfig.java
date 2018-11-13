package com.topjet.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by zhaocj on 2017/7/15.
 */
@Configuration
@EnableTransactionManagement
//@ComponentScan
@MapperScan(basePackages = {"com.topjet.system.mapper"},sqlSessionFactoryRef = "systemsqlSessionFactory")
public class SystemMybatisConfig {

    private static Logger logger = Logger.getLogger(SystemMybatisConfig.class);

    @Bean(name = "systemdataSource")
    @ConfigurationProperties(prefix = "spring.systemdatasource")
    public DataSource systemdataSource() {
        logger.info("-------------------- writeDataSource init ---------------------");
        return DataSourceBuilder.create().build();
    }


    @Bean
    public SqlSessionFactory systemsqlSessionFactory(@Qualifier("systemdataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.topjet.system.domain.model.*");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("com.topjet.system.mapper.*.xml"));
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactoryBean.getObject();
    }
    /**
     * 配置事务管理器
     */
    @Bean(name = "transactionManager2")
//    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("systemdataSource") DataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }
}
