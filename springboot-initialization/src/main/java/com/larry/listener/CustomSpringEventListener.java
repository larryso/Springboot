package com.larry.listener;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomSpringEventListener implements ApplicationListener<CustomeEvent> {
    @SneakyThrows
    @Override
    public void onApplicationEvent(CustomeEvent event) {
        Thread.sleep(3000);
        log.info("Index Controller called....");
    }
}
