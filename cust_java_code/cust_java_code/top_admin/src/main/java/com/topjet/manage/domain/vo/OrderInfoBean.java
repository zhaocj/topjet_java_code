package com.topjet.manage.domain.vo;

import com.topjet.manage.domain.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

public class OrderInfoBean extends BaseModel {
    private Integer id;

    private Integer driverId;

    private Integer ownerId;

    private Integer goodsId;

    private String serialNo;

    private Integer status;

    private Integer driverTruckId;

    private Integer isAssigned;

    private BigDecimal freightFee;

    private Integer isFreightFeeManaged;

    private Integer freightFeeStatus;

    private BigDecimal aheadFee;

    private Integer isAheadFeeManaged;

    private Integer aheadFeeStatus;

    private BigDecimal deliveryFee;

    private Integer isDeliveryFeeManaged;

    private Integer deliveryFeeStatus;

    private BigDecimal backFee;

    private Integer backFeeStatus;

    private BigDecimal agencyFee;

    private Integer agencyFeeStatus;

    private String pickupCode;

    private Date pickupStartTime;

    private String unloadCode;

    private Short transportingDays;

    private Integer bonusValid;

    private Integer isDriverAgreementAccept;

    private Integer isOwnerAgreementAccept;

    private Integer isGuaranteed;

    private Integer isInner;

    private Integer cancelReason;

    private String cancelRemark;

    private Integer createBy;

    private Date updateTime;

    private Date createTime;

    private Integer updateBy;

    private Integer version;

    private Integer deleted;
    
    private String ownerName;
    
    private String driverName;
    private  String  frozenRemark;
    private Integer orderAffiliateVersion;//订单附属表orderAffiliate 版本号
    private Integer flag;//标记是冻结还是解冻  0 冻结 1 解冻

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getOrderAffiliateVersion() {
        return orderAffiliateVersion;
    }

    public void setOrderAffiliateVersion(Integer orderAffiliateVersion) {
        this.orderAffiliateVersion = orderAffiliateVersion;
    }
    public String getFrozenRemark() {
        return frozenRemark;
    }

    public void setFrozenRemark(String frozenRemark) {
        this.frozenRemark = frozenRemark;
    }

    private static final long serialVersionUID = -6388893204561107652L;

    public String getOwnerName() {
		return ownerName;
	}


	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}


	public String getDriverName() {
		return driverName;
	}


	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo == null ? null : serialNo.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public BigDecimal getFreightFee() {
        return freightFee;
    }

    public void setFreightFee(BigDecimal freightFee) {
        this.freightFee = freightFee;
    }

    public Integer getIsFreightFeeManaged() {
        return isFreightFeeManaged;
    }

    public void setIsFreightFeeManaged(Integer isFreightFeeManaged) {
        this.isFreightFeeManaged = isFreightFeeManaged;
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

    public Integer getIsAheadFeeManaged() {
        return isAheadFeeManaged;
    }

    public void setIsAheadFeeManaged(Integer isAheadFeeManaged) {
        this.isAheadFeeManaged = isAheadFeeManaged;
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

    public Integer getIsDeliveryFeeManaged() {
        return isDeliveryFeeManaged;
    }

    public void setIsDeliveryFeeManaged(Integer isDeliveryFeeManaged) {
        this.isDeliveryFeeManaged = isDeliveryFeeManaged;
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

    public BigDecimal getAgencyFee() {
        return agencyFee;
    }

    public void setAgencyFee(BigDecimal agencyFee) {
        this.agencyFee = agencyFee;
    }

    public Integer getAgencyFeeStatus() {
        return agencyFeeStatus;
    }

    public void setAgencyFeeStatus(Integer agencyFeeStatus) {
        this.agencyFeeStatus = agencyFeeStatus;
    }

    public String getPickupCode() {
        return pickupCode;
    }

    public void setPickupCode(String pickupCode) {
        this.pickupCode = pickupCode == null ? null : pickupCode.trim();
    }

    public Date getPickupStartTime() {
        return pickupStartTime;
    }

    public void setPickupStartTime(Date pickupStartTime) {
        this.pickupStartTime = pickupStartTime;
    }

    public String getUnloadCode() {
        return unloadCode;
    }

    public void setUnloadCode(String unloadCode) {
        this.unloadCode = unloadCode == null ? null : unloadCode.trim();
    }

    public Short getTransportingDays() {
        return transportingDays;
    }

    public void setTransportingDays(Short transportingDays) {
        this.transportingDays = transportingDays;
    }

    public Integer getBonusValid() {
        return bonusValid;
    }

    public void setBonusValid(Integer bonusValid) {
        this.bonusValid = bonusValid;
    }

    public Integer getIsDriverAgreementAccept() {
        return isDriverAgreementAccept;
    }

    public void setIsDriverAgreementAccept(Integer isDriverAgreementAccept) {
        this.isDriverAgreementAccept = isDriverAgreementAccept;
    }

    public Integer getIsOwnerAgreementAccept() {
        return isOwnerAgreementAccept;
    }

    public void setIsOwnerAgreementAccept(Integer isOwnerAgreementAccept) {
        this.isOwnerAgreementAccept = isOwnerAgreementAccept;
    }

    public Integer getIsGuaranteed() {
        return isGuaranteed;
    }

    public void setIsGuaranteed(Integer isGuaranteed) {
        this.isGuaranteed = isGuaranteed;
    }

    public Integer getIsInner() {
        return isInner;
    }

    public void setIsInner(Integer isInner) {
        this.isInner = isInner;
    }

    public Integer getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(Integer cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getCancelRemark() {
        return cancelRemark;
    }

    public void setCancelRemark(String cancelRemark) {
        this.cancelRemark = cancelRemark == null ? null : cancelRemark.trim();
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
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