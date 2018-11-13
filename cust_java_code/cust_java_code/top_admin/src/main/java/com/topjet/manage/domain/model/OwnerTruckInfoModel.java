package com.topjet.manage.domain.model;

import java.util.Date;

public class OwnerTruckInfoModel {
    private Integer id;

    private Integer driverTruckId;

    private Integer driverId;

    private Integer ownerId;

    private Integer truckId;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDriverTruckId() {
        return driverTruckId;
    }

    public void setDriverTruckId(Integer driverTruckId) {
        this.driverTruckId = driverTruckId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
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