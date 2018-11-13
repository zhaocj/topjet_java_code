package com.topjet.manage.domain.model;

import java.util.Date;

public class BrokerRouteInfoModel {
    private Integer id;

    private Integer brokerId;

    private Integer beginCity;

    private Integer endCity;

    private Integer rank;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(Integer brokerId) {
        this.brokerId = brokerId;
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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
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