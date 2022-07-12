package com.hbproject.board.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
//**으로 두번째 경로까지 잡아줘야 mapper 디렉토리를 잡음
//com.hbproject.board.**.mapper와 동일
@MapperScan("com.hbproject.board.services.*.mapper")
public class MybatisConfig {

    //@Value 는 단일값 주입이기때문에 여러값을 받아올때는 @ConfigurationProperties을 이용해야한다.
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    @Bean
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/*.xml"));

        return sessionFactory.getObject();
    }
}
