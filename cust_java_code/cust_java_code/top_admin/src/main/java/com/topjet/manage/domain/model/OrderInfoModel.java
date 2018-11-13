package com.topjet.manage.domain.model;

import org.omg.PortableInterceptor.INACTIVE;

import java.math.BigDecimal;
import java.util.Date;

public class OrderInfoModel {
    private Integer id;

    private Integer ownerId;

    private Integer driverId;

    private Integer goodsId;

    private String orderNo;

    private Byte orderStatus;

    private Integer driverTruckId;

    private String isAssigned;

    private Integer bonusValid;

    private String isDriverAgreementAccept;

    private String isOwnerAgreementAccept;

    private String isGuaranteed;

    private String isHidden;

    private String isInner;

    private String sourceType;

    private Date createTime;

    private Date updateTime;

    private String deleted;

    private Integer freightFee;

    public Integer getFreightFee() {
        return freightFee;
    }

    public void setFreightFee(Integer freightFee) {
        this.freightFee = freightFee;
    }

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

    public Byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getDriverTruckId() {
        return driverTruckId;
    }

    public void setDriverTruckId(Integer driverTruckId) {
        this.driverTruckId = driverTruckId;
    }

    public String getIsAssigned() {
        return isAssigned;
    }

    public void setIsAssigned(String isAssigned) {
        this.isAssigned = isAssigned;
    }

    public Integer getBonusValid() {
        return bonusValid;
    }

    public void setBonusValid(Integer bonusValid) {
        this.bonusValid = bonusValid;
    }

    public String getIsDriverAgreementAccept() {
        return isDriverAgreementAccept;
    }

    public void setIsDriverAgreementAccept(String isDriverAgreementAccept) {
        this.isDriverAgreementAccept = isDriverAgreementAccept;
    }

    public String getIsOwnerAgreementAccept() {
        return isOwnerAgreementAccept;
    }

    public void setIsOwnerAgreementAccept(String isOwnerAgreementAccept) {
        this.isOwnerAgreementAccept = isOwnerAgreementAccept;
    }

    public String getIsGuaranteed() {
        return isGuaranteed;
    }

    public void setIsGuaranteed(String isGuaranteed) {
        this.isGuaranteed = isGuaranteed;
    }

    public String getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(String isHidden) {
        this.isHidden = isHidden;
    }

    public String getIsInner() {
        return isInner;
    }

    public void setIsInner(String isInner) {
        this.isInner = isInner;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
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

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }
}