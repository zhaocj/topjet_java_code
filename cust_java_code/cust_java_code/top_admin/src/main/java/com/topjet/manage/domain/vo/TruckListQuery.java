package com.topjet.manage.domain.vo;

import com.topjet.manage.domain.model.BaseModel;

import java.util.Date;

/**
 * Created by Tym on 2016/8/25.
 */
public class TruckListQuery extends BaseModel{
    private String mobile;//手机
    private String plateNo;//车辆号
    private String auditStatus;//认证状态: 1 未认证 2 已认证 3 认证中 4 认证失败
    private String type;//车型名称
    private Date startDate;//开始时间
    private Date endDate;//结束时间
    private String auditUser;//审核人

    private String username;//车主姓名
    private String createTime;//添加车辆时间
    private String typeAndLength;//车型车长
    private String orderCount;//运输次数
    private String truckAge;//年限
    private String truckColor;//颜色
    private String status;//认证状态
    private String plateNo1;
    private String plateNo2;
    private String plateNo3;
    private Integer uid;
    private Integer driverId;
    private Integer id;
    private String reditRemark;

    public String getReditRemark() {
        return reditRemark;
    }

    public void setReditRemark(String reditRemark) {
        this.reditRemark = reditRemark;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTypeAndLength() {
        return typeAndLength;
    }

    public void setTypeAndLength(String typeAndLength) {
        this.typeAndLength = typeAndLength;
    }

    public String getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(String orderCount) {
        this.orderCount = orderCount;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser;
    }
    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
