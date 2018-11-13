package com.topjet.cloud.user.dao.model;

/**
 * Created by hongtaoer-win on 2017/8/8.
 * UserOtherInfo 用户附属信息表 Model
 */
public class UserOtherInfoModel {
    //ID
    private Integer id;
    //用户的ID
    private Integer userId;
    //常驻地一级城市地址
    private String resident1;
    //常驻地三级城市地址
    private String resident2;
    //常驻地二级城市地址
    private String resident3;
    //常驻地城市ID
    private Integer residentCityId;
    //公司名称
    private String companyName;
    //经营地址
    private String businessAddress;
    //经营地址ID
    private Integer businessAddressCityId;
    //安全提示
    private String safetyTips;
    //注册时手机唯一码
    private String registeredIMEI;
    //注册城市ID
    private Integer registeredCityId;
    //身份证地址
    private String idAddress;
    //固定电话
    private String telephone;
    //管理平台审核备注
    private String reditRemark;

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
