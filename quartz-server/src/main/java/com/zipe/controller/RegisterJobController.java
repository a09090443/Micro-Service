package com.zipe.controller;

import com.zipe.job.AbstractJob;
import com.zipe.payload.ScheduleJobDetail;
import com.zipe.service.IRegisterJobService;
import org.quartz.JobDataMap;
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
public class RegisterJobController extends AbstractJob {
    private static final Logger logger = LoggerFactory.getLogger(RegisterJobController.class);

    @Autowired
    private IRegisterJobService registerJobService;

    @PostMapping("/register")
    public ResponseEntity<ScheduleJobDetail> register(@Valid @RequestBody ScheduleJobDetail scheduleJobDetail) {
        ScheduleJobDetail result = null;

        registerJobService.saveOrUpdate(scheduleJobDetail);
        try {
            result = this.executeJobProcess(scheduleJobDetail, new JobDataMap());
        } catch (SchedulerException ex) {
            logger.error("Error scheduling email", ex);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/stop")
    public ResponseEntity<ScheduleJobDetail> delete(@Valid @RequestBody ScheduleJobDetail scheduleJobDetail) {
        ScheduleJobDetail result = null;
        try {
            result = this.deleteJobProcess(scheduleJobDetail);
        } catch (SchedulerException ex) {
            logger.error("Error scheduling email", ex);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }

        return ResponseEntity.ok(result);
    }
}
