package com.topjet.manage.domain.model;

import java.util.Date;

public class OwnerTruckInviteInfoModel {
    private Integer id;

    private Integer truckId;

    private String dirverMobile;

    private Integer used;

    private Integer ownerId;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public String getDirverMobile() {
        return dirverMobile;
    }

    public void setDirverMobile(String dirverMobile) {
        this.dirverMobile = dirverMobile;
    }

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
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