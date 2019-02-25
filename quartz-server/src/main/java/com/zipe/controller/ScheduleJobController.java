package com.zipe.controller;

import com.zipe.enums.SheduleJobStatusEmun;
import com.zipe.job.AbstractJob;
import com.zipe.model.ScheduleJobEntity;
import com.zipe.payload.ScheduleJobDetail;
import com.zipe.service.IScheduleJobService;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
public class ScheduleJobController extends AbstractJob {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleJobController.class);

    @Autowired
    private IScheduleJobService scheduleJobService;

    @PostMapping("/register")
    public ResponseEntity<ScheduleJobDetail> register(@Valid @RequestBody ScheduleJobDetail scheduleJobDetail) throws Exception {
        try {
            scheduleJobDetail = saveOrUpdateScheduleJobStatus(scheduleJobDetail, SheduleJobStatusEmun.START.status);

            result = this.createJobProcess(scheduleJobDetail);
            if (null == result.getErrorMessage()) {
                return ResponseEntity.ok(result);
            } else {
                logger.error(result.getErrorMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
            }

        } catch (SchedulerException ex) {
            logger.error("Error scheduling message", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/delete")
    public ResponseEntity<ScheduleJobDetail> delete(@Valid @RequestBody ScheduleJobDetail scheduleJobDetail) throws Exception {
        try {
            scheduleJobDetail = saveOrUpdateScheduleJobStatus(scheduleJobDetail, SheduleJobStatusEmun.DELETE.status);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }

        result = this.deleteJobProcess(scheduleJobDetail);
        if (null == result.getErrorMessage()) {
            return ResponseEntity.ok(result);
        } else {
            logger.error(result.getErrorMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    @PostMapping("/stop")
    public ResponseEntity<ScheduleJobDetail> stop(@Valid @RequestBody ScheduleJobDetail scheduleJobDetail) throws Exception {
        try {
            scheduleJobDetail = saveOrUpdateScheduleJobStatus(scheduleJobDetail, SheduleJobStatusEmun.SUSPEND.status);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }

        result = this.suspendJobProcess(scheduleJobDetail);
        if (null == result.getErrorMessage()) {
            return ResponseEntity.ok(result);
        } else {
            logger.error(result.getErrorMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    @PostMapping("/start")
    public ResponseEntity<ScheduleJobDetail> start(@Valid @RequestBody ScheduleJobDetail scheduleJobDetail) throws Exception {
        try {
            scheduleJobDetail = saveOrUpdateScheduleJobStatus(scheduleJobDetail, SheduleJobStatusEmun.START.status);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }

        result = this.resumeJobProcess(scheduleJobDetail);
        if (null == result.getErrorMessage()) {
            return ResponseEntity.ok(result);
        } else {
            logger.error(result.getErrorMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    private ScheduleJobDetail saveOrUpdateScheduleJobStatus(ScheduleJobDetail scheduleJobDetail, int status) throws Exception {
        scheduleJobDetail.setStatus(status);
        try {
            ScheduleJobEntity scheduleJobEntity = scheduleJobService.findByJobName(scheduleJobDetail.getJobName());
            if(null != scheduleJobEntity){
                scheduleJobService.delete(scheduleJobEntity);
            }
            scheduleJobService.saveOrUpdate(scheduleJobDetail);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
        return scheduleJobDetail;
    }
}
