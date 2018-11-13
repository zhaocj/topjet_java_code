package com.topjet.manage.domain.bean;

import com.topjet.manage.domain.model.BaseModel;

import java.util.Date;

/**
 * Created by zhouyw on 2016/10/9.
 */
public class MemberAuditListResponseBean extends BaseModel {

    private static final long serialVersionUID = -3388893204881107652L;

    private Integer id;
    private Integer userDataUpdateId;

    private Integer userDataUpdateVersion;

    private String username;
    private Integer age;

    private String mobile;

    private Integer userType;

    private Date createTime;

    private Integer useStatus;

    private Integer userAuthStatus;

    private String reditRemark;

    private String auditName;

    private  String assignName;

    private  Integer headStatus;//头像审核状态

    public Integer getHeadStatus() {
        return headStatus;
    }

    public void setHeadStatus(Integer headStatus) {
        this.headStatus = headStatus;
    }

    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }

    public Integer getUserDataUpdateId() {
        return userDataUpdateId;
    }

    public void setUserDataUpdateId(Integer userDataUpdateId) {
        this.userDataUpdateId = userDataUpdateId;
    }

    public Integer getUserDataUpdateVersion() {
        return userDataUpdateVersion;
    }

    public void setUserDataUpdateVersion(Integer userDataUpdateVersion) {
        this.userDataUpdateVersion = userDataUpdateVersion;
    }



    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public String getReditRemark() {
        return reditRemark;
    }

    public void setReditRemark(String reditRemark) {
        this.reditRemark = reditRemark;
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public Integer getUserAuthStatus() {
        return userAuthStatus;
    }

    public void setUserAuthStatus(Integer userAuthStatus) {
        this.userAuthStatus = userAuthStatus;
    }
}
