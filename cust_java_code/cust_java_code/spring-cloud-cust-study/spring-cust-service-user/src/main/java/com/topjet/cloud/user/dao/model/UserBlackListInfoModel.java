package com.topjet.cloud.user.dao.model;

import java.util.Date;

/**
 * Created by zhangn on 2017/8/17.
 */
public class UserBlackListInfoModel {

    private Integer id;

    private Integer userId;

    private Integer bType;

    private Date createTime;

    private Date updateTime;

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

    public Integer getbType() {
        return bType;
    }

    public void setbType(Integer bType) {
        this.bType = bType;
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
