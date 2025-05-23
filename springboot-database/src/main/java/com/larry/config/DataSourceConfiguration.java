package com.larry.config;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@MapperScan(basePackages = "com.larry.mapper", sqlSessionFactoryRef = "test1SqlSessionFactory")
public class DataSourceConfiguration {
	@Bean(name="testDataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.test")
	public DataSource testDataSource() {
		return DataSourceBuilder.create().build();
	}
}
