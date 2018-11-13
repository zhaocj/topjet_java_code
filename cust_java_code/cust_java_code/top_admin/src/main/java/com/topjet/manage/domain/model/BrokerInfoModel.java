package com.topjet.manage.domain.model;

import java.util.Date;

public class BrokerInfoModel extends BaseModel{
    private Integer id;

    private Integer userId;

    private Integer brokerStatus;

    private Integer browseCount;

    private Integer calledCount;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

    private String mobile;

    private Integer beginCity;

    private Integer endCity;

    private String userName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBrokerStatus() {
        return brokerStatus;
    }

    public void setBrokerStatus(Integer brokerStatus) {
        this.brokerStatus = brokerStatus;
    }

    public Integer getBrowseCount() {
        return browseCount;
    }

    public void setBrowseCount(Integer browseCount) {
        this.browseCount = browseCount;
    }

    public Integer getCalledCount() {
        return calledCount;
    }

    public void setCalledCount(Integer calledCount) {
        this.calledCount = calledCount;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getBeginCity() {
        return beginCity;
    }

    public void setBeginCity(Integer beginCity) {
        this.beginCity = beginCity;
    }

    public Integer getEndCity() {
        return endCity;
    }

    public void setEndCity(Integer endCity) {
        this.endCity = endCity;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}