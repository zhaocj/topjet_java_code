package com.topjet.manage.domain.bean;


import com.topjet.manage.domain.model.BaseModel;

import java.util.Date;

/**
 * Created by zhouyw on 2016/10/09.
 * 用于用户管理请求参数
 */
public class MemberListRequestBean extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1816328392261905936L;

	private Integer id;

	private String idNo;

	private String auditName;

	private String idAuditName;

	private String headAuditName;

	private String mobile;

	private String userType;

	private String useStatus;

	private String resident1;

	private String resident2;

	private Integer memberStatus;

	private Integer flag;

	private Date beginDate;

	private Date endDate;

	private String userAuthStatus;

	private String isSolve;

	private String iconStatus;

	private String urlTobeFlag;

	private Integer residentStart;

	private Integer residentEnd;
	private Integer sourceType;//审核类型
	private String auditAllName;//审核人

	public String getAuditAllName() {
		return auditAllName;
	}

	public void setAuditAllName(String auditAllName) {
		this.auditAllName = auditAllName;
	}

	public String getIconStatus() {
		return iconStatus;
	}

	public void setIconStatus(String iconStatus) {
		this.iconStatus = iconStatus;
	}

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	public Integer getResidentStart() {
		return residentStart;
	}

	public void setResidentStart(Integer residentStart) {
		this.residentStart = residentStart;
	}

	public Integer getResidentEnd() {
		return residentEnd;
	}

	public void setResidentEnd(Integer residentEnd) {
		this.residentEnd = residentEnd;
	}

	public String getUrlTobeFlag() {
		return urlTobeFlag;
	}

	public void setUrlTobeFlag(String urlTobeFlag) {
		this.urlTobeFlag = urlTobeFlag;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getAuditName() {
		return auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public String getResident1() {
		return resident1;
	}

	public void setResident1(String resident1) {
		this.resident1 = resident1;
	}

	public String getResident2() {
		return resident2;
	}

	public void setResident2(String resident2) {
		this.resident2 = resident2;
	}

	public Integer getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(Integer memberStatus) {
		this.memberStatus = memberStatus;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getUserAuthStatus() {
		return userAuthStatus;
	}

	public void setUserAuthStatus(String userAuthStatus) {
		this.userAuthStatus = userAuthStatus;
	}

	public String getIdAuditName() {
		return idAuditName;
	}

	public void setIdAuditName(String idAuditName) {
		this.idAuditName = idAuditName;
	}

	public String getHeadAuditName() {
		return headAuditName;
	}

	public void setHeadAuditName(String headAuditName) {
		this.headAuditName = headAuditName;
	}

	public String getIsSolve() {
		return isSolve;
	}

	public void setIsSolve(String isSolve) {
		this.isSolve = isSolve;
	}
}
