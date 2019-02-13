package com.zipe.job;

import com.zipe.model.ScheduleJobLogEntity;
import com.zipe.repository.IScheduleJobLogEntityRepository;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class QuartzJobFactory extends QuartzJobBean {

    @Autowired
    private IScheduleJobLogEntityRepository scheduleJobLogEntityRepository;

    private ScheduleJobLogEntity scheduleJobLogEntity = new ScheduleJobLogEntity();

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        scheduleJobLogEntity.setJobName(jobExecutionContext.getJobDetail().getKey().getName());
        scheduleJobLogEntity.setJobDescription(jobExecutionContext.getJobDetail().getDescription());
        scheduleJobLogEntity.setStartTime(sdf.format(new Date()));
        try {
            scheduleJobLogEntity.setStatus(1);
            scheduleJobLogEntityRepository.saveAndFlush(scheduleJobLogEntity);

            this.executeJob(jobExecutionContext);

            scheduleJobLogEntity.setStatus(2);

        } catch (Exception e) {
            scheduleJobLogEntity.setMessage(e.getMessage());
            scheduleJobLogEntity.setStatus(3);

        } finally {
            scheduleJobLogEntity.setEndTime(sdf.format(new Date()));
            scheduleJobLogEntityRepository.saveAndFlush(scheduleJobLogEntity);

        }
    }

    abstract void executeJob(JobExecutionContext jobExecutionContext);
}
