
package com.topjet.manage.domain.bean;

import com.topjet.manage.domain.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Filename      :  MatchCenterOrderBean.java
 * 匹配中心
 * 货源查询
 */
public class MatchCenterOrderBean extends BaseModel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Integer orderId;
	private Integer ownerId;
	private Integer goodsId;
	private String serialNo;
	private Integer orderStatus;
	private String ownerName;
	private String ownerMobile;
	private Integer ownerType;
	private String residentCityId;
	private String residentCity;

	private Integer truckId;
	private String depart1;
	private String depart2;
	private String depart3;
	private String departCityId;
	private String destination1;
	private String destination2;
	private String destination3;
	private String destinationCityId;
	private String destinationDetail;
	private Date loadDate;
	private String senderMobile;
	private String sender;
	private BigDecimal weight;
	private BigDecimal volume;
	private Date createTime;

	private String truckType;
	private String truckTypeName;
	private String truckLength;

	private Date startDate;
	private Integer freightFeeStatus;
	private String isAgencyFee;
	private BigDecimal agencyFee;
	private String loadDateType;
	private Integer distanceTime;
	private Integer successCount;
	private Integer failCount;
	private Integer version;
	private Integer isHidden;
	private Integer quantityType;
	private Integer quantityMax;
	private Integer goodsStatus;
	private Integer quantityMin;
	private String unit;
	private String reditRemark;
	private String typeAndLength;
	private Integer typeId;
	private Integer lengthId;

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getLengthId() {
		return lengthId;
	}

	public void setLengthId(Integer lengthId) {
		this.lengthId = lengthId;
	}

	public String getTypeAndLength() {
		return typeAndLength;
	}

	public void setTypeAndLength(String typeAndLength) {
		this.typeAndLength = typeAndLength;
	}

	public String getReditRemark() {
		return reditRemark;
	}

	public void setReditRemark(String reditRemark) {
		this.reditRemark = reditRemark;
	}

	public Integer getQuantityType() {
		return quantityType;
	}

	public void setQuantityType(Integer quantityType) {
		this.quantityType = quantityType;
	}

	public Integer getQuantityMax() {
		return quantityMax;
	}

	public void setQuantityMax(Integer quantityMax) {
		this.quantityMax = quantityMax;
	}

	public Integer getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(Integer goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public Integer getQuantityMin() {
		return quantityMin;
	}

	public void setQuantityMin(Integer quantityMin) {
		this.quantityMin = quantityMin;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(Integer isHidden) {
		this.isHidden = isHidden;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}

	public Integer getFailCount() {
		return failCount;
	}

	public void setFailCount(Integer failCount) {
		this.failCount = failCount;
	}

	public Integer getDistanceTime() {
		return distanceTime;
	}

	public void setDistanceTime(Integer distanceTime) {
		this.distanceTime = distanceTime;
	}

	public String getLoadDateType() {
		return loadDateType;
	}

	public void setLoadDateType(String loadDateType) {
		this.loadDateType = loadDateType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Integer getFreightFeeStatus() {
		return freightFeeStatus;
	}

	public void setFreightFeeStatus(Integer freightFeeStatus) {
		this.freightFeeStatus = freightFeeStatus;
	}

	public String getIsAgencyFee() {
		return isAgencyFee;
	}

	public void setIsAgencyFee(String isAgencyFee) {
		this.isAgencyFee = isAgencyFee;
	}

	public BigDecimal getAgencyFee() {
		return agencyFee;
	}

	public void setAgencyFee(BigDecimal agencyFee) {
		this.agencyFee = agencyFee;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getResidentCityId() {
		return residentCityId;
	}

	public void setResidentCityId(String residentCityId) {
		this.residentCityId = residentCityId;
	}

	public Integer getTruckId() {
		return truckId;
	}

	public void setTruckId(Integer truckId) {
		this.truckId = truckId;
	}

	public String getDepart1() {
		return depart1;
	}

	public void setDepart1(String depart1) {
		this.depart1 = depart1;
	}

	public String getDepart2() {
		return depart2;
	}

	public void setDepart2(String depart2) {
		this.depart2 = depart2;
	}

	public String getDepart3() {
		return depart3;
	}

	public void setDepart3(String depart3) {
		this.depart3 = depart3;
	}

	public String getDepartCityId() {
		return departCityId;
	}

	public void setDepartCityId(String departCityId) {
		this.departCityId = departCityId;
	}

	public String getDestination1() {
		return destination1;
	}

	public void setDestination1(String destination1) {
		this.destination1 = destination1;
	}

	public String getDestination2() {
		return destination2;
	}

	public void setDestination2(String destination2) {
		this.destination2 = destination2;
	}

	public String getDestination3() {
		return destination3;
	}

	public void setDestination3(String destination3) {
		this.destination3 = destination3;
	}

	public String getDestinationCityId() {
		return destinationCityId;
	}

	public void setDestinationCityId(String destinationCityId) {
		this.destinationCityId = destinationCityId;
	}

	public String getDestinationDetail() {
		return destinationDetail;
	}

	public void setDestinationDetail(String destinationDetail) {
		this.destinationDetail = destinationDetail;
	}

	public Date getLoadDate() {
		return loadDate;
	}

	public void setLoadDate(Date loadDate) {
		this.loadDate = loadDate;
	}

	public String getSenderMobile() {
		return senderMobile;
	}

	public void setSenderMobile(String senderMobile) {
		this.senderMobile = senderMobile;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTruckType() {
		return truckType;
	}

	public void setTruckType(String truckType) {
		this.truckType = truckType;
	}

	public String getTruckTypeName() {
		return truckTypeName;
	}

	public void setTruckTypeName(String truckTypeName) {
		this.truckTypeName = truckTypeName;
	}

	public String getTruckLength() {
		return truckLength;
	}

	public void setTruckLength(String truckLength) {
		this.truckLength = truckLength;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerMobile() {
		return ownerMobile;
	}

	public void setOwnerMobile(String ownerMobile) {
		this.ownerMobile = ownerMobile;
	}

	public Integer getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(Integer ownerType) {
		this.ownerType = ownerType;
	}

	public String getResidentCity() {
		return residentCity;
	}

	public void setResidentCity(String residentCity) {
		this.residentCity = residentCity;
	}
}

