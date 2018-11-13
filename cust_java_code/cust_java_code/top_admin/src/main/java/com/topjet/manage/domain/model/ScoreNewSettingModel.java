package com.topjet.manage.domain.model;

import java.util.Date;

public class ScoreNewSettingModel {
    private Integer id;

    private Integer ruleId;

    private Integer scoreType;

    private Integer controlCount;

    private Integer everyTimeScore;

    private Integer scoreMax;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private Integer valid;

    private Integer deleted;

    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getScoreType() {
        return scoreType;
    }

    public void setScoreType(Integer scoreType) {
        this.scoreType = scoreType;
    }

    public Integer getControlCount() {
        return controlCount;
    }

    public void setControlCount(Integer controlCount) {
        this.controlCount = controlCount;
    }

    public Integer getEveryTimeScore() {
        return everyTimeScore;
    }

    public void setEveryTimeScore(Integer everyTimeScore) {
        this.everyTimeScore = everyTimeScore;
    }

    public Integer getScoreMax() {
        return scoreMax;
    }

    public void setScoreMax(Integer scoreMax) {
        this.scoreMax = scoreMax;
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

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
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