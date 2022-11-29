package com.larry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class App {
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
}
