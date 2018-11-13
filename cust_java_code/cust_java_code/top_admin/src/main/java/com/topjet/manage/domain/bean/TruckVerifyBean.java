package com.topjet.manage.domain.bean;

/**
 * @Author: zhaocj
 * @Description: 车辆认证审核传参
 * @Date: 2017-09-20 10:33
 */

public class TruckVerifyBean {

    private Integer id;

    private String  plateNo;

    private Integer truckColor;

    private Integer truckAge;

    private Integer truckType;

    private Integer truckLength;

    private Integer auditStatus;

    private String operatorRemark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public Integer getTruckColor() {
        return truckColor;
    }

    public void setTruckColor(Integer truckColor) {
        this.truckColor = truckColor;
    }

    public Integer getTruckAge() {
        return truckAge;
    }

    public void setTruckAge(Integer truckAge) {
        this.truckAge = truckAge;
    }

    public Integer getTruckType() {
        return truckType;
    }

    public void setTruckType(Integer truckType) {
        this.truckType = truckType;
    }

    public Integer getTruckLength() {
        return truckLength;
    }

    public void setTruckLength(Integer truckLength) {
        this.truckLength = truckLength;
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
}
