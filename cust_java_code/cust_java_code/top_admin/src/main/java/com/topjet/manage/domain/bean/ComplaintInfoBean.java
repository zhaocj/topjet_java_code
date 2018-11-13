package com.topjet.manage.domain.bean;

import com.topjet.manage.domain.model.BaseModel;

import java.util.Date;

public class ComplaintInfoBean extends BaseModel {
    private Integer id;

    private Integer userId;

    private String auditName;

    private Date auditCreateTime;

    private String name;

    private String mobile;

    private String complaintedName;

    private String complaintedMobile;

    private Integer complaintedUserId;

    private Integer orderId;

    private String orderSerialNo;

    private Integer status;

    private Integer source;

    private String content;

    private String remark;

    private String auditRemark;

    private String complaintCardID;

    private String complaintedCardID;

    private Integer complaintType;

    private Integer complaintedType;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private Integer version;

    private Integer deleted;

    private static final long serialVersionUID = 1L;

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    public Date getAuditCreateTime() {
        return auditCreateTime;
    }

    public void setAuditCreateTime(Date auditCreateTime) {
        this.auditCreateTime = auditCreateTime;
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public ComplaintInfoBean() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getComplaintedName() {
        return complaintedName;
    }

    public void setComplaintedName(String complaintedName) {
        this.complaintedName = complaintedName == null ? null : complaintedName.trim();
    }

    public String getComplaintedMobile() {
        return complaintedMobile;
    }

    public void setComplaintedMobile(String complaintedMobile) {
        this.complaintedMobile = complaintedMobile == null ? null : complaintedMobile.trim();
    }

    public Integer getComplaintedUserId() {
        return complaintedUserId;
    }

    public void setComplaintedUserId(Integer complaintedUserId) {
        this.complaintedUserId = complaintedUserId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderSerialNo() {
        return orderSerialNo;
    }

    public void setOrderSerialNo(String orderSerialNo) {
        this.orderSerialNo = orderSerialNo == null ? null : orderSerialNo.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public String getComplaintCardID() {
        return complaintCardID;
    }

    public void setComplaintCardID(String complaintCardID) {
        this.complaintCardID = complaintCardID;
    }

    public String getComplaintedCardID() {
        return complaintedCardID;
    }

    public void setComplaintedCardID(String complaintedCardID) {
        this.complaintedCardID = complaintedCardID;
    }

    public Integer getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(Integer complaintType) {
        this.complaintType = complaintType;
    }

    public Integer getComplaintedType() {
        return complaintedType;
    }

    public void setComplaintedType(Integer complaintedType) {
        this.complaintedType = complaintedType;
    }
}