package com.zipe.job;

import com.zipe.enums.ScheduleEmun;
import com.zipe.enums.SheduleJobStatusEmun;
import com.zipe.payload.ScheduleJobDetail;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractJob {

    @Autowired
    private Scheduler scheduler;

    protected ScheduleJobDetail result = null;

    public ScheduleJobDetail executeJobProcess(ScheduleJobDetail scheduleJobDetail) throws ClassNotFoundException, ParseException, SchedulerException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = sdf.parse(scheduleJobDetail.getStartDate() + " " + scheduleJobDetail.getTime());
        Date endTime = sdf.parse(scheduleJobDetail.getEndDate() + " " + scheduleJobDetail.getTime());
        try {
            JobDetail jobDetail = buildJobDetail(scheduleJobDetail);
            Trigger trigger = buildJobTrigger(jobDetail, startTime, endTime, scheduleJobDetail);

            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw e;
        }

        return scheduleJobDetail;
    }

    public ScheduleJobDetail deleteJobProcess(ScheduleJobDetail scheduleJobDetail) throws SchedulerException, ParseException, ClassNotFoundException {
        scheduleJobDetail = this.scheduleJobStatusProcess(scheduleJobDetail, SheduleJobStatusEmun.DELETE);
        return scheduleJobDetail;
    }

    public ScheduleJobDetail suspendJobProcess(ScheduleJobDetail scheduleJobDetail) throws SchedulerException, ParseException, ClassNotFoundException {
        scheduleJobDetail = this.scheduleJobStatusProcess(scheduleJobDetail, SheduleJobStatusEmun.SUSPEND);
        return scheduleJobDetail;
    }

    public ScheduleJobDetail resumeJobProcess(ScheduleJobDetail scheduleJobDetail) throws SchedulerException, ParseException, ClassNotFoundException {
        scheduleJobDetail = this.scheduleJobStatusProcess(scheduleJobDetail, SheduleJobStatusEmun.RESUME);
        return scheduleJobDetail;
    }

    public ScheduleJobDetail createJobProcess(ScheduleJobDetail scheduleJobDetail) throws SchedulerException, ParseException, ClassNotFoundException {
        scheduleJobDetail = this.scheduleJobStatusProcess(scheduleJobDetail, SheduleJobStatusEmun.CREATE);
        return scheduleJobDetail;
    }

    public JobDetail buildJobDetail(ScheduleJobDetail scheduleJobDetail) throws ClassNotFoundException {

        Class clazz = Class.forName(scheduleJobDetail.getClassPath());

        if (null == scheduleJobDetail.getJobDataMap()) {
            scheduleJobDetail.setJobDataMap(new JobDataMap());
        }

        return JobBuilder.newJob(clazz)
                .withIdentity(scheduleJobDetail.getJobName(), scheduleJobDetail.getGroup())
                .withDescription(scheduleJobDetail.getDescription())
                .usingJobData(scheduleJobDetail.getJobDataMap())
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

    private ScheduleJobDetail scheduleJobStatusProcess(ScheduleJobDetail scheduleJobDetail, SheduleJobStatusEmun sheduleJobStatusEmun) throws SchedulerException, ParseException, ClassNotFoundException {
        JobKey jobKey = JobKey.jobKey(scheduleJobDetail.getJobName(), scheduleJobDetail.getGroup());
        try {
            if (!scheduler.checkExists(jobKey) && !sheduleJobStatusEmun.getDesc().equals(SheduleJobStatusEmun.CREATE.desc)) {
                throw new SchedulerConfigException("The job is not exist.");
            }
        } catch (SchedulerException e) {
            scheduleJobDetail.setErrorMessage(e.getMessage());
        }

        try {
            switch (sheduleJobStatusEmun) {
                case CREATE:
                    scheduler.deleteJob(jobKey);
                    this.executeJobProcess(scheduleJobDetail);
                    break;
                case SUSPEND:
                    scheduler.pauseJob(jobKey);
                    break;
                case RESUME:
                    scheduler.resumeJob(jobKey);
                    break;
                case DELETE:
                    scheduler.deleteJob(jobKey);
                    break;
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            scheduleJobDetail.setErrorMessage("Schedule job : " + scheduleJobDetail.getJobName() + " error.");
        }


        return scheduleJobDetail;
    }
}
