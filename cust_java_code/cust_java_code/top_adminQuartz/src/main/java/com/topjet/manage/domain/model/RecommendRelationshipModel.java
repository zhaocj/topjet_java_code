package com.topjet.manage.domain.model;

import java.util.Date;

public class RecommendRelationshipModel {
    private Integer id;

    private String recommendMobile;

    private String recommendedMobile;

    private Integer recommendUserId;

    private Integer recommendedUserId;

    private Byte success;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Byte deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecommendMobile() {
        return recommendMobile;
    }

    public void setRecommendMobile(String recommendMobile) {
        this.recommendMobile = recommendMobile;
    }

    public String getRecommendedMobile() {
        return recommendedMobile;
    }

    public void setRecommendedMobile(String recommendedMobile) {
        this.recommendedMobile = recommendedMobile;
    }

    public Integer getRecommendUserId() {
        return recommendUserId;
    }

    public void setRecommendUserId(Integer recommendUserId) {
        this.recommendUserId = recommendUserId;
    }

    public Integer getRecommendedUserId() {
        return recommendedUserId;
    }

    public void setRecommendedUserId(Integer recommendedUserId) {
        this.recommendedUserId = recommendedUserId;
    }

    public Byte getSuccess() {
        return success;
    }

    public void setSuccess(Byte success) {
        this.success = success;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }
}