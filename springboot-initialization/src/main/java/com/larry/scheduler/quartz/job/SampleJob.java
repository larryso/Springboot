package com.larry.scheduler.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SampleJob extends QuartzJobBean{
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        String dataPassed = context.getMergedJobDataMap().get("data").toString();
        log.info("Data passed from jobDetails: {}", dataPassed);
    }
}
