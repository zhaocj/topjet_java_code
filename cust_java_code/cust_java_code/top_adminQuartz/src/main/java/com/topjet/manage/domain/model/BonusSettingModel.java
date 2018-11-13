package com.topjet.manage.domain.model;

import java.math.BigDecimal;
import java.util.Date;

public class BonusSettingModel{
    private Integer id;

    private Integer bonusType;

    private Integer bonusTarget;

    private BigDecimal everyTimeAmount;

    private Integer amountType;

    private BigDecimal bonusScopeMin;

    private BigDecimal bonusScopeMax;

    private Integer createBy;

    private Date createTime;

    private Integer deleted;

    private Integer version;

    private static final long serialVersionUID = 1L;

    public BonusSettingModel(Integer id, Integer bonusType, Integer bonusTarget, BigDecimal everyTimeAmount, Integer amountType, BigDecimal bonusScopeMin, BigDecimal bonusScopeMax, Integer createBy, Date createTime, Integer deleted, Integer version) {
        this.id = id;
        this.bonusType = bonusType;
        this.bonusTarget = bonusTarget;
        this.everyTimeAmount = everyTimeAmount;
        this.amountType = amountType;
        this.bonusScopeMin = bonusScopeMin;
        this.bonusScopeMax = bonusScopeMax;
        this.createBy = createBy;
        this.createTime = createTime;
        this.deleted = deleted;
        this.version = version;
    }

    public BonusSettingModel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBonusType() {
        return bonusType;
    }

    public void setBonusType(Integer bonusType) {
        this.bonusType = bonusType;
    }

    public Integer getBonusTarget() {
        return bonusTarget;
    }

    public void setBonusTarget(Integer bonusTarget) {
        this.bonusTarget = bonusTarget;
    }

    public BigDecimal getEveryTimeAmount() {
        return everyTimeAmount;
    }

    public void setEveryTimeAmount(BigDecimal everyTimeAmount) {
        this.everyTimeAmount = everyTimeAmount;
    }

    public Integer getAmountType() {
        return amountType;
    }

    public void setAmountType(Integer amountType) {
        this.amountType = amountType;
    }

    public BigDecimal getBonusScopeMin() {
        return bonusScopeMin;
    }

    public void setBonusScopeMin(BigDecimal bonusScopeMin) {
        this.bonusScopeMin = bonusScopeMin;
    }

    public BigDecimal getBonusScopeMax() {
        return bonusScopeMax;
    }

    public void setBonusScopeMax(BigDecimal bonusScopeMax) {
        this.bonusScopeMax = bonusScopeMax;
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}