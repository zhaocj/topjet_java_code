package com.topjet.manage.domain.vo;

import java.math.BigDecimal;

/**
 * Created by zhaocj on 2017/10/16.
 */
public class OrderBonusListVO {

    private Integer id;
    private Integer version;
    private Integer ownerId;
    private Integer driverId;
    private Integer orderId;
    private String createDate;
    private String serialNo;
    private BigDecimal freightFee;
    private BigDecimal agencyFee;
    private String senderName;
    private String senderMobile;
    private BigDecimal ownerAmount;
    private String receiverName;
    private String receiverMobile;
    private BigDecimal driverAmount;
    private String fisrtAuditName;
    private String firstAuditName;
    private String firstAuditStatus;
    private String secondAuditName;
    private String secondAuditStatus;
    private String thirdAuditName;
    private String thirdAuditStatus;
    private String fisrtAuditTime;
    private String firstAuditRemark;
    private String secondAuditTime;
    private String secondAuditRemark;
    private String thirdAuditTime;
    private String destination1;
    private String destination2;
    private String destination3;
    private String depart1;
    private String depart2;
    private String depart3;
    private String auditStatus;
    private String thirdAuditRemark;
    private String reditRemark;

    public String getReditRemark() {
        return reditRemark;
    }

    public void setReditRemark(String reditRemark) {
        this.reditRemark = reditRemark;
    }


    public String getFisrtAuditTime() {
        return fisrtAuditTime;
    }

    public void setFisrtAuditTime(String fisrtAuditTime) {
        this.fisrtAuditTime = fisrtAuditTime;
    }

    public String getFirstAuditRemark() {
        return firstAuditRemark;
    }

    public void setFirstAuditRemark(String firstAuditRemark) {
        this.firstAuditRemark = firstAuditRemark;
    }

    public String getSecondAuditTime() {
        return secondAuditTime;
    }

    public void setSecondAuditTime(String secondAuditTime) {
        this.secondAuditTime = secondAuditTime;
    }

    public String getSecondAuditRemark() {
        return secondAuditRemark;
    }

    public void setSecondAuditRemark(String secondAuditRemark) {
        this.secondAuditRemark = secondAuditRemark;
    }

    public String getThirdAuditTime() {
        return thirdAuditTime;
    }

    public void setThirdAuditTime(String thirdAuditTime) {
        this.thirdAuditTime = thirdAuditTime;
    }

    public String getThirdAuditRemark() {
        return thirdAuditRemark;
    }

    public void setThirdAuditRemark(String thirdAuditRemark) {
        this.thirdAuditRemark = thirdAuditRemark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }



    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderMobile() {
        return senderMobile;
    }

    public void setSenderMobile(String senderMobile) {
        this.senderMobile = senderMobile;
    }


    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getFisrtAuditName() {
        return fisrtAuditName;
    }

    public void setFisrtAuditName(String fisrtAuditName) {
        this.fisrtAuditName = fisrtAuditName;
    }

    public String getFirstAuditStatus() {
        return firstAuditStatus;
    }

    public void setFirstAuditStatus(String firstAuditStatus) {
        this.firstAuditStatus = firstAuditStatus;
    }

    public String getSecondAuditName() {
        return secondAuditName;
    }

    public void setSecondAuditName(String secondAuditName) {
        this.secondAuditName = secondAuditName;
    }

    public String getSecondAuditStatus() {
        return secondAuditStatus;
    }

    public void setSecondAuditStatus(String secondAuditStatus) {
        this.secondAuditStatus = secondAuditStatus;
    }

    public String getThirdAuditName() {
        return thirdAuditName;
    }

    public void setThirdAuditName(String thirdAuditName) {
        this.thirdAuditName = thirdAuditName;
    }

    public String getThirdAuditStatus() {
        return thirdAuditStatus;
    }

    public void setThirdAuditStatus(String thirdAuditStatus) {
        this.thirdAuditStatus = thirdAuditStatus;
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

    public BigDecimal getAgencyFee() {
        return agencyFee;
    }

    public void setAgencyFee(BigDecimal agencyFee) {
        this.agencyFee = agencyFee;
    }

    public BigDecimal getOwnerAmount() {
        return ownerAmount;
    }

    public void setOwnerAmount(BigDecimal ownerAmount) {
        this.ownerAmount = ownerAmount;
    }

    public BigDecimal getDriverAmount() {
        return driverAmount;
    }

    public void setDriverAmount(BigDecimal driverAmount) {
        this.driverAmount = driverAmount;
    }

    public BigDecimal getFreightFee() {
        return freightFee;
    }

    public void setFreightFee(BigDecimal freightFee) {
        this.freightFee = freightFee;
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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }
    public String getFirstAuditName() {
        return firstAuditName;
    }

    public void setFirstAuditName(String firstAuditName) {
        this.firstAuditName = firstAuditName;
    }

}
