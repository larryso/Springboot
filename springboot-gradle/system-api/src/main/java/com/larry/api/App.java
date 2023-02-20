package com.larry.api;

import com.larry.api.common.utils.ServerUtils;
import com.larry.api.logging.PatternLayoutLoggingContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import javax.inject.Inject;

@Slf4j
@SpringBootApplication
public class App {
    @Inject
    private ServerUtils serverUtils;

    public static void main(String[] args) throws ClassNotFoundException {
        SpringApplication.run(new Class[]{App.class,
                        Class.forName(PatternLayoutLoggingContext.class.getName(), true, Thread.currentThread().getContextClassLoader())},
                args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx){
        log.info("Application initializing ..... InstanceId is: {}", serverUtils.getInstanceId());
        return args ->{
            // do some initialization hear
        };
    }
    @PreDestroy
    public void onExit(){

    }
}
