package com.topjet.manage.domain.bean;


import com.topjet.manage.domain.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

public class BonusBillInfoModelBean extends BaseModel {
    private Integer id;

    private String billNo;

    private Integer payerId;

    private Integer billType;

    private Integer status;
    
    private Date createTime;

    private Date updateTime;

    private BigDecimal amount;
    
    private Integer createBy;
    
    private Integer deleted;

    private Integer sourceType;

    private Integer sourceId;
    
    private Integer version;

    private Integer payeeId;

    private Integer walletStatus;

    private Integer billPayType;

    private static final long serialVersionUID = 1L;

    public BonusBillInfoModelBean(Integer id, String billNo, Integer payerId, Integer billType, Integer status, Date createTime, Date updateTime, BigDecimal amount, Integer createBy, Integer deleted, Integer sourceType, Integer sourceId, Integer version, Integer payeeId, Integer walletStatus, Integer billPayType) {
        this.id = id;
        this.billNo = billNo;
        this.payerId = payerId;
        this.billType = billType;
        this.status = status;
        this.amount = amount;
        this.sourceType = sourceType;
        this.sourceId = sourceId;
        this.payeeId = payeeId;
        this.walletStatus = walletStatus;
        this.billPayType = billPayType;
        this.createTime = createTime;
        this.createBy = createBy;
        this.updateTime = updateTime;
        this.version = version;
        this.deleted = deleted;
    }

    public BonusBillInfoModelBean() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo == null ? null : billNo.trim();
    }

    public Integer getPayerId() {
        return payerId;
    }

    public void setPayerId(Integer payerId) {
        this.payerId = payerId;
    }

    public Integer getBillType() {
        return billType;
    }

    public void setBillType(Integer billType) {
        this.billType = billType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(Integer payeeId) {
        this.payeeId = payeeId;
    }

    public Integer getWalletStatus() {
        return walletStatus;
    }

    public void setWalletStatus(Integer walletStatus) {
        this.walletStatus = walletStatus;
    }

    public Integer getBillPayType() {
        return billPayType;
    }

    public void setBillPayType(Integer billPayType) {
        this.billPayType = billPayType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
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