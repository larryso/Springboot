package com.larry.scheduler.quartz;

import com.larry.scheduler.quartz.job.SampleJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class QuartzService {
    @Autowired
    private Scheduler scheduler;

    public void fireSimpleJob(){
        try{
            JobDetail jobDetail = this.buildJobDetailsForSimpleJob();
            Trigger trigger = this.buildTriggerForSimpleJob(jobDetail);
            scheduler.scheduleJob(jobDetail, trigger);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private JobDetail buildJobDetailsForSimpleJob(){
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("data", "Data can be passed to job, and could be consumed by others!");
        return JobBuilder.newJob(SampleJob.class).
                withIdentity(UUID.randomUUID().toString(), "SimpleJob").
                withDescription("Description of SimpleJob").
                usingJobData(jobDataMap).//pass data that job need
                storeDurably(true). //false - delete job once job executed
                build();
    }
    private Trigger buildTriggerForSimpleJob(JobDetail jobDetail){
        ZonedDateTime dateTime = ZonedDateTime.of(LocalDate.parse("2023-12-18"),
                LocalTime.of(10, 40), ZoneId.systemDefault());
        return TriggerBuilder.newTrigger().
                forJob(jobDetail).
                withIdentity(jobDetail.getKey().getName(), "SimpleJobTrigger").
                withDescription("SimpleJobTrigger").
                //startNow().
                        startAt(Date.from(dateTime.toInstant())).
                withSchedule(SimpleScheduleBuilder.repeatSecondlyForTotalCount(2, 2)).
                build();
    }
}
