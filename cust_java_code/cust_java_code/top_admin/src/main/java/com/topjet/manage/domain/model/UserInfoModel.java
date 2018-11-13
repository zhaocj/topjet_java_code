package com.topjet.manage.domain.model;

import java.util.Date;

public class UserInfoModel extends BaseModel{
    private Integer id;

    private String idNo;

    private String username;

    private String mobile;

    private Integer userType;
    private Integer userNature;

    private Integer companyStatus;

    private Integer useStatus;

    private Integer userAuthStatus;

    private Integer iconStatus;

    private Integer sex;

    private Integer walletId;

    private Integer isGuaranteed;

    private Integer isAnonymous;

    private Date createTime;

    private Integer deleted;
    private Integer version;

    private String companyName;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getUserNature() {
        return userNature;
    }

    public void setUserNature(Integer userNature) {
        this.userNature = userNature;
    }

    public Integer getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(Integer companyStatus) {
        this.companyStatus = companyStatus;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
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

    public Integer getUserAuthStatus() {
        return userAuthStatus;
    }

    public void setUserAuthStatus(Integer userAuthStatus) {
        this.userAuthStatus = userAuthStatus;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }

    public Integer getIsGuaranteed() {
        return isGuaranteed;
    }

    public void setIsGuaranteed(Integer isGuaranteed) {
        this.isGuaranteed = isGuaranteed;
    }

    public Integer getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(Integer isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getIconStatus() {
        return iconStatus;
    }

    public void setIconStatus(Integer iconStatus) {
        this.iconStatus = iconStatus;
    }
}