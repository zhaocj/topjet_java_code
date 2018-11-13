package com.topjet.manage.domain.model;

import java.math.BigDecimal;
import java.util.Date;

public class RefundInfoModel extends BaseModel{
    private Integer id;

    private Integer billId;

    private BigDecimal refundMoney;

    private Integer orderId;

    private Integer status;

    private Integer csStatus;

    private Integer triggerId;

    private Integer reason;

    private Integer orderStatus;

    private String remark;

    private String userRemark;

    private Integer repulseReason;

    private String repulseRemark;

    private Date createTime;

    private Date updateTime;

    private Integer version;

    private Integer deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public BigDecimal getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(BigDecimal refundMoney) {
        this.refundMoney = refundMoney;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCsStatus() {
        return csStatus;
    }

    public void setCsStatus(Integer csStatus) {
        this.csStatus = csStatus;
    }

    public Integer getTriggerId() {
        return triggerId;
    }

    public void setTriggerId(Integer triggerId) {
        this.triggerId = triggerId;
    }

    public Integer getReason() {
        return reason;
    }

    public void setReason(Integer reason) {
        this.reason = reason;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    public Integer getRepulseReason() {
        return repulseReason;
    }

    public void setRepulseReason(Integer repulseReason) {
        this.repulseReason = repulseReason;
    }

    public String getRepulseRemark() {
        return repulseRemark;
    }

    public void setRepulseRemark(String repulseRemark) {
        this.repulseRemark = repulseRemark;
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


    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}