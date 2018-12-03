package com.ylzinfo.app.model.old;

import com.ylzinfo.app.model.ValueObject;

public class ChargesQueryHelper extends ValueObject {
    private String hospADCode;
    private String insADCode;
    private String nowDay;

    public String getHospADCode() {
        return hospADCode;
    }

    public void setHospADCode(String hospADCode) {
        this.hospADCode = hospADCode;
    }

    public String getInsADCode() {
        return insADCode;
    }

    public void setInsADCode(String insADCode) {
        this.insADCode = insADCode;
    }

    public String getNowDay() {
        return nowDay;
    }

    public void setNowDay(String nowDay) {
        this.nowDay = nowDay;
    }

    public ChargesQueryHelper(String hospADCode, String insADCode, String nowDay) {
        this.hospADCode = hospADCode;
        this.insADCode = insADCode;
        this.nowDay = nowDay;
    }
}
