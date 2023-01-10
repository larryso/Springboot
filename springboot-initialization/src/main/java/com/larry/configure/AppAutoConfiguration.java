package com.larry.configure;

import com.larry.smartLifeCycleTest.TestSmartLifeCycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class AppAutoConfiguration {
    @Bean
    TestSmartLifeCycle testSmartLifeCycle(){
        return new TestSmartLifeCycle();
    }
}
