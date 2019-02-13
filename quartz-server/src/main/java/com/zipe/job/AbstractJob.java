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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = sdf.parse(scheduleJobDetail.getStartDate() + " " + scheduleJobDetail.getTime());
        Date endTime = sdf.parse(scheduleJobDetail.getEndDate() + " " + scheduleJobDetail.getTime());
        try {
            JobDetail jobDetail = buildJobDetail(scheduleJobDetail, jobDataMap);
            Trigger trigger = buildJobTrigger(jobDetail, startTime, endTime, scheduleJobDetail);

            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw e;
        }

        return null;
    }

    public ScheduleJobDetail deleteJobProcess(ScheduleJobDetail scheduleJobDetail) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(scheduleJobDetail.getJobName(), scheduleJobDetail.getGroup());
        try {
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            throw e;
        }
        return null;
    }

    public JobDetail buildJobDetail(ScheduleJobDetail scheduleJobDetail, JobDataMap jobDataMap) throws ClassNotFoundException {

        Class clazz = Class.forName(scheduleJobDetail.getClassPath());
        return JobBuilder.newJob(clazz)
                .withIdentity(scheduleJobDetail.getJobName(), scheduleJobDetail.getGroup())
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
