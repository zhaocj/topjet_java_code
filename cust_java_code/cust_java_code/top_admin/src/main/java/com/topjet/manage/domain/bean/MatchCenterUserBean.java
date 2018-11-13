
package com.topjet.manage.domain.bean;

import com.topjet.manage.domain.model.BaseModel;

/**
 * @Filename      :  MatchCenterUserBean.java
 * 匹配中心
 * 用户查询
 */
public class MatchCenterUserBean extends BaseModel{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
    private   Integer userId;
    private  String userName;
    private  String userMobile;
    private  Integer age;
    private  Integer  userType;
    private  Integer orderCount;//订单总数
	private  Integer pendingOrderCount;//待成交订单数
	private  Integer completedOrderCount;//已成交订单数
	private  Integer acceptedOrderCount;//已承运订单数
	private  Integer carrierOrderCount;//承运中订单数
	private  Integer revokeOrderCount;//撤销订单数
	private  Integer falseOrderCount;//虚假订单数
    private  Integer  days;//天数
    private  Integer amount;//订单数
	private  String reditRemark;

	public String getReditRemark() {
		return reditRemark;
	}

	public void setReditRemark(String reditRemark) {
		this.reditRemark = reditRemark;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public Integer getPendingOrderCount() {
		return pendingOrderCount;
	}

	public void setPendingOrderCount(Integer pendingOrderCount) {
		this.pendingOrderCount = pendingOrderCount;
	}

	public Integer getCompletedOrderCount() {
		return completedOrderCount;
	}

	public void setCompletedOrderCount(Integer completedOrderCount) {
		this.completedOrderCount = completedOrderCount;
	}

	public Integer getAcceptedOrderCount() {
		return acceptedOrderCount;
	}

	public void setAcceptedOrderCount(Integer acceptedOrderCount) {
		this.acceptedOrderCount = acceptedOrderCount;
	}

	public Integer getCarrierOrderCount() {
		return carrierOrderCount;
	}

	public void setCarrierOrderCount(Integer carrierOrderCount) {
		this.carrierOrderCount = carrierOrderCount;
	}

	public Integer getRevokeOrderCount() {
		return revokeOrderCount;
	}

	public void setRevokeOrderCount(Integer revokeOrderCount) {
		this.revokeOrderCount = revokeOrderCount;
	}

	public Integer getFalseOrderCount() {
		return falseOrderCount;
	}

	public void setFalseOrderCount(Integer falseOrderCount) {
		this.falseOrderCount = falseOrderCount;
	}
}

