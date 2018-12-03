package com.ylzinfo.app.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class STimetaskLog extends ValueObject{
    private String logID; //日子ID
    private String createDate; //创建时间
    private String MSGNO; //任务类别
    private String reason; //失败原因
    private String state; //执行结果
    private String message; //任务描述

    public String getLogID() {
        return logID;
    }

    public void setLogID(String logID) {
        this.logID = logID;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        this.createDate = df.format(createDate);
    }

    public String getMSGNO() {
        return MSGNO;
    }

    public void setMSGNO(String MSGNO) {
        this.MSGNO = MSGNO;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
