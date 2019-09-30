package com.larry;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.larry")
@EnableAutoConfiguration
@MapperScan(basePackages = "com.larry.mapper")
@EntityScan(basePackages = "com.larry.entity")
@EnableJpaRepositories(basePackages = "com.larry.dao")
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
