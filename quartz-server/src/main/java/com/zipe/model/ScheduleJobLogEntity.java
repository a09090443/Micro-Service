package com.zipe.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "schedule_job_log", schema = "dev_network", catalog = "")
public class ScheduleJobLogEntity {
    private Integer id;
    private String jobName;
    private String jobDescription;
    private Integer status;
    private String startTime;
    private String endTime;
    private String message;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "job_name")
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Basic
    @Column(name = "job_description")
    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "start_time")
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time")
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleJobLogEntity that = (ScheduleJobLogEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(jobName, that.jobName) &&
                Objects.equals(jobDescription, that.jobDescription) &&
                Objects.equals(status, that.status) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jobName, jobDescription, status, startTime, endTime, message);
    }
}
