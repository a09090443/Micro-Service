package com.zipe.repository;

import com.zipe.model.ScheduleJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IScheduleJobEntityRepository extends JpaRepository<ScheduleJobEntity, Long> {

    public ScheduleJobEntity findById(int id);

    public ScheduleJobEntity findByJobName(String jobName);
}