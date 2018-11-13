package com.topjet.manage.domain.bean;

import com.topjet.manage.domain.model.BaseModel;

import java.util.Date;

/**
 * Created by tsj010 on 2018/5/7.
 */
public class CompanyAuditBean  extends BaseModel {

    private  Integer  id ; // userid
    private String sysUserId;//任务分配者ID
    private  int  userNature;//用户类型  0、个人用户（默认）1、企业用户
    private  int companyStatus;//企业认证状态 0、未认证（默认） 1、认证中 2、认证失败 3、认证通过',
    private  int  identityType ;//身份类型）：0、非企业用户（默认）1、物流公司 2、发货商家
    private String auditName;//审核人
    private  String assignName;//分配人
    private  String companyName;//企业名称
    private Date createTime;//注册时间
    private String mobile;//电话



    //营业执照
    private String businessPhotoUrl;
    private String businessPhotoUrlTobe;
    private String businessPhotoUrlTobe1;
    private String businessPhotoType;
    private String businesPhotoBase64Url;



    //门店图片
    private String  shopFrontPhotoUrl;
    private String shopFrontPhotoUrlTobe;
    private String shopFrontPhotoUrlTobe1;
    private String shopFrontType;
    private String shopFrontPhotoBase64Url;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusinessPhotoUrl() {
        return businessPhotoUrl;
    }

    public void setBusinessPhotoUrl(String businessPhotoUrl) {
        this.businessPhotoUrl = businessPhotoUrl;
    }

    public String getBusinessPhotoUrlTobe() {
        return businessPhotoUrlTobe;
    }

    public void setBusinessPhotoUrlTobe(String businessPhotoUrlTobe) {
        this.businessPhotoUrlTobe = businessPhotoUrlTobe;
    }

    public String getBusinessPhotoUrlTobe1() {
        return businessPhotoUrlTobe1;
    }

    public void setBusinessPhotoUrlTobe1(String businessPhotoUrlTobe1) {
        this.businessPhotoUrlTobe1 = businessPhotoUrlTobe1;
    }

    public String getBusinessPhotoType() {
        return businessPhotoType;
    }

    public void setBusinessPhotoType(String businessPhotoType) {
        this.businessPhotoType = businessPhotoType;
    }

    public String getBusinesPhotoBase64Url() {
        return businesPhotoBase64Url;
    }

    public void setBusinesPhotoBase64Url(String businesPhotoBase64Url) {
        this.businesPhotoBase64Url = businesPhotoBase64Url;
    }

    public String getShopFrontPhotoUrl() {
        return shopFrontPhotoUrl;
    }

    public void setShopFrontPhotoUrl(String shopFrontPhotoUrl) {
        this.shopFrontPhotoUrl = shopFrontPhotoUrl;
    }

    public String getShopFrontPhotoUrlTobe() {
        return shopFrontPhotoUrlTobe;
    }

    public void setShopFrontPhotoUrlTobe(String shopFrontPhotoUrlTobe) {
        this.shopFrontPhotoUrlTobe = shopFrontPhotoUrlTobe;
    }

    public String getShopFrontPhotoUrlTobe1() {
        return shopFrontPhotoUrlTobe1;
    }

    public void setShopFrontPhotoUrlTobe1(String shopFrontPhotoUrlTobe1) {
        this.shopFrontPhotoUrlTobe1 = shopFrontPhotoUrlTobe1;
    }

    public String getShopFrontType() {
        return shopFrontType;
    }

    public void setShopFrontType(String shopFrontType) {
        this.shopFrontType = shopFrontType;
    }

    public String getShopFrontPhotoBase64Url() {
        return shopFrontPhotoBase64Url;
    }

    public void setShopFrontPhotoBase64Url(String shopFrontPhotoBase64Url) {
        this.shopFrontPhotoBase64Url = shopFrontPhotoBase64Url;
    }


    public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    public int getUserNature() {
        return userNature;
    }

    public void setUserNature(int userNature) {
        this.userNature = userNature;
    }

    public int getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(int companyStatus) {
        this.companyStatus = companyStatus;
    }

    public int getIdentityType() {
        return identityType;
    }

    public void setIdentityType(int identityType) {
        this.identityType = identityType;
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
