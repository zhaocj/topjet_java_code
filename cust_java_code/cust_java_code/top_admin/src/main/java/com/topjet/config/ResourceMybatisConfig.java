package com.topjet.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@MapperScan(basePackages = {"com.topjet.resource.mapper"},sqlSessionFactoryRef = "resourcesqlSessionFactory")
public class ResourceMybatisConfig {

    private static Logger logger = Logger.getLogger(ResourceMybatisConfig.class);

    @Bean(name = "resourcedataSource")
    @ConfigurationProperties(prefix = "spring.resourcedatasource")
    public DataSource resourcedataSource() {
        logger.info("-------------------- writeDataSource init ---------------------");
        return DataSourceBuilder.create().build();
    }


    @Bean
    public SqlSessionFactory resourcesqlSessionFactory(@Qualifier("resourcedataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.topjet.resource.domain.model.*");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("com.topjet.resource.mapper.*.xml"));
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactoryBean.getObject();
    }
    /**
     * 配置事务管理器
     */
    @Bean(name = "transactionManager1")
//    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("resourcedataSource") DataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }
}
