package com.topjet.manage.domain.bean;
import com.topjet.manage.domain.model.BaseModel;

import java.util.Date;

/**
 * @Filename      :  MatchCenterDriverBean.java
 * 匹配中心
 * 司机位置查询
 */
public class MatchCenterDriverBean extends BaseModel {

	private static final long serialVersionUID = 1L;

	private Integer gpsid;
	private Integer driverTruckid;
	private Integer createTimeInterval;
	private Integer userId;
	private String address1;
	private String address2;
	private String address3;
	private String detail;
	private String cityId;
	private String userName;
	private String mobile;
	private Integer userType;
	private String resident1;//临时常跑城市
	private String resident2;//临时常跑城市
	private String resident3;//临时常跑城市
	private String businessLineFisrt1;
	private String businessLineFisrt2;
	private String businessLineFisrt3;
	private String businessLineSecond1;
	private String businessLineSecond2;
	private String businessLineSecond3;
	private String businessLineThird1;
	private String businessLineThird2;
	private String businessLineThird3;
	private String businessLineForth1;
	private String businessLineForth2;
	private String businessLineForth3;
	private String businessLineFirstCityId;
	private String businessLineSecondCityId;
	private String businessLineThirdCityId;
	private String businessLineForthCityId;
	private String businessLine1;
	private String businessLine2;
	private String businessLine3;
	private String businessLineCityId;
	private String reditRemark;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReditRemark() {
		return reditRemark;
	}

	public void setReditRemark(String reditRemark) {
		this.reditRemark = reditRemark;
	}

	public String getBusinessLine1() {
		return businessLine1;
	}

	public void setBusinessLine1(String businessLine1) {
		this.businessLine1 = businessLine1;
	}

	public String getBusinessLine2() {
		return businessLine2;
	}

	public void setBusinessLine2(String businessLine2) {
		this.businessLine2 = businessLine2;
	}

	public String getBusinessLine3() {
		return businessLine3;
	}

	public void setBusinessLine3(String businessLine3) {
		this.businessLine3 = businessLine3;
	}

	public String getBusinessLineCityId() {
		return businessLineCityId;
	}

	public void setBusinessLineCityId(String businessLineCityId) {
		this.businessLineCityId = businessLineCityId;
	}

	public String getBusinessLineFirstCityId() {
		return businessLineFirstCityId;
	}

	public void setBusinessLineFirstCityId(String businessLineFirstCityId) {
		this.businessLineFirstCityId = businessLineFirstCityId;
	}

	public String getBusinessLineSecondCityId() {
		return businessLineSecondCityId;
	}

	public void setBusinessLineSecondCityId(String businessLineSecondCityId) {
		this.businessLineSecondCityId = businessLineSecondCityId;
	}

	public String getBusinessLineThirdCityId() {
		return businessLineThirdCityId;
	}

	public void setBusinessLineThirdCityId(String businessLineThirdCityId) {
		this.businessLineThirdCityId = businessLineThirdCityId;
	}

	public String getBusinessLineForthCityId() {
		return businessLineForthCityId;
	}

	public void setBusinessLineForthCityId(String businessLineForthCityId) {
		this.businessLineForthCityId = businessLineForthCityId;
	}

	public String getBusinessLineFisrt1() {
		return businessLineFisrt1;
	}

	public void setBusinessLineFisrt1(String businessLineFisrt1) {
		this.businessLineFisrt1 = businessLineFisrt1;
	}

	public String getBusinessLineFisrt2() {
		return businessLineFisrt2;
	}

	public void setBusinessLineFisrt2(String businessLineFisrt2) {
		this.businessLineFisrt2 = businessLineFisrt2;
	}

	public String getBusinessLineFisrt3() {
		return businessLineFisrt3;
	}

	public void setBusinessLineFisrt3(String businessLineFisrt3) {
		this.businessLineFisrt3 = businessLineFisrt3;
	}

	public String getBusinessLineSecond1() {
		return businessLineSecond1;
	}

	public void setBusinessLineSecond1(String businessLineSecond1) {
		this.businessLineSecond1 = businessLineSecond1;
	}

	public String getBusinessLineSecond2() {
		return businessLineSecond2;
	}

	public void setBusinessLineSecond2(String businessLineSecond2) {
		this.businessLineSecond2 = businessLineSecond2;
	}

	public String getBusinessLineSecond3() {
		return businessLineSecond3;
	}

	public void setBusinessLineSecond3(String businessLineSecond3) {
		this.businessLineSecond3 = businessLineSecond3;
	}

	public String getBusinessLineThird1() {
		return businessLineThird1;
	}

	public void setBusinessLineThird1(String businessLineThird1) {
		this.businessLineThird1 = businessLineThird1;
	}

	public String getBusinessLineThird2() {
		return businessLineThird2;
	}

	public void setBusinessLineThird2(String businessLineThird2) {
		this.businessLineThird2 = businessLineThird2;
	}

	public String getBusinessLineThird3() {
		return businessLineThird3;
	}

	public void setBusinessLineThird3(String businessLineThird3) {
		this.businessLineThird3 = businessLineThird3;
	}

	public String getBusinessLineForth1() {
		return businessLineForth1;
	}

	public void setBusinessLineForth1(String businessLineForth1) {
		this.businessLineForth1 = businessLineForth1;
	}

	public String getBusinessLineForth2() {
		return businessLineForth2;
	}

	public void setBusinessLineForth2(String businessLineForth2) {
		this.businessLineForth2 = businessLineForth2;
	}

	public String getBusinessLineForth3() {
		return businessLineForth3;
	}

	public void setBusinessLineForth3(String businessLineForth3) {
		this.businessLineForth3 = businessLineForth3;
	}

	private String plateNo1;
	private String plateNo2;
	private String plateNo3;

	private String truckName;
	private String truckType;
	private String truckTypeName;
	private String truckLength;
	private String truckStatus;
	private Date createTime;
	private Date updateTime;

	public Integer getGpsid() {
		return gpsid;
	}

	public void setGpsid(Integer gpsid) {
		this.gpsid = gpsid;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getResident1() {
		return resident1;
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

	public String getResident3() {
		return resident3;
	}

	public void setResident3(String resident3) {
		this.resident3 = resident3;
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

	public String getTruckName() {
		return truckName;
	}

	public void setTruckName(String truckName) {
		this.truckName = truckName;
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

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getTruckStatus() {
		return truckStatus;
	}

	public void setTruckStatus(String truckStatus) {
		this.truckStatus = truckStatus;
	}

	public Integer getCreateTimeInterval() {
		return createTimeInterval;
	}

	public void setCreateTimeInterval(Integer createTimeInterval) {
		this.createTimeInterval = createTimeInterval;
	}

	public Integer getDriverTruckid() {
		return driverTruckid;
	}

	public void setDriverTruckid(Integer driverTruckid) {
		this.driverTruckid = driverTruckid;
	}

	@Override
	public Date getUpdateTime() {
		return updateTime;
	}

	@Override
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}

