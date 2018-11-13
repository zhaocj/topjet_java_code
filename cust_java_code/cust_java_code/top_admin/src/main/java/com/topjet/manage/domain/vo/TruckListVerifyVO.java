package com.topjet.manage.domain.vo;

public class TruckListVerifyVO {
    private Integer id;
    private String name;//车主姓名
    private String mobile;//手机
    private String createTime;//提交时间
    private String plateNo;//车牌号
    private String typeAndLength;//车型车长
    private String status;//认证状态
    private String auditUser;//操作员
    private String remark;//备注
    private String uid; //车主ID
    private Integer version;
    private String assignName;

    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getTypeAndLength() {
        return typeAndLength;
    }

    public void setTypeAndLength(String typeAndLength) {
        this.typeAndLength = typeAndLength;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
