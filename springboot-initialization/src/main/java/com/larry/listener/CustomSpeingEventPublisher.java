package com.larry.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomSpeingEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;
    public void publishCustomEvent(final String message) {
        log.info("Publishing custom event. ");
        CustomeEvent customSpringEvent = new CustomeEvent(this, message);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }
}
