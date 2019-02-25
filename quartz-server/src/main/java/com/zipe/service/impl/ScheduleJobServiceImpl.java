package com.zipe.service.impl;

import com.zipe.model.ScheduleJobEntity;
import com.zipe.payload.ScheduleJobDetail;
import com.zipe.repository.IScheduleJobEntityRepository;
import com.zipe.service.IScheduleJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Transactional
@Service("scheduleJobService")
public class ScheduleJobServiceImpl implements IScheduleJobService {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleJobServiceImpl.class);

    @Autowired
    private IScheduleJobEntityRepository scheduleJobEntityRepository;

    @Override
    public List<ScheduleJobEntity> findAllJobs() throws Exception {
        return scheduleJobEntityRepository.findAll();
    }

    @Override
    public ScheduleJobEntity findById(int id) throws Exception {
        return scheduleJobEntityRepository.findById(id);
    }

    @Override
    public ScheduleJobEntity findByJobName(String jobName) throws Exception {
        return scheduleJobEntityRepository.findByJobName(jobName);
    }

    @Override
    public void saveOrUpdate(ScheduleJobDetail scheduleJobDetail){
        ScheduleJobEntity scheduleJobEntity = new ScheduleJobEntity();
        scheduleJobEntity.setJobClass(scheduleJobDetail.getClassPath());
        scheduleJobEntity.setJobName(scheduleJobDetail.getJobName());
        scheduleJobEntity.setJobGroup(scheduleJobDetail.getGroup());
        scheduleJobEntity.setJobDescription(scheduleJobDetail.getDescription());
        scheduleJobEntity.setStartTime(scheduleJobDetail.getStartDate() + " " + scheduleJobDetail.getTime());
        scheduleJobEntity.setEndTime(scheduleJobDetail.getEndDate() + " " + scheduleJobDetail.getTime());
        scheduleJobEntity.setStatus(scheduleJobDetail.getStatus());
        scheduleJobEntity.setExecuteTimes(scheduleJobDetail.getRepeatTimes());
        scheduleJobEntity.setTimeUnit(scheduleJobDetail.getTimeUnit());
        scheduleJobEntity.setRepeatInterval(scheduleJobDetail.getInterval());

        scheduleJobEntityRepository.save(scheduleJobEntity);
    }

    @Override
    public void delete(ScheduleJobEntity scheduleJobEntity) throws Exception {
        scheduleJobEntityRepository.delete(scheduleJobEntity);
    }
}
