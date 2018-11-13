package com.topjet.manage.domain.bean;


import com.topjet.manage.domain.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

public class RefundDetail2Bean extends BaseModel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3288893204561107652L;

	private Integer id;

	private Integer billId;
	private BigDecimal refundMoney;

	private Integer goodsId;
	private Integer orderId;
	private Integer driverId;
	private Integer ownerId;
	private Integer status;
	private Integer csStatus;
	private Integer csStatus1;
	private Integer triggerId;
	private String reason;
	private Integer rfOrderStatus;
	private String remark;
	private String userRemark;
	private Date createTime;
	private Integer createBy;
	private Date updateTime;
	private Integer updateBy;
	private Integer version;
	private Integer deleted;
	private String provePhoto1;
	private String provePhoto2;
	private String provePhoto3;
	private String provePhoto4;
	private String repulseReason;//反驳原因

	private String repulseRemark;//反驳备注
	private Integer oiOrderStatus;
	
	private String triggerName;
	private String mobile;
	private String nickName;//审核人
	private String reditRemark;

	private String oppositeName;
	private Integer flag;//标记是否是从提醒进入的

	private Integer orderFrozenState;//订单冻结状态
	private Integer orderAffiliateId;//订单附属表orderAffiliate 主键
	private Integer orderAffiliateVersion;//订单附属表orderAffiliate 版本号

	//申请时间  时间段
	private Date beginDate;
	private Date endDate;

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getReditRemark() {
		return reditRemark;
	}

	public void setReditRemark(String reditRemark) {
		this.reditRemark = reditRemark;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public BigDecimal getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(BigDecimal refundMoney) {
		this.refundMoney = refundMoney;
	}

	public String getRepulseRemark() {
		return repulseRemark;
	}

	public void setRepulseRemark(String repulseRemark) {
		this.repulseRemark = repulseRemark;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getOrderFrozenState() {
		return orderFrozenState;
	}

	public void setOrderFrozenState(Integer orderFrozenState) {
		this.orderFrozenState = orderFrozenState;
	}

	public Integer getOrderAffiliateId() {
		return orderAffiliateId;
	}

	public void setOrderAffiliateId(Integer orderAffiliateId) {
		this.orderAffiliateId = orderAffiliateId;
	}

	public Integer getOrderAffiliateVersion() {
		return orderAffiliateVersion;
	}

	public void setOrderAffiliateVersion(Integer orderAffiliateVersion) {
		this.orderAffiliateVersion = orderAffiliateVersion;
	}

	public String getProvePhoto2() {
		return provePhoto2;
	}

	public void setProvePhoto2(String provePhoto2) {
		this.provePhoto2 = provePhoto2;
	}

	public String getProvePhoto3() {
		return provePhoto3;
	}

	public void setProvePhoto3(String provePhoto3) {
		this.provePhoto3 = provePhoto3;
	}

	public String getProvePhoto4() {
		return provePhoto4;
	}

	public void setProvePhoto4(String provePhoto4) {
		this.provePhoto4 = provePhoto4;
	}

	private String triggerId2;
	private String oppositId;
	private String triggerMobile;
	private String oppositMobile;
	private String billNo;
	private String orderNo;
	
	private BigDecimal freightFee;
	private BigDecimal agencyFee;
	private Integer triggerType;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRepulseReason() {
		return repulseReason;
	}

	public void setRepulseReason(String repulseReason) {
		this.repulseReason = repulseReason;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public BigDecimal getFreightFee() {
		return freightFee;
	}

	public void setFreightFee(BigDecimal freightFee) {
		this.freightFee = freightFee;
	}

	public BigDecimal getAgencyFee() {
		return agencyFee;
	}

	public void setAgencyFee(BigDecimal agencyFee) {
		this.agencyFee = agencyFee;
	}

	public Integer getTriggerType() {
		return triggerType;
	}

	public void setTriggerType(Integer triggerType) {
		this.triggerType = triggerType;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getTriggerMobile() {
		return triggerMobile;
	}

	public void setTriggerMobile(String triggerMobile) {
		this.triggerMobile = triggerMobile;
	}

	public String getOppositMobile() {
		return oppositMobile;
	}

	public void setOppositMobile(String oppositMobile) {
		this.oppositMobile = oppositMobile;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public Integer getCsStatus1() {
		return csStatus1;
	}

	public void setCsStatus1(Integer csStatus1) {
		this.csStatus1 = csStatus1;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getOppositeName() {
		return oppositeName;
	}

	public void setOppositeName(String oppositeName) {
		this.oppositeName = oppositeName;
	}

	public String getTriggerId2() {
		return triggerId2;
	}

	public void setTriggerId2(String triggerId2) {
		this.triggerId2 = triggerId2;
	}

	public String getOppositId() {
		return oppositId;
	}

	public void setOppositId(String oppositId) {
		this.oppositId = oppositId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTriggerId() {
		return triggerId;
	}

	public void setTriggerId(Integer triggerId) {
		this.triggerId = triggerId;
	}

	public Integer getRfOrderStatus() {
		return rfOrderStatus;
	}

	public void setRfOrderStatus(Integer rfOrderStatus) {
		this.rfOrderStatus = rfOrderStatus;
	}

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCsStatus() {
		return csStatus;
	}

	public void setCsStatus(Integer csStatus) {
		this.csStatus = csStatus;
	}


	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserRemark() {
		return userRemark;
	}

	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
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

	

	public Integer getOiOrderStatus() {
		return oiOrderStatus;
	}

	public void setOiOrderStatus(Integer oiOrderStatus) {
		this.oiOrderStatus = oiOrderStatus;
	}


	public String getProvePhoto1() {
		return provePhoto1;
	}

	public void setProvePhoto1(String provePhoto1) {
		this.provePhoto1 = provePhoto1;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
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
}
