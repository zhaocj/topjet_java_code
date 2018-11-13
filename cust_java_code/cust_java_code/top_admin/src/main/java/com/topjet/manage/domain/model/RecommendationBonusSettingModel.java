package com.topjet.manage.domain.model;

import java.math.BigDecimal;
import java.util.Date;

public class RecommendationBonusSettingModel {
    private Integer id;

    private BigDecimal amountPerPerson;

    private BigDecimal maxAmountPerDay;

    private Integer bonusCount;

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

    public BigDecimal getAmountPerPerson() {
        return amountPerPerson;
    }

    public void setAmountPerPerson(BigDecimal amountPerPerson) {
        this.amountPerPerson = amountPerPerson;
    }

    public BigDecimal getMaxAmountPerDay() {
        return maxAmountPerDay;
    }

    public void setMaxAmountPerDay(BigDecimal maxAmountPerDay) {
        this.maxAmountPerDay = maxAmountPerDay;
    }

    public Integer getBonusCount() {
        return bonusCount;
    }

    public void setBonusCount(Integer bonusCount) {
        this.bonusCount = bonusCount;
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