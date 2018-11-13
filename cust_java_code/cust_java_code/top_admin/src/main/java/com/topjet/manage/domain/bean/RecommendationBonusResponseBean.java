package com.topjet.manage.domain.bean;



import com.topjet.common.ResultBaseMsg;

import java.util.Date;

public class RecommendationBonusResponseBean extends ResultBaseMsg {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7098547772782814094L;

	private Date createTime;

	private String startTime;

	private String endTime;

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

	private String recommendedIMEI;

	private Integer recommendedCount;

	private Integer id;

	private Integer row;

	private String returnMessage;

	private Integer successCount;

	private Integer failCount;
	
	private Integer  auditCycle ;
	
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

	public Integer getRecommendedCount() {
		return recommendedCount;
	}

	public void setRecommendedCount(Integer recommendedCount) {
		this.recommendedCount = recommendedCount;
	}

}
