package com.topjet.manage.domain.model;

import java.math.BigDecimal;
import java.util.Date;

public class CallCenterLogModel {
    private Integer id;

    private String callNo;

    private String callSheetId;

    private String calledNo;

    private String callID;

    private String callType;

    private String recordFile;

    private Date ring;

    private Date begin;

    private Date end;

    private Date queueTime;

    private String queue;

    private String agent;

    private String exten;

    private String agentName;

    private String actionID;

    private String callState;

    private String state;

    private String fileServer;

    private String queueName;

    private String ivrkey;

    private String province;

    private String district;

    private String webcallActionID;

    private BigDecimal ringTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCallNo() {
        return callNo;
    }

    public void setCallNo(String callNo) {
        this.callNo = callNo;
    }

    public String getCallSheetId() {
        return callSheetId;
    }

    public void setCallSheetId(String callSheetId) {
        this.callSheetId = callSheetId;
    }

    public String getCalledNo() {
        return calledNo;
    }

    public void setCalledNo(String calledNo) {
        this.calledNo = calledNo;
    }

    public String getCallID() {
        return callID;
    }

    public void setCallID(String callID) {
        this.callID = callID;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getRecordFile() {
        return recordFile;
    }

    public void setRecordFile(String recordFile) {
        this.recordFile = recordFile;
    }

    public Date getRing() {
        return ring;
    }

    public void setRing(Date ring) {
        this.ring = ring;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getQueueTime() {
        return queueTime;
    }

    public void setQueueTime(Date queueTime) {
        this.queueTime = queueTime;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getExten() {
        return exten;
    }

    public void setExten(String exten) {
        this.exten = exten;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getActionID() {
        return actionID;
    }

    public void setActionID(String actionID) {
        this.actionID = actionID;
    }

    public String getCallState() {
        return callState;
    }

    public void setCallState(String callState) {
        this.callState = callState;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFileServer() {
        return fileServer;
    }

    public void setFileServer(String fileServer) {
        this.fileServer = fileServer;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getIvrkey() {
        return ivrkey;
    }

    public void setIvrkey(String ivrkey) {
        this.ivrkey = ivrkey;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWebcallActionID() {
        return webcallActionID;
    }

    public void setWebcallActionID(String webcallActionID) {
        this.webcallActionID = webcallActionID;
    }

    public BigDecimal getRingTime() {
        return ringTime;
    }

    public void setRingTime(BigDecimal ringTime) {
        this.ringTime = ringTime;
    }
}