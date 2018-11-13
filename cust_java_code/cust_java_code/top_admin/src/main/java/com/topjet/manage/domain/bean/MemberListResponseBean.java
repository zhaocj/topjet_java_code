package com.topjet.manage.domain.bean;


import com.topjet.manage.domain.model.BaseModel;

import java.util.Date;

/**
 * Created by liyj on 2017/8/12.
 * 用于用户管理返回参数
 */
public class MemberListResponseBean extends BaseModel {

    private static final long serialVersionUID = -3388893204561107652L;

    private Integer id;

    private Integer version;

    private String username;

    private String mobile;
    private  Integer age;

    private Integer userType;

    private Integer useStatus;

    private Integer userAuthStatus;

    private Integer isSolve;//头像审核状态

    private String resident1;

    private String resident2;

    private String resident3;

    private String auditName;//实名审核人

    private String idAuditName;//身份审核人

    private String headAuditName;//头像审核人

    private String auditAllName;

    private Integer driverOrderCount;

    private Integer cancelDriverOrderCount;

    private Integer ownerOrderCount;

    private String residentCity;

    private String residentCityId;

    private String auditIDReason;

    private String reditRemark;

    private Integer sourceType;

    private String assignName;

    private String iconStatus;
    private Integer bType;//会员类型
    private Integer ubid;//黑名单id
    private Date createTime;//创建时间
    private Date updateTime;//黑名单修改时间
    private Integer deleted;//标志
    private Date BlackCreateTime;//黑名单创建时间

    public String getAuditAllName() {
        return auditAllName;
    }

    public void setAuditAllName(String auditAllName) {
        this.auditAllName = auditAllName;
    }

    public Date getBlackCreateTime() {
        return BlackCreateTime;
    }

    public void setBlackCreateTime(Date blackCreateTime) {
        BlackCreateTime = blackCreateTime;
    }

    public Integer getbType() {
        return bType;
    }

    public void setbType(Integer bType) {
        this.bType = bType;
    }

    public Integer getUbid() {
        return ubid;
    }

    public void setUbid(Integer ubid) {
        this.ubid = ubid;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getIconStatus() {
        return iconStatus;
    }

    public void setIconStatus(String iconStatus) {
        this.iconStatus = iconStatus;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDriverOrderCount() {
        return driverOrderCount;
    }

    public void setDriverOrderCount(Integer driverOrderCount) {
        this.driverOrderCount = driverOrderCount;
    }

    public Integer getOwnerOrderCount() {
        return ownerOrderCount;
    }

    public void setOwnerOrderCount(Integer ownerOrderCount) {
        this.ownerOrderCount = ownerOrderCount;
    }

    public String getResidentCity() {
        return residentCity;
    }

    public void setResidentCity(String residentCity) {
        this.residentCity = residentCity;
    }

    public String getResidentCityId() {
        return residentCityId;
    }

    public void setResidentCityId(String residentCityId) {
        this.residentCityId = residentCityId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getAuditIDReason() {
        return auditIDReason;
    }

    public void setAuditIDReason(String auditIDReason) {
        this.auditIDReason = auditIDReason;
    }

    public String getReditRemark() {
        return reditRemark;
    }

    public void setReditRemark(String reditRemark) {
        this.reditRemark = reditRemark;
    }

    public Integer getUserAuthStatus() {
        return userAuthStatus;
    }

    public void setUserAuthStatus(Integer userAuthStatus) {
        this.userAuthStatus = userAuthStatus;
    }

    public String getIdAuditName() {
        return idAuditName;
    }

    public void setIdAuditName(String idAuditName) {
        this.idAuditName = idAuditName;
    }

    public Integer getCancelDriverOrderCount() {
        return cancelDriverOrderCount;
    }

    public void setCancelDriverOrderCount(Integer cancelDriverOrderCount) {
        this.cancelDriverOrderCount = cancelDriverOrderCount;
    }

    public Integer getIsSolve() {
        return isSolve;
    }

    public void setIsSolve(Integer isSolve) {
        this.isSolve = isSolve;
    }

    public String getHeadAuditName() {
        return headAuditName;
    }

    public void setHeadAuditName(String headAuditName) {
        this.headAuditName = headAuditName;
    }

    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }
}
