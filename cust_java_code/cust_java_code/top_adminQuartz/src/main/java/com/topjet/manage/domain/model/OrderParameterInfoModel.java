package com.topjet.manage.domain.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by tsj010 on 2017/12/20.
 */
public class OrderParameterInfoModel {

    private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer orderId;

    private Integer userId;

    private Integer parameterType;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String detail;

    private Integer cityId;

    private String IEMI;

    private Date createTime;

    private Date updateTime;

    private Integer version;

    private Integer deleted;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getParameterType() {
        return parameterType;
    }

    public void setParameterType(Integer parameterType) {
        this.parameterType = parameterType;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getIEMI() {
        return IEMI;
    }

    public void setIEMI(String IEMI) {
        this.IEMI = IEMI;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}


