package com.ylzinfo.app.model;

public class Qglw_job_clogDTO extends ValueObject {
    private String AAZ496; //原发送方报文ID
    private String LOGID0; //日志ID
    private String AAE030; //发送时间
    private String AAE031; //获取部平台信息时间
    private String LogDate; //写入日志时间
    private String MSGNO; //交易代码
    private String AAE314; //成功标志
    private String AAA204; //提示信息
    private String AUTOSIGN; //自动标志

    public String getAAZ496() {
        return AAZ496;
    }

    public void setAAZ496(String AAZ496) {
        this.AAZ496 = AAZ496;
    }

    public String getLOGID0() {
        return LOGID0;
    }

    public void setLOGID0(String LOGID0) {
        this.LOGID0 = LOGID0;
    }

    public String getAAE030() {
        return AAE030;
    }

    public String getAAE031() {
        return AAE031;
    }

    public String getLogDate() {
        return LogDate;
    }

    public void setLogDate(String logDate) {
        LogDate = logDate;
    }

    public String getMSGNO() {
        return MSGNO;
    }

    public void setMSGNO(String MSGNO) {
        this.MSGNO = MSGNO;
    }

    public String getAAE314() {
        return AAE314;
    }

    public void setAAE314(String AAE314) {
        this.AAE314 = AAE314;
    }

    public String getAAA204() {
        return AAA204;
    }

    public void setAAA204(String AAA204) {
        this.AAA204 = AAA204;
    }

    public String getAUTOSIGN() {
        return AUTOSIGN;
    }

    public void setAUTOSIGN(String AUTOSIGN) {
        this.AUTOSIGN = AUTOSIGN;
    }

    public void start(){
        long startTime = System.currentTimeMillis();
        this.AAE030 = Long.toString(startTime);
    }

    public void end(){
        long endTime = System.currentTimeMillis();
        this.AAE031 = Long.toString(endTime);
    }
}
