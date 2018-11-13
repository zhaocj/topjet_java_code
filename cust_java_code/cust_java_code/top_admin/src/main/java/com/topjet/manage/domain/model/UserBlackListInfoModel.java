package com.topjet.manage.domain.model;

import java.util.Date;

public class UserBlackListInfoModel extends BaseModel {
    private Integer id;

    private Integer userId;

    private Integer bType;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

    private static final long serialVersionUID = 1L;

    public UserBlackListInfoModel(Integer id, Integer userId, Integer bType, Date createTime, Date updateTime, Integer deleted) {
        this.id = id;
        this.userId = userId;
        this.bType = bType;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleted = deleted;
    }

    public UserBlackListInfoModel() {
        super();
    }

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

    public Integer getbType() {
        return bType;
    }

    public void setbType(Integer bType) {
        this.bType = bType;
    }
}