package com.topjet.common.auth.service.dto;

/**
 * Created by tsj010 on 2018/5/11.
 */
public class CompanyRequestDTO {
    /**
     * 新增企业信息接口
     * 目的：同步配货企业信息至tms
     */
    private String OuterAccount;//配货用户ID
    private String EnterpriseName;//企业名称
    private String Mobile;//手机号
    private String RealName;//用户姓名
    private String RegDateTime;//注册日期
    private String RegCity;//注册城市 名称
    private String BusinessLicense;//营业执照 key
    private String BusinessFacade;//门头照 key
    private String Telephone;//固定电话
    private String Province;//运营地址 省
    private String City;//运营地址 市
    private String Area;//运营地址 区
    private String EntAddress;//运营地址  详细地址
    private String Longitude;//运营地址 经度
    private String Latitude;//运营地址  纬度
    private String WalletId;//钱包ID
    private String Password;//用户登录密码
    private String EntCode;//	信用代码
    private String AuditUser;//	审核人
    private String AuditTime;//审核时间
    private String AuditRemark;//审核备注
    private String Remark;//内部备注


    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getWalletId() {
        return WalletId;
    }

    public void setWalletId(String walletId) {
        WalletId = walletId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEntCode() {
        return EntCode;
    }

    public void setEntCode(String entCode) {
        EntCode = entCode;
    }

    public String getAuditUser() {
        return AuditUser;
    }

    public void setAuditUser(String auditUser) {
        AuditUser = auditUser;
    }

    public String getAuditTime() {
        return AuditTime;
    }

    public void setAuditTime(String auditTime) {
        AuditTime = auditTime;
    }

    public String getAuditRemark() {
        return AuditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        AuditRemark = auditRemark;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getOuterAccount() {
        return OuterAccount;
    }

    public void setOuterAccount(String outerAccount) {
        OuterAccount = outerAccount;
    }

    public String getEnterpriseName() {
        return EnterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        EnterpriseName = enterpriseName;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getRegDateTime() {
        return RegDateTime;
    }

    public void setRegDateTime(String regDateTime) {
        RegDateTime = regDateTime;
    }

    public String getRegCity() {
        return RegCity;
    }

    public void setRegCity(String regCity) {
        RegCity = regCity;
    }

    public String getBusinessLicense() {
        return BusinessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        BusinessLicense = businessLicense;
    }

    public String getBusinessFacade() {
        return BusinessFacade;
    }

    public void setBusinessFacade(String businessFacade) {
        BusinessFacade = businessFacade;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getEntAddress() {
        return EntAddress;
    }

    public void setEntAddress(String entAddress) {
        EntAddress = entAddress;
    }
}
