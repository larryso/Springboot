package com.larry.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;

@Slf4j
@Component
@Async
public class MyApplicationListener {
    @EventListener
    public void handleApplicationStartingEvent(ContextStartedEvent contextStartedEvent){
        log.info("@@@@@@ Application Starting.....");
    }
    @EventListener
    public void handleApplicationStartedEvent(ContextRefreshedEvent contextRefreshedEvent){
        log.info("@@@@@@ Application Started.....");
    }
    @EventListener
    public void handleApplicationStartedEvent2(ApplicationStartedEvent applicationStartedEvent){
        log.info("@@@@@@@ Application Started 2 ......");
    }
    @EventListener
    public void handleApplicationStoppingEvent(ContextStoppedEvent contextStoppedEvent){
        log.info("@@@@@@ Application Stropping.....");
    }
    @EventListener
    public void handleApplicationStoppedEvent(ContextClosedEvent contextClosedEvent){
        log.info("@@@@@@ Application Stoped.....");
    }
    @EventListener
    public void handleRequestHandledEvent(RequestHandledEvent requestHandledEvent){
        log.info("@@@@@@ Request handled.....");
    }

}
