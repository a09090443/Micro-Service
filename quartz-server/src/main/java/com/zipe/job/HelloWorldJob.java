package com.zipe.job;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldJob extends QuartzJobFactory {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorldJob.class);

    @Override
    void executeJob(JobExecutionContext jobExecutionContext) {
        try {
            System.out.println(jobExecutionContext.getJobDetail());
            System.out.println("Hello World!!");
        } catch (Exception e) {
            throw e;
        }
    }
}
