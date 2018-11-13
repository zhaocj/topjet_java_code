package com.topjet.manage.domain.model;

import java.util.Date;

public class OrderAffiliateModel {
    private Integer id;

    private Integer orderId;

    private Integer orderFrozenState;

    private Integer version;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private Integer deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderFrozenState() {
        return orderFrozenState;
    }

    public void setOrderFrozenState(Integer orderFrozenState) {
        this.orderFrozenState = orderFrozenState;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


}