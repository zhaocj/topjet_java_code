package com.topjet.cloud.user.dao.model;

import java.util.Date;

/**
 * Created by hongtaoer-win on 2017/8/8.
 * userPushTokenInfo 推送token映射model
 */
public class UserPushTokenInfoModel {
    //ID
    private Integer id;
    //用户ID
    private Integer userId;
    //用户推送token
    private String userToken;
    //数据创建时间
    private Date createTime;
    //数据更新时间
    private Date updateTime;
    //删除标记
    private Integer deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
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
