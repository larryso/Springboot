package com.larry.scheduler.quartz.listener;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Trigger;
import org.quartz.listeners.SchedulerListenerSupport;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TigerListener extends SchedulerListenerSupport {
    @Override
    public void jobScheduled(Trigger trigger) {
        super.jobScheduled(trigger);
    }

    @Override
    public void schedulerStarted() {
        super.schedulerStarted();
    }

    @Override
    public void triggerFinalized(Trigger trigger) {
        super.triggerFinalized(trigger);
    }
}
