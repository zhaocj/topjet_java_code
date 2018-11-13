package com.topjet.manage.domain.bean;

import com.topjet.manage.domain.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderBonusBean extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3388893204561107652L;

	
	private String cityCode;
	
	private String cityMinCode;
	
	private  String  cityMaxCode ;

	private Date startTime;
	private Date endTime;
	private Date haulageTime;//签收时间

	private Integer auditStatus;

	public Date getHaulageTime() {
		return haulageTime;
	}

	public void setHaulageTime(Date haulageTime) {
		this.haulageTime = haulageTime;
	}
	/*
	 * 订单补贴编号
	 */

	private Integer bonusOrderId;

	private String goodsOwnerName;

	private String goodsOwnerMobile;

	private String driverName;

	private String driverMobile;

	private String resident1;
	
	private Integer Id;

	private String resident2;

	private BigDecimal distance;

	private BigDecimal volume;

	private BigDecimal weight;

	private String volumeAndWeight;

	private Date transportTimes;

	private Integer transportTime;//天

	private Integer hour;//小时

	private Integer second;//分钟

	private Date loadDate;//提货时间

	private Date unloadCreateTime;

	private String auditName;//审核人

	private Integer quantityType;

	private BigDecimal quantityMax;

	private BigDecimal quantityMin;

	private String unit;

	public List<Integer> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<Integer> statusList) {
		this.statusList = statusList;
	}

	private List<Integer> statusList;

	public Integer getSecond() {
		return second;
	}

	public void setSecond(Integer second) {
		this.second = second;
	}

	public String getAuditName() {
		return auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public Date getUnloadCreateTime() {
		return unloadCreateTime;
	}

	public void setUnloadCreateTime(Date unloadCreateTime) {
		this.unloadCreateTime = unloadCreateTime;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Date getTransportTimes() {
		return transportTimes;
	}

	public void setTransportTimes(Date transportTimes) {
		this.transportTimes = transportTimes;
	}

	public Date getLoadDate() {
		return loadDate;
	}

	public void setLoadDate(Date loadDate) {
		this.loadDate = loadDate;
	}

	public Integer getTransportTime() {
		return transportTime;
	}

	public void setTransportTime(Integer transportTime) {
		this.transportTime = transportTime;
	}

	public String getVolumeAndWeight() {
		return volumeAndWeight;
	}

	public void setVolumeAndWeight(String volumeAndWeight) {
		this.volumeAndWeight = volumeAndWeight;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	private String fisrtAuditUser;//一级审核人
	private String firstAuditTime;//一级审核时间
	private String secondAuditTime;//二审时间
	private String thirdAuditTime;//三审时间

	public String getSecondAuditTime() {
		return secondAuditTime;
	}

	public void setSecondAuditTime(String secondAuditTime) {
		this.secondAuditTime = secondAuditTime;
	}

	public String getThirdAuditTime() {
		return thirdAuditTime;
	}

	public void setThirdAuditTime(String thirdAuditTime) {
		this.thirdAuditTime = thirdAuditTime;
	}

	public String getFisrtAuditUser() {
		return fisrtAuditUser;
	}

	public void setFisrtAuditUser(String fisrtAuditUser) {
		this.fisrtAuditUser = fisrtAuditUser;
	}

	public String getFirstAuditTime() {
		return firstAuditTime;
	}

	public void setFirstAuditTime(String firstAuditTime) {
		this.firstAuditTime = firstAuditTime;
	}

	public BigDecimal getDistance() {
		return distance;
	}

	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	private String assignName;
	public String getAssignName() {
		return assignName;
	}

	public void setAssignName(String assignName) {
		this.assignName = assignName;
	}
	/*
	 * 出发地
	 */
	private String depart1;

	private String depart2;
	private String depart3;


	private String destination1;

	private String destination2;
	private String destination3;

	private BigDecimal transportFee;

	private BigDecimal agentFee;

	private BigDecimal freightFee;

	private BigDecimal driverAmount;

	private String type;

	private BigDecimal ownerAmount;

	private String firstAuditRemark;

	private String secondAuditRemark;

	private String thirdAuditRemark;

	private String firstAuditStatus;

	private String secondAuditStatus;

	private String thirdAuditStatus;

	private Integer settingId;

	private Integer orderBonusVersion;

	private String firstAuditUser;

	private String secondAuditUser;

	private String thirdAuditUser;

	private Integer driverId;

	private Integer ownerId;
	
	private String returnMessage;
	
	private Integer successCount ;
	
	private Integer  failCount ;
	
	private Integer auditCycle;

	private Integer version;

	private  String serialNo;

	private String flag;//标记是否为分配任务客服

	private String sysUserId;//任务分配者ID

	private String firstAuditName;

	private String secondAuditName;

	public String getFirstAuditName() {
		return firstAuditName;
	}

	public void setFirstAuditName(String firstAuditName) {
		this.firstAuditName = firstAuditName;
	}

	public String getSecondAuditName() {
		return secondAuditName;
	}

	public void setSecondAuditName(String secondAuditName) {
		this.secondAuditName = secondAuditName;
	}

	public String getThirdAuditName() {
		return thirdAuditName;
	}

	public void setThirdAuditName(String thirdAuditName) {
		this.thirdAuditName = thirdAuditName;
	}

	private String thirdAuditName;

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public Integer getAuditCycle() {
		return auditCycle;
	}

	public void setAuditCycle(Integer auditCycle) {
		this.auditCycle = auditCycle;
	}

	public BigDecimal getFreightFee() {
		return freightFee;
	}

	public void setFreightFee(BigDecimal freightFee) {
		this.freightFee = freightFee;
	}

	/*
	 * 根据补贴单号和version 查找的返回结果
	 */
	private Integer row;

	public Integer getBonusOrderId() {
		return bonusOrderId;
	}

	public void setBonusOrderId(Integer bonusOrderId) {
		this.bonusOrderId = bonusOrderId;
	}

	public String getGoodsOwnerName() {
		return goodsOwnerName;
	}

	public void setGoodsOwnerName(String goodsOwnerName) {
		this.goodsOwnerName = goodsOwnerName;
	}

	public String getFirstAuditUser() {
		return firstAuditUser;
	}

	public void setFirstAuditUser(String firstAuditUser) {
		this.firstAuditUser = firstAuditUser;
	}

	public String getSecondAuditUser() {
		return secondAuditUser;
	}

	public void setSecondAuditUser(String secondAuditUser) {
		this.secondAuditUser = secondAuditUser;
	}

	public String getThirdAuditUser() {
		return thirdAuditUser;
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

	public void setThirdAuditUser(String thirdAuditUser) {
		this.thirdAuditUser = thirdAuditUser;
	}

	public String getGoodsOwnerMobile() {
		return goodsOwnerMobile;
	}

	public void setGoodsOwnerMobile(String goodsOwnerMobile) {
		this.goodsOwnerMobile = goodsOwnerMobile;
	}

	public String getFirstAuditStatus() {
		return firstAuditStatus;
	}

	public void setFirstAuditStatus(String firstAuditStatus) {
		this.firstAuditStatus = firstAuditStatus;
	}

	public String getSecondAuditStatus() {
		return secondAuditStatus;
	}

	public void setSecondAuditStatus(String secondAuditStatus) {
		this.secondAuditStatus = secondAuditStatus;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getThirdAuditStatus() {
		return thirdAuditStatus;
	}

	public void setThirdAuditStatus(String thirdAuditStatus) {
		this.thirdAuditStatus = thirdAuditStatus;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverMobile() {
		return driverMobile;
	}

	public void setDriverMobile(String driverMobile) {
		this.driverMobile = driverMobile;
	}

	public String getResident1() {
		return resident1;
	}

	public Integer getOrderBonusVersion() {
		return orderBonusVersion;
	}

	public void setOrderBonusVersion(Integer orderBonusVersion) {
		this.orderBonusVersion = orderBonusVersion;
	}

	public void setResident1(String resident1) {
		this.resident1 = resident1;
	}

	public String getResident2() {
		return resident2;
	}

	public void setResident2(String resident2) {
		this.resident2 = resident2;
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

	public BigDecimal getTransportFee() {
		return transportFee;
	}

	public void setTransportFee(BigDecimal transportFee) {
		this.transportFee = transportFee;
	}

	public BigDecimal getAgentFee() {
		return agentFee;
	}

	public void setAgentFee(BigDecimal agentFee) {
		this.agentFee = agentFee;
	}

	public BigDecimal getDriverAmount() {
		return driverAmount;
	}

	public void setDriverAmount(BigDecimal driverAmount) {
		this.driverAmount = driverAmount;
	}

	public BigDecimal getOwnerAmount() {
		return ownerAmount;
	}

	public void setOwnerAmount(BigDecimal ownerAmount) {
		this.ownerAmount = ownerAmount;
	}

	public String getFirstAuditRemark() {
		return firstAuditRemark;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public void setFirstAuditRemark(String firstAuditRemark) {
		this.firstAuditRemark = firstAuditRemark;
	}
   
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityMinCode() {
		return cityMinCode;
	}

	public void setCityMinCode(String cityMinCode) {
		this.cityMinCode = cityMinCode;
	}

	public String getCityMaxCode() {
		return cityMaxCode;
	}

	public void setCityMaxCode(String cityMaxCode) {
		this.cityMaxCode = cityMaxCode;
	}

	public String getSecondAuditRemark() {
		return secondAuditRemark;
	}

	public void setSecondAuditRemark(String secondAuditRemark) {
		this.secondAuditRemark = secondAuditRemark;
	}

	public String getThirdAuditRemark() {
		return thirdAuditRemark;
	}

	public void setThirdAuditRemark(String thirdAuditRemark) {
		this.thirdAuditRemark = thirdAuditRemark;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
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

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}


	public Integer getSettingId() {
		return settingId;
	}

	public void setSettingId(Integer settingId) {
		this.settingId = settingId;
	}

	public String getDepart3() {
		return depart3;
	}

	public void setDepart3(String depart3) {
		this.depart3 = depart3;
	}

	public String getDestination3() {
		return destination3;
	}

	public void setDestination3(String destination3) {
		this.destination3 = destination3;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Integer getQuantityType() {
		return quantityType;
	}

	public void setQuantityType(Integer quantityType) {
		this.quantityType = quantityType;
	}

	public BigDecimal getQuantityMax() {
		return quantityMax;
	}

	public void setQuantityMax(BigDecimal quantityMax) {
		this.quantityMax = quantityMax;
	}

	public BigDecimal getQuantityMin() {
		return quantityMin;
	}

	public void setQuantityMin(BigDecimal quantityMin) {
		this.quantityMin = quantityMin;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}
