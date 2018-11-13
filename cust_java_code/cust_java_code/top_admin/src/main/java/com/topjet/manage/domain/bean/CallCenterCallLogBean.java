package com.topjet.manage.domain.bean;


import com.topjet.manage.domain.model.BaseModel;

import java.util.Date;

public class CallCenterCallLogBean extends BaseModel {

	private Integer id;

	private String callSheetId;

	private String originCallNo;

	private String originCalledNo;

	private String callType;

	private String status;

	private String state;

	private String ringTime;

	private String beginTime;

	private String endTime;

	private Integer businessType;

	private String agent;

	private String queue;

	private String queueName;

	private String monitorFilename;

	private String agentName;

	private String remark;

	private Date createTime;

	private Integer createBy;

	private Date updateTime;

	private Integer updateBy;

	private Integer version;

	private Integer deleted;

	private String createByName;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCallSheetId() {
		return callSheetId;
	}

	public void setCallSheetId(String callSheetId) {
		this.callSheetId = callSheetId;
	}

	public String getOriginCallNo() {
		return originCallNo;
	}

	public void setOriginCallNo(String originCallNo) {
		this.originCallNo = originCallNo;
	}

	public String getOriginCalledNo() {
		return originCalledNo;
	}

	public void setOriginCalledNo(String originCalledNo) {
		this.originCalledNo = originCalledNo;
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

	public String getRingTime() {
		return ringTime;
	}

	public void setRingTime(String ringTime) {
		this.ringTime = ringTime;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getQueue() {
		return queue;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getMonitorFilename() {
		return monitorFilename;
	}

	public void setMonitorFilename(String monitorFilename) {
		this.monitorFilename = monitorFilename;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}
}
