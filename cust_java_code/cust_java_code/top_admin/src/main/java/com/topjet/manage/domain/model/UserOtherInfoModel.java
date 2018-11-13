package com.topjet.manage.domain.model;

import java.math.BigDecimal;

public class UserOtherInfoModel {
    private Integer id;

    private Integer userId;
    private Integer identityType;

    private String resident1;

    private String resident2;

    private String resident3;

    private Integer residentCityId;

    private String companyName;

    private String businessAddress;

    private Integer businessAddressCityId;

    private BigDecimal  businessAddressLongitude;
    private BigDecimal  businessAddressLatitude;
    private String safetyTips;

    private String registeredIMEI;

    private Integer registeredCityId;

    private String idAddress;

    private String telephone;

    private String reditRemark;
    private String creditCode;
    private String internalRemark;

    public BigDecimal getBusinessAddressLongitude() {
        return businessAddressLongitude;
    }

    public void setBusinessAddressLongitude(BigDecimal businessAddressLongitude) {
        this.businessAddressLongitude = businessAddressLongitude;
    }

    public BigDecimal getBusinessAddressLatitude() {
        return businessAddressLatitude;
    }

    public void setBusinessAddressLatitude(BigDecimal businessAddressLatitude) {
        this.businessAddressLatitude = businessAddressLatitude;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getInternalRemark() {
        return internalRemark;
    }

    public void setInternalRemark(String internalRemark) {
        this.internalRemark = internalRemark;
    }

    public Integer getIdentityType() {
        return identityType;
    }

    public void setIdentityType(Integer identityType) {
        this.identityType = identityType;
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

    public Integer getResidentCityId() {
        return residentCityId;
    }

    public void setResidentCityId(Integer residentCityId) {
        this.residentCityId = residentCityId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public Integer getBusinessAddressCityId() {
        return businessAddressCityId;
    }

    public void setBusinessAddressCityId(Integer businessAddressCityId) {
        this.businessAddressCityId = businessAddressCityId;
    }

    public String getSafetyTips() {
        return safetyTips;
    }

    public void setSafetyTips(String safetyTips) {
        this.safetyTips = safetyTips;
    }

    public String getRegisteredIMEI() {
        return registeredIMEI;
    }

    public void setRegisteredIMEI(String registeredIMEI) {
        this.registeredIMEI = registeredIMEI;
    }

    public Integer getRegisteredCityId() {
        return registeredCityId;
    }

    public void setRegisteredCityId(Integer registeredCityId) {
        this.registeredCityId = registeredCityId;
    }

    public String getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(String idAddress) {
        this.idAddress = idAddress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getReditRemark() {
        return reditRemark;
    }

    public void setReditRemark(String reditRemark) {
        this.reditRemark = reditRemark;
    }
}