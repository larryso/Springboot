package com.larry.api;

import com.larry.api.logging.PatternLayoutLoggingContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) throws ClassNotFoundException {
        SpringApplication.run(new Class[]{App.class,
                        Class.forName(PatternLayoutLoggingContext.class.getName(), true, Thread.currentThread().getContextClassLoader())},
                args);
    }
}
