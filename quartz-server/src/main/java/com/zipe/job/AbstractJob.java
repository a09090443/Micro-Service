package com.zipe.job;

import com.zipe.enums.ScheduleEmun;
import com.zipe.payload.ScheduleJobDetail;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public abstract class AbstractJob {

    @Autowired
    private Scheduler scheduler;

    public ScheduleJobDetail executeJobProcess(ScheduleJobDetail scheduleJobDetail, JobDataMap jobDataMap) throws ClassNotFoundException, ParseException, SchedulerException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        Date startTime = sdf.parse(scheduleJobDetail.getStartDate() + " " + scheduleJobDetail.getTime());
        Date endTime = sdf.parse(scheduleJobDetail.getEndDate() + " " + scheduleJobDetail.getTime());

        JobDetail jobDetail = buildJobDetail(scheduleJobDetail, jobDataMap);
        Trigger trigger = buildJobTrigger(jobDetail, startTime, endTime, scheduleJobDetail);
        scheduler.scheduleJob(jobDetail, trigger);
        return null;
    }

    public JobDetail buildJobDetail(ScheduleJobDetail scheduleJobDetail, JobDataMap jobDataMap) throws ClassNotFoundException {

        Class clazz = Class.forName(scheduleJobDetail.getClassPath());
        return JobBuilder.newJob(clazz)
                .withIdentity(UUID.randomUUID().toString(), scheduleJobDetail.getGroup())
                .withDescription(scheduleJobDetail.getDescription())
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    public Trigger buildJobTrigger(JobDetail jobDetail, Date startTime, Date endTime, ScheduleJobDetail scheduleJobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), scheduleJobDetail.getJobName())
                .withDescription(scheduleJobDetail.getDescription())
                .startAt(startTime)
                .endAt(endTime)
                .withSchedule(ScheduleEmun.getTimeUnit(scheduleJobDetail.getTimeUnit()).setCycle(scheduleJobDetail.getInterval(), scheduleJobDetail.getRepeatTimes()))
                .build();
    }
}
