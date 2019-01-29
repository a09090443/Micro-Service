package com.zipe.controller;

import com.zipe.job.AbstractJob;
import com.zipe.payload.ScheduleJobDetail;
import org.quartz.JobDataMap;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
public class TestJobController extends AbstractJob {
    private static final Logger logger = LoggerFactory.getLogger(TestJobController.class);

    @PostMapping("/schedulerTest")
    public ResponseEntity<ScheduleJobDetail> scheduleTest(@Valid @RequestBody ScheduleJobDetail scheduleJobDetail) {
        ScheduleJobDetail result = null;
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
}
