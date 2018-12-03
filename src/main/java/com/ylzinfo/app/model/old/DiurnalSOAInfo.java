package com.ylzinfo.app.model.old;

public class DiurnalSOAInfo extends ValueObject {

    private String hospADCode;
    private String insADCode;
    private double totalCost;
    private double persCashPayment;
    private double medicInsFundPayment;
    private String cutOffTime;
    private String senderMessageID;
    private String recipientMessageID;
    private String typeOfTransaction;
    private String AD;
    private String settlementSerialNum;

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

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getPersCashPayment() {
        return persCashPayment;
    }

    public void setPersCashPayment(double persCashPayment) {
        this.persCashPayment = persCashPayment;
    }

    public double getMedicInsFundPayment() {
        return medicInsFundPayment;
    }

    public void setMedicInsFundPayment(double medicInsFundPayment) {
        this.medicInsFundPayment = medicInsFundPayment;
    }

    public String getCutOffTime() {
        return cutOffTime;
    }

    public void setCutOffTime(String cutOffTime) {
        this.cutOffTime = cutOffTime;
    }

    public String getSenderMessageID() {
        return senderMessageID;
    }

    public void setSenderMessageID(String senderMessageID) {
        this.senderMessageID = senderMessageID;
    }

    public String getRecipientMessageID() {
        return recipientMessageID;
    }

    public void setRecipientMessageID(String recipientMessageID) {
        this.recipientMessageID = recipientMessageID;
    }

    public String getTypeOfTransaction() {
        return typeOfTransaction;
    }

    public void setTypeOfTransaction(String typeOfTransaction) {
        this.typeOfTransaction = typeOfTransaction;
    }

    public String getAD() {
        return AD;
    }

    public void setAD(String AD) {
        this.AD = AD;
    }

    public String getSettlementSerialNum() {
        return settlementSerialNum;
    }

    public void setSettlementSerialNum(String settlementSerialNum) {
        this.settlementSerialNum = settlementSerialNum;
    }



}
