package com.topjet.manage.domain.bean;


import com.topjet.manage.domain.model.BaseModel;

import java.util.Date;

public class RecommendationBonusBean extends BaseModel {

	/**
	 * 
	 */

	private Date createTime;

	private String startTime;

	private String endTime;

	private Integer auditProcess;

	private Integer auditStatus;

	private String firstAuditStatus;

	private String firstAuditUser;

	private Date firstAuditTime;

	private String secondAuditStatus;

	private String secondAuditUser;

	private Date secondAuditTime;

	private String thirdAuditStatus;

	private String thirdAuditUser;

	private Date thirdAuditTime;

	private String thirdAuditRemark;

	private Integer version;

	private Integer deleted;

	private String firstAuditRemark;

	private String secondAuditRemark;

	private Integer userId;

	private Integer recommendUserId;

	private Integer recommendedUserId;

	private String recommendMobile;

	private String recommendedMobile;

	private String recommendName;

	private String recommendedName;



	private String	recommendedIdNo;

	private String recommendedIMEI;

	private Integer id;

	private Integer row;

	private String returnMessage;

	private Integer successCount;

	private Integer failCount;
	
	private Integer  auditCycle ;

	private String isValid;

	private String userType;

	private String flag;//标记是否为分配任务客服

	private String sysUserId;//任务分配者ID

	private  String assignName;

	private  String isFirstCallCount;//一审是否回访

    private  String  isSecondCallCount;//二审是否回访

	private String auditName;//审核人

	public Integer getAuditProcess() {
		return auditProcess;
	}

	public void setAuditProcess(Integer auditProcess) {
		this.auditProcess = auditProcess;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getAuditName() {
		return auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public String getThirdAuditStatus() {
		return thirdAuditStatus;
	}

	public void setThirdAuditStatus(String thirdAuditStatus) {
		this.thirdAuditStatus = thirdAuditStatus;
	}

	public String getThirdAuditUser() {
		return thirdAuditUser;
	}

	public void setThirdAuditUser(String thirdAuditUser) {
		this.thirdAuditUser = thirdAuditUser;
	}

	public Date getThirdAuditTime() {
		return thirdAuditTime;
	}

	public void setThirdAuditTime(Date thirdAuditTime) {
		this.thirdAuditTime = thirdAuditTime;
	}

	public String getThirdAuditRemark() {
		return thirdAuditRemark;
	}

	public void setThirdAuditRemark(String thirdAuditRemark) {
		this.thirdAuditRemark = thirdAuditRemark;
	}

	public String getIsSecondCallCount() {
		return isSecondCallCount;
	}

	public void setIsSecondCallCount(String isSecondCallCount) {
		this.isSecondCallCount = isSecondCallCount;
	}

	public String getIsFirstCallCount() {
		return isFirstCallCount;
	}

	public void setIsFirstCallCount(String isFirstCallCount) {
		this.isFirstCallCount = isFirstCallCount;
	}

	private String firstCallCount;

	private String secondCallCount;

	private String pendingCallCount;

	public String getFirstCallCount() {
		return firstCallCount;
	}

	public void setFirstCallCount(String firstCallCount) {
		this.firstCallCount = firstCallCount;
	}

	public String getSecondCallCount() {
		return secondCallCount;
	}

	public void setSecondCallCount(String secondCallCount) {
		this.secondCallCount = secondCallCount;
	}

	public String getPendingCallCount() {
		return pendingCallCount;
	}

	public void setPendingCallCount(String pendingCallCount) {
		this.pendingCallCount = pendingCallCount;
	}

	public String getAssignName() {
		return assignName;
	}

	public void setAssignName(String assignName) {
		this.assignName = assignName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public Integer getAuditCycle() {
		return auditCycle;
	}

	public void setAuditCycle(Integer auditCycle) {
		this.auditCycle = auditCycle;
	}

	public Integer getId() {
		return id;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public Integer getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}

	public Integer getFailCount() {
		return failCount;
	}

	public void setFailCount(Integer failCount) {
		this.failCount = failCount;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getFirstAuditStatus() {
		return firstAuditStatus;
	}

	public void setFirstAuditStatus(String firstAuditStatus) {
		this.firstAuditStatus = firstAuditStatus;
	}

	public String getFirstAuditUser() {
		return firstAuditUser;
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

	public void setFirstAuditUser(String firstAuditUser) {
		this.firstAuditUser = firstAuditUser;
	}

	public Date getFirstAuditTime() {
		return firstAuditTime;
	}

	public void setFirstAuditTime(Date firstAuditTime) {
		this.firstAuditTime = firstAuditTime;
	}

	public String getSecondAuditStatus() {
		return secondAuditStatus;
	}

	public void setSecondAuditStatus(String secondAuditStatus) {
		this.secondAuditStatus = secondAuditStatus;
	}

	public String getSecondAuditUser() {
		return secondAuditUser;
	}

	public void setSecondAuditUser(String secondAuditUser) {
		this.secondAuditUser = secondAuditUser;
	}

	public Date getSecondAuditTime() {
		return secondAuditTime;
	}

	public void setSecondAuditTime(Date secondAuditTime) {
		this.secondAuditTime = secondAuditTime;
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

	public String getFirstAuditRemark() {
		return firstAuditRemark;
	}

	public void setFirstAuditRemark(String firstAuditRemark) {
		this.firstAuditRemark = firstAuditRemark;
	}

	public String getSecondAuditRemark() {
		return secondAuditRemark;
	}

	public void setSecondAuditRemark(String secondAuditRemark) {
		this.secondAuditRemark = secondAuditRemark;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRecommendUserId() {
		return recommendUserId;
	}

	public void setRecommendUserId(Integer recommendUserId) {
		this.recommendUserId = recommendUserId;
	}

	public Integer getRecommendedUserId() {
		return recommendedUserId;
	}

	public void setRecommendedUserId(Integer recommendedUserId) {
		this.recommendedUserId = recommendedUserId;
	}

	public String getRecommendMobile() {
		return recommendMobile;
	}

	public void setRecommendMobile(String recommendMobile) {
		this.recommendMobile = recommendMobile;
	}

	public String getRecommendedMobile() {
		return recommendedMobile;
	}

	public void setRecommendedMobile(String recommendedMobile) {
		this.recommendedMobile = recommendedMobile;
	}

	public String getRecommendName() {
		return recommendName;
	}

	public void setRecommendName(String recommendName) {
		this.recommendName = recommendName;
	}

	public String getRecommendedName() {
		return recommendedName;
	}

	public void setRecommendedName(String recommendedName) {
		this.recommendedName = recommendedName;
	}

	public String getRecommendedIMEI() {
		return recommendedIMEI;
	}

	public void setRecommendedIMEI(String recommendedIMEI) {
		this.recommendedIMEI = recommendedIMEI;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}
	public String getRecommendedIdNo() {
		return recommendedIdNo;
	}

	public void setRecommendedIdNo(String recommendedIdNo) {
		this.recommendedIdNo = recommendedIdNo;
	}
}
