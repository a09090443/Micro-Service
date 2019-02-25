package com.zipe.service;

import com.zipe.model.ScheduleJobEntity;
import com.zipe.payload.ScheduleJobDetail;

import java.util.List;

public interface IScheduleJobService {

    public List<ScheduleJobEntity> findAllJobs() throws Exception;

    public ScheduleJobEntity findById(int id) throws Exception;

    public ScheduleJobEntity findByJobName(String jobName) throws Exception;

    public void saveOrUpdate(ScheduleJobDetail scheduleJobDetail) throws Exception;

    public void delete(ScheduleJobEntity scheduleJobEntity) throws Exception;

}
