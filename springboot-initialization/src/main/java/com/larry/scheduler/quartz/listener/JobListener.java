package com.larry.scheduler.quartz.listener;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobListener extends JobListenerSupport {
    @Override
    public String getName() {
        return "JOB_TIGGER_LISTENER";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        log.info("Job to be Executed: {}", context);
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        super.jobExecutionVetoed(context);
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        super.jobWasExecuted(context, jobException);
    }
}
