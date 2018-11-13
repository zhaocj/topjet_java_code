package com.topjet.manage.domain.model;

import java.math.BigDecimal;
import java.util.Date;

public class OrderBonusInfoModel extends BaseModel{
    private Integer id;

    private Integer orderId;

    private Integer driverId;

    private Integer ownerId;

    private Integer type;

    private BigDecimal driverAmount;

    private BigDecimal ownerAmount;

    private Integer auditProcess;

    private Integer auditStatus;

    private Integer settingId;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private Integer version;

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

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getDriverAmount() {
        return driverAmount;
    }

    public void setDriverAmount(BigDecimal driverAmount) {
        this.driverAmount = driverAmount;
    }

    public BigDecimal getOwnerAmount() {
        return ownerAmount;
    }

    public void setOwnerAmount(BigDecimal ownerAmount) {
        this.ownerAmount = ownerAmount;
    }

    public Integer getAuditProcess() {
        return auditProcess;
    }

    public void setAuditProcess(Integer auditProcess) {
        this.auditProcess = auditProcess;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getSettingId() {
        return settingId;
    }

    public void setSettingId(Integer settingId) {
        this.settingId = settingId;
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