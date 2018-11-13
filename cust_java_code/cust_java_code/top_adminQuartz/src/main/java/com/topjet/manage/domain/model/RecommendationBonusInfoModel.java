package com.topjet.manage.domain.model;

import java.math.BigDecimal;
import java.util.Date;

public class RecommendationBonusInfoModel {
    private Integer id;

    private Integer recommendationId;

    private Integer userId;

    private BigDecimal amount;

    private Integer auditProcess;

    private Integer auditStatus;

    private String auditUser;

    private Integer auditUserId;

    private Integer settingId;

    private Integer firstCallCount;

    private Integer secondCallCount;

    private Integer pendingCallCount;

    private Date createTime;

    private Short version;

    private Integer deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(Integer recommendationId) {
        this.recommendationId = recommendationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public String getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser;
    }

    public Integer getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(Integer auditUserId) {
        this.auditUserId = auditUserId;
    }

    public Integer getSettingId() {
        return settingId;
    }

    public void setSettingId(Integer settingId) {
        this.settingId = settingId;
    }

    public Integer getFirstCallCount() {
        return firstCallCount;
    }

    public void setFirstCallCount(Integer firstCallCount) {
        this.firstCallCount = firstCallCount;
    }

    public Integer getSecondCallCount() {
        return secondCallCount;
    }

    public void setSecondCallCount(Integer secondCallCount) {
        this.secondCallCount = secondCallCount;
    }

    public Integer getPendingCallCount() {
        return pendingCallCount;
    }

    public void setPendingCallCount(Integer pendingCallCount) {
        this.pendingCallCount = pendingCallCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Short getVersion() {
        return version;
    }

    public void setVersion(Short version) {
        this.version = version;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}