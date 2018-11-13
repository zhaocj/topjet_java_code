package com.topjet.manage.domain.model;

import com.topjet.manage.domain.vo.TruckListQuery;
import com.topjet.manage.domain.vo.TruckListVerifyVO;

import java.util.Date;

public class TruckInfoModel extends TruckListQuery {
    private Integer id;

    private String plateNo1;

    private String plateNo2;

    private String plateNo3;

    private Integer truckLength;

    private Integer truckType;

    private Integer plateColor;

    private Integer ownerId;

    private String truckHeadImg;

    private String driverLicenseImg;

    private Date createTime;

    private Integer deleted;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlateNo1() {
        return plateNo1;
    }

    public void setPlateNo1(String plateNo1) {
        this.plateNo1 = plateNo1;
    }

    public String getPlateNo2() {
        return plateNo2;
    }

    public void setPlateNo2(String plateNo2) {
        this.plateNo2 = plateNo2;
    }

    public String getPlateNo3() {
        return plateNo3;
    }

    public void setPlateNo3(String plateNo3) {
        this.plateNo3 = plateNo3;
    }

    public Integer getTruckLength() {
        return truckLength;
    }

    public void setTruckLength(Integer truckLength) {
        this.truckLength = truckLength;
    }

    public Integer getTruckType() {
        return truckType;
    }

    public void setTruckType(Integer truckType) {
        this.truckType = truckType;
    }

    public Integer getPlateColor() {
        return plateColor;
    }

    public void setPlateColor(Integer plateColor) {
        this.plateColor = plateColor;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getTruckHeadImg() {
        return truckHeadImg;
    }

    public void setTruckHeadImg(String truckHeadImg) {
        this.truckHeadImg = truckHeadImg;
    }

    public String getDriverLicenseImg() {
        return driverLicenseImg;
    }

    public void setDriverLicenseImg(String driverLicenseImg) {
        this.driverLicenseImg = driverLicenseImg;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}