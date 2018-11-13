package com.topjet.manage.domain.model;

import java.util.Date;

public class ComplaintInfoModel extends BaseModel{
    private Integer id;

    private Integer userId;

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

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private Integer version;

    private Integer deleted;

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
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getComplaintedName() {
        return complaintedName;
    }

    public void setComplaintedName(String complaintedName) {
        this.complaintedName = complaintedName;
    }

    public String getComplaintedMobile() {
        return complaintedMobile;
    }

    public void setComplaintedMobile(String complaintedMobile) {
        this.complaintedMobile = complaintedMobile;
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
        this.orderSerialNo = orderSerialNo;
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
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}