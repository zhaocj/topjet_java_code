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
 * Created by XS on 2017/7/15.
 */
@Configuration
@EnableTransactionManagement
//@ComponentScan
@MapperScan("com.topjet.manage.mapper.writeMapper")
public class MybatisConfig {

    private static Logger logger = Logger.getLogger(MybatisConfig.class);


//    private Class<? extends DataSource> dataSourceType=com.alibaba.druid.pool.DruidDataSource.class;

    @Bean(name="dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    public DataSource dataSource() {
        logger.info("-------------------- writeDataSource init ---------------------");
//        logger.info("dataSourceType:"+dataSourceType);
        return DataSourceBuilder.create().build();
    }
    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setTypeAliasesPackage("com.topjet.manage.domain.model.*");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("com.topjet.manage.mapper.writeMapper.*.xml"));
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactoryBean.getObject();
    }
    /**
     * 配置事务管理器
     */
    @Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }
}
