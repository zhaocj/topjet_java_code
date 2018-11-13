package com.topjet.manage.domain.bean;

/**
 * Created by Tym on 2016/8/26.
 */
public class TruckEditResponseBean {
    private Integer id;
    private String username;
    private String mobile;
    private String userCreateTime;
    private String truckCreateTime;
    private String plateNo;
    private String truckColor;
    private String truckAge;
    private String drivingLicensePhotoUrl;//行驶证照片
    private String  truckPhotoUrl;//车辆照片
    private Integer TruckTypeId;
    private Integer version;
    private Integer auditStatus;//认证状态1 未认证 2 已认证 3 认证中 4 认证失败',
    private String operatorRemark;//管理平台操作备注
    private Integer truckLength;//车长id
    private Integer truckType;//车型id
    private String plateNo1;
    private String plateNo2;
    private String plateNo3;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPlateNo1() {
        return plateNo1;
    }

    public void setPlateNo1(String plateNo1) {
        this.plateNo1 = plateNo1;
    }

    public String getPlateNo2() {
        return plateNo2;
    }

    public void setPlateNo2(String plateNo2) {
        this.plateNo2 = plateNo2;
    }

    public String getPlateNo3() {
        return plateNo3;
    }

    public void setPlateNo3(String plateNo3) {
        this.plateNo3 = plateNo3;
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

    public String getOperatorRemark() {
        return operatorRemark;
    }

    public void setOperatorRemark(String operatorRemark) {
        this.operatorRemark = operatorRemark;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getDrivingLicensePhotoUrl() {
        return drivingLicensePhotoUrl;
    }

    public void setDrivingLicensePhotoUrl(String drivingLicensePhotoUrl) {
        this.drivingLicensePhotoUrl = drivingLicensePhotoUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getTruckAge() {
        return truckAge;
    }

    public void setTruckAge(String truckAge) {
        this.truckAge = truckAge;
    }

    public String getTruckColor() {
        return truckColor;
    }

    public void setTruckColor(String truckColor) {
        this.truckColor = truckColor;
    }

    public String getTruckCreateTime() {
        return truckCreateTime;
    }

    public void setTruckCreateTime(String truckCreateTime) {
        this.truckCreateTime = truckCreateTime;
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

    public String getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(String userCreateTime) {
        this.userCreateTime = userCreateTime;
    }
}
