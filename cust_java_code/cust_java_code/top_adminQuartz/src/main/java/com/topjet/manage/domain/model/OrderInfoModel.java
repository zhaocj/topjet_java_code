package com.topjet.manage.domain.model;

import java.math.BigDecimal;
import java.util.Date;

public class OrderInfoModel {
    private Integer id;

    private Integer ownerId;

    private Integer driverId;

    private Integer goodsId;

    private String orderNo;

    private Integer orderStatus;

    private Integer driverTruckId;

    private Integer isAssigned;

    private Integer payStyle;

    private BigDecimal agencyFee;

    private Integer agencyStatus;

    private BigDecimal freightFee;

    private Integer freightFeeStatus;

    private BigDecimal aheadFee;

    private Integer aheadFeeStatus;

    private BigDecimal deliveryFee;

    private Integer deliveryFeeStatus;

    private BigDecimal backFee;

    private Integer backFeeStatus;

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

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getDriverTruckId() {
        return driverTruckId;
    }

    public void setDriverTruckId(Integer driverTruckId) {
        this.driverTruckId = driverTruckId;
    }

    public Integer getIsAssigned() {
        return isAssigned;
    }

    public void setIsAssigned(Integer isAssigned) {
        this.isAssigned = isAssigned;
    }

    public Integer getPayStyle() {
        return payStyle;
    }

    public void setPayStyle(Integer payStyle) {
        this.payStyle = payStyle;
    }

    public BigDecimal getAgencyFee() {
        return agencyFee;
    }

    public void setAgencyFee(BigDecimal agencyFee) {
        this.agencyFee = agencyFee;
    }

    public Integer getAgencyStatus() {
        return agencyStatus;
    }

    public void setAgencyStatus(Integer agencyStatus) {
        this.agencyStatus = agencyStatus;
    }

    public BigDecimal getFreightFee() {
        return freightFee;
    }

    public void setFreightFee(BigDecimal freightFee) {
        this.freightFee = freightFee;
    }

    public Integer getFreightFeeStatus() {
        return freightFeeStatus;
    }

    public void setFreightFeeStatus(Integer freightFeeStatus) {
        this.freightFeeStatus = freightFeeStatus;
    }

    public BigDecimal getAheadFee() {
        return aheadFee;
    }

    public void setAheadFee(BigDecimal aheadFee) {
        this.aheadFee = aheadFee;
    }

    public Integer getAheadFeeStatus() {
        return aheadFeeStatus;
    }

    public void setAheadFeeStatus(Integer aheadFeeStatus) {
        this.aheadFeeStatus = aheadFeeStatus;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Integer getDeliveryFeeStatus() {
        return deliveryFeeStatus;
    }

    public void setDeliveryFeeStatus(Integer deliveryFeeStatus) {
        this.deliveryFeeStatus = deliveryFeeStatus;
    }

    public BigDecimal getBackFee() {
        return backFee;
    }

    public void setBackFee(BigDecimal backFee) {
        this.backFee = backFee;
    }

    public Integer getBackFeeStatus() {
        return backFeeStatus;
    }

    public void setBackFeeStatus(Integer backFeeStatus) {
        this.backFeeStatus = backFeeStatus;
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