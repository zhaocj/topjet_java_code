package com.topjet.manage.domain.bean;


import com.topjet.manage.domain.model.BaseModel;

public class ScoreBean extends BaseModel {

private int userId;
	
	private String idNo;

    private String name;

    private String mobile;

    private String type;

    private String imei;

    private String status;

    private String resident1;

    private String resident2;
    
    private String companyName;
    
    private String businessAddress;

    private String cause;

    private Integer value;

    private String auditName;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	
	
	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getAuditName() {
		return auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}
}
