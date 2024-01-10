package com.larry.scheduler.quartz.listener;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Trigger;
import org.quartz.listeners.SchedulerListenerSupport;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScheduleListener extends SchedulerListenerSupport {

    @Override
    public void schedulerStarted() {
        super.schedulerStarted();
    }

    @Override
    public void triggerFinalized(Trigger trigger) {
        super.triggerFinalized(trigger);
    }

    @Override
    public void jobScheduled(Trigger trigger) {
        log.info("Scheduler: jobScheduled {}", trigger);
    }
}
