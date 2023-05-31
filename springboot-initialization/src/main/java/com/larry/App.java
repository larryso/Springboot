package com.larry;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.larry.rest.ObjectMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PreDestroy;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class App {
    @Autowired
    @Qualifier("threadPoolTaskExecutor")
    private TaskExecutor executor;
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            log.info("CommondLineRunner running with arguments: {}", Arrays.toString(args));
        };
    }
    @Bean
    ApplicationRunner applicationRunner(){
        return args -> {
            log.info("ApplicationRunner running with arguments: {}", args.getOptionNames());
        };
    }
    //customize Springboot jacskon config
    @Bean
    @Primary
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = ObjectMapperUtils.createObjectMapper();
        objectMapper.deactivateDefaultTyping();
        SimpleModule module = new SimpleModule("initialization", new Version(0,1,0,"", "com.larry", "spring-boot-initialization"));
        module.addSerializer(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        objectMapper.registerModule(module);
        return objectMapper;
    }
    @PreDestroy
    public void onExit(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = (ThreadPoolTaskExecutor) executor;
        threadPoolTaskExecutor.shutdown();
    }
}
