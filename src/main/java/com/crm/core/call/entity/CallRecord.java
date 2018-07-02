package com.crm.core.call.entity;

import java.util.Date;

/**
 * Created by miku03 on 2018/5/21.
 */
public class CallRecord {
    private String callId;
    private String appId;
    private String caller;
    private String callerId;
    private String callerName;
    private String called;
    private String calledId;
    private String calledName;
    private Date startTime;
    private Date endTime;
    private Integer TotalMins;
    private String recordUrl;
    private String callType;
    private String status;
    private String dtmfType;
    private String dtmfCode;
    private String data;
    private String tcFailCode;
    private String times;

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getTcFailCode() {
        return tcFailCode;
    }

    public void setTcFailCode(String tcFailCode) {
        this.tcFailCode = tcFailCode;
    }

    public String getCallerId() {
        return callerId;
    }

    public void setCallerId(String callerId) {
        this.callerId = callerId;
    }

    public String getCalledId() {
        return calledId;
    }

    public void setCalledId(String calledId) {
        this.calledId = calledId;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public String getCalled() {
        return called;
    }

    public void setCalled(String called) {
        this.called = called;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getTotalMins() {
        return TotalMins;
    }

    public void setTotalMins(Integer totalMins) {
        TotalMins = totalMins;
    }

    public String getRecordUrl() {
        return recordUrl;
    }

    public void setRecordUrl(String recordUrl) {
        this.recordUrl = recordUrl;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDtmfType() {
        return dtmfType;
    }

    public void setDtmfType(String dtmfType) {
        this.dtmfType = dtmfType;
    }

    public String getDtmfCode() {
        return dtmfCode;
    }

    public void setDtmfCode(String dtmfCode) {
        this.dtmfCode = dtmfCode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCallerName() {
        return callerName;
    }

    public void setCallerName(String callerName) {
        this.callerName = callerName;
    }

    public String getCalledName() {
        return calledName;
    }

    public void setCalledName(String calledName) {
        this.calledName = calledName;
    }
}
