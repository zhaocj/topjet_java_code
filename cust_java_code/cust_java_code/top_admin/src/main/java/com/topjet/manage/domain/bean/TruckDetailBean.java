package com.topjet.manage.domain.bean;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-09-18 10:15
 */

public class TruckDetailBean {
    private Integer id;

    private String name;

    private String mobile;

    private String userCreateTime;

    private String truckCreateTime;

    private String plateNo;

    private String truckColor;

    private String truckAge;

    private String drivingLicensePhotoUrl;//行驶证照片

    private String  truckPhotoUrl;//车辆照片

    private Integer TruckTypeId;

    private Integer auditStatus;//认证状态1 未认证 2 已认证 3 认证中 4 认证失败',

    private String operatorRemark;//管理平台操作备注

    private Integer truckLength;//车长id

    private Integer truckType;//车型id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(String userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public String getTruckCreateTime() {
        return truckCreateTime;
    }

    public void setTruckCreateTime(String truckCreateTime) {
        this.truckCreateTime = truckCreateTime;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getTruckColor() {
        return truckColor;
    }

    public void setTruckColor(String truckColor) {
        this.truckColor = truckColor;
    }

    public String getTruckAge() {
        return truckAge;
    }

    public void setTruckAge(String truckAge) {
        this.truckAge = truckAge;
    }

    public String getDrivingLicensePhotoUrl() {
        return drivingLicensePhotoUrl;
    }

    public void setDrivingLicensePhotoUrl(String drivingLicensePhotoUrl) {
        this.drivingLicensePhotoUrl = drivingLicensePhotoUrl;
    }

    public String getTruckPhotoUrl() {
        return truckPhotoUrl;
    }

    public void setTruckPhotoUrl(String truckPhotoUrl) {
        this.truckPhotoUrl = truckPhotoUrl;
    }

    public Integer getTruckTypeId() {
        return TruckTypeId;
    }

    public void setTruckTypeId(Integer truckTypeId) {
        TruckTypeId = truckTypeId;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getOperatorRemark() {
        return operatorRemark;
    }

    public void setOperatorRemark(String operatorRemark) {
        this.operatorRemark = operatorRemark;
    }

    public Integer getTruckLength() {
        return truckLength;
    }

    public void setTruckLength(Integer truckLength) {
        this.truckLength = truckLength;
    }

    public Integer getTruckType() {
        return truckType;
    }

    public void setTruckType(Integer truckType) {
        this.truckType = truckType;
    }
}
