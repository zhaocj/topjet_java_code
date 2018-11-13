package com.topjet.manage.domain.model;

import java.util.Date;

public class DriverBusinessLineInfoModel {
    private Integer id;

    private Integer driverId;

    private String businessLine1;

    private String businessLine2;

    private String businessLine3;

    private Integer businessLineCityId;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getBusinessLine1() {
        return businessLine1;
    }

    public void setBusinessLine1(String businessLine1) {
        this.businessLine1 = businessLine1;
    }

    public String getBusinessLine2() {
        return businessLine2;
    }

    public void setBusinessLine2(String businessLine2) {
        this.businessLine2 = businessLine2;
    }

    public String getBusinessLine3() {
        return businessLine3;
    }

    public void setBusinessLine3(String businessLine3) {
        this.businessLine3 = businessLine3;
    }

    public Integer getBusinessLineCityId() {
        return businessLineCityId;
    }

    public void setBusinessLineCityId(Integer businessLineCityId) {
        this.businessLineCityId = businessLineCityId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}