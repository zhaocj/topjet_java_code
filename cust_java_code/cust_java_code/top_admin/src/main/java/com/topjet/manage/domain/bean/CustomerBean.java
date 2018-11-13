package com.topjet.manage.domain.bean;

import com.topjet.manage.domain.model.BaseModel;

import java.util.Date;

/**
 * Created by tsj010 on 2017/11/13.
 */
public class CustomerBean extends BaseModel {
    //时间段
    private Date beginDate;
    private Date endDate;
    private  Integer   customerId;//客服id
    private  String   customerName;//客服name

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    private  Integer  memberAuditCount;//实名认证数量
    private  Integer  idAuditCount;//身份认证数量
    private  Integer  truckAuditCount;//车辆认真数量
    private  Integer  headAuditCount;//头像认证数量
    private  Integer  memberSuccessCount;//实名认证成功数量
    private  Integer  idSuccessCount;//身份认证成功数量
    private  Integer  truckSuccessCount;//车辆认证成功数量
    private  Integer  headSuccessCount;//头像认证成功数量


    public Integer getMemberSuccessCount() {
        return memberSuccessCount;
    }

    public void setMemberSuccessCount(Integer memberSuccessCount) {
        this.memberSuccessCount = memberSuccessCount;
    }

    public Integer getIdSuccessCount() {
        return idSuccessCount;
    }

    public void setIdSuccessCount(Integer idSuccessCount) {
        this.idSuccessCount = idSuccessCount;
    }

    public Integer getTruckSuccessCount() {
        return truckSuccessCount;
    }

    public void setTruckSuccessCount(Integer truckSuccessCount) {
        this.truckSuccessCount = truckSuccessCount;
    }

    public Integer getHeadSuccessCount() {
        return headSuccessCount;
    }

    public void setHeadSuccessCount(Integer headSuccessCount) {
        this.headSuccessCount = headSuccessCount;
    }

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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getMemberAuditCount() {
        return memberAuditCount;
    }

    public void setMemberAuditCount(Integer memberAuditCount) {
        this.memberAuditCount = memberAuditCount;
    }

    public Integer getIdAuditCount() {
        return idAuditCount;
    }

    public void setIdAuditCount(Integer idAuditCount) {
        this.idAuditCount = idAuditCount;
    }

    public Integer getTruckAuditCount() {
        return truckAuditCount;
    }

    public void setTruckAuditCount(Integer truckAuditCount) {
        this.truckAuditCount = truckAuditCount;
    }

    public Integer getHeadAuditCount() {
        return headAuditCount;
    }

    public void setHeadAuditCount(Integer headAuditCount) {
        this.headAuditCount = headAuditCount;
    }
}
