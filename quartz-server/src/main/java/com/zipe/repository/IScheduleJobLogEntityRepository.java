package com.zipe.repository;

import com.zipe.model.ScheduleJobEntity;
import com.zipe.model.ScheduleJobLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IScheduleJobLogEntityRepository extends JpaRepository<ScheduleJobLogEntity, Long> {

    public ScheduleJobLogEntity findById(int id);
}