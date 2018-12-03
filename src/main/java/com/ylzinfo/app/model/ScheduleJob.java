package com.ylzinfo.app.model;

import java.util.Date;

public class ScheduleJob extends ValueObject {

    public static final String STATUS_RUNNING = "1";  //正在运行

    public static final String STATUS_NOT_RUNNING = "0"; // 已停止

    public static final String CONCURRENT_IS = "1";

    public static final String CONCURRENT_NOT = "0";

    private String jobId;

    private Date createTime;

    private Date updateTime;

    private String jobName; //任务名称

    private String jobGroup; //任务分组

    private String jobStatus; //任务状态 是否启动任务

    private String cronExpression; //cron表达式

    private String description; //描述

    private String beanClass; //任务执行时调用哪个类的方法 包名+类名

    private String isConcurrent; //任务是否并发

    private String springId; //spring bean

    private String methodName; //任务调用的方法名

    public ScheduleJob(STimetask sTimetask){
        this.jobId = sTimetask.getMSGNO();
        this.jobGroup = sTimetask.getGroupName();
        this.jobName = sTimetask.getName();
        this.jobStatus = sTimetask.getJobStatus();
        this.isConcurrent = sTimetask.getIsConcurrent();
        this.cronExpression = sTimetask.getCron();
        this.beanClass = sTimetask.getBeanName();
        this.methodName = sTimetask.getMethodName();
    }

    public ScheduleJob(){}

    public String getJobId() {
        return jobId;
    }


    public void setJobId(String jobId) {
        this.jobId = jobId;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(String beanClass) {
        this.beanClass = beanClass;
    }

    public String getIsConcurrent() {
        return isConcurrent;
    }

    public void setIsConcurrent(String isConcurrent) {
        this.isConcurrent = isConcurrent;
    }

    public String getSpringId() {
        return springId;
    }

    public void setSpringId(String springId) {
        this.springId = springId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }


}
