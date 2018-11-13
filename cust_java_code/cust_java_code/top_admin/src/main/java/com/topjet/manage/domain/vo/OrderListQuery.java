package com.topjet.manage.domain.vo;


import com.topjet.manage.domain.model.BaseModel;

import java.util.Date;

/**
 * Created by tsj006 on 2016/9/1.
 */
public class OrderListQuery extends BaseModel {
    private String mobile;//手机号
    private String orderNo;//订单编号
    private Date startDate;//开始时间
    private Date endDate;//结束时间
    private String departCityId;//
    private String endDepartCityId;//
    private String destinationCityId;//
    private String endDestinationCityId;//
    private String orderStatus;//订单状态
    private String isFreightFeeManaged;//是否托管
    private String freightFeeStatus;//运费状态
    private String sysName;//操作人
    private Integer version;//版本
    private String createDate;//创建时间
    private String name;//创建人
    private String departCity;//提货地址
    private String destinationCity;//收货地址
    private int orderTable; // 订单表历或史订单表
    private String receiverMobile;//接单人手机号
    private String reditRemark;

    public String getReditRemark() {
        return reditRemark;
    }

    public void setReditRemark(String reditRemark) {
        this.reditRemark = reditRemark;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getIsFreightFeeManaged() {
        return isFreightFeeManaged;
    }
    public void setIsFreightFeeManaged(String isFreightFeeManaged) {
        this.isFreightFeeManaged = isFreightFeeManaged;
    }
    public String getFreightFeeStatus() {
        return freightFeeStatus;
    }
    public void setFreightFeeStatus(String freightFeeStatus) {
        this.freightFeeStatus = freightFeeStatus;
    }

    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartCity() {
        return departCity;
    }

    public void setDepartCity(String departCity) {
        this.departCity = departCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getEndDepartCityId() {
        return endDepartCityId;
    }

    public void setEndDepartCityId(String endDepartCityId) {
        this.endDepartCityId = endDepartCityId;
    }

    public String getEndDestinationCityId() {
        return endDestinationCityId;
    }

    public void setEndDestinationCityId(String endDestinationCityId) {
        this.endDestinationCityId = endDestinationCityId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDepartCityId() {
        return departCityId;
    }

    public void setDepartCityId(String departCityId) {
        this.departCityId = departCityId;
    }

    public String getDestinationCityId() {
        return destinationCityId;
    }

    public void setDestinationCityId(String destinationCityId) {
        this.destinationCityId = destinationCityId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }


    public int getOrderTable() {
        return orderTable;
    }

    public void setOrderTable(int orderTable) {
        this.orderTable = orderTable;
    }
}
