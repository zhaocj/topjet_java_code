package com.topjet.manage.domain.vo;


import com.topjet.manage.domain.model.BaseModel;

/**
 * Created by zhaocj on 2017/9/15.
 */
public class TruckListVerifyQuery extends BaseModel {
    private String mobile;//手机
    private String plateNo;//车牌号
    private String auditStatus;//状态
    private String auditUser;//审核人

    private String name;//车主姓名
    private String typeAndLength;//车型车长
    private String status;//认证状态
    private String remark;//备注

    private String flag;//标记是否为分配任务客服

    private String sysUserId;//任务分配者ID

    private String plateNo1;

    private String plateNo2;

    private String plateNo3;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeAndLength() {
        return typeAndLength;
    }

    public void setTypeAndLength(String typeAndLength) {
        this.typeAndLength = typeAndLength;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
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
}
