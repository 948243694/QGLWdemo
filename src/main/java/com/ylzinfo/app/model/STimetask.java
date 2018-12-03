package com.ylzinfo.app.model;

import java.util.Date;

public class STimetask extends ValueObject {
    private String MSGNO; //任务ID
    private String name; //任务名称
    private String groupName; //任务分组
    private String startTime; //开启时间
    private String endTime; //结束时间
    private String cron; //cron表达式
    private String jobStatus; //任务状态（是否执行）
    private String planStatus; //任务计划状态
    private String isConcurrent; //是否并发
    private String methodName; //方法名
    private String beanName ; //类路径
    private String description; //任务描述

    public String getMSGNO() {
        return MSGNO;
    }

    public void setMSGNO(String MSGNO) {
        this.MSGNO = MSGNO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }

    public String getIsConcurrent() {
        return isConcurrent;
    }

    public void setIsConcurrent(String isConcurrent) {
        this.isConcurrent = isConcurrent;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
