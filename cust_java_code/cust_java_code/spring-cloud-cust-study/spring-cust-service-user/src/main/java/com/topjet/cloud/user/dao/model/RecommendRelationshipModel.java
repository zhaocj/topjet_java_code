package com.topjet.cloud.user.dao.model;

import java.util.Date;

/**
 * Created by hongtaoer-win on 2017/8/8.
 * recommendRelationship 推荐人表映射model
 */
public class RecommendRelationshipModel {
    //ID
    private Integer id;
    //推荐人手机号
    private String recommendMobile;
    //被推荐人手机号
    private String recommendedMobile;
    //推荐人ID
    private Integer recommendUserId;
    //被推荐人ID
    private Integer recommendedUserId;
    //是否成功 1.是 0否
    private Integer success;
    //备注
    private String remark;
    //数据创建时间
    private Date createTime;
    //数据更新时间
    private Date updateTime;
    //删除状态
    private Integer deleted;

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

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
