package com.topjet.cloud.user.dao.model;

import java.util.Date;

/**
 * Created by hongtaoer-win on 2017/8/8.
 * UserInfo 用户详情映射model
 */
public class UserInfoModel {
    //用户ID
    private Integer id;
    //身份证号
    private String idNo;
    //姓名
    private String username;
    //手机号码
    private String mobile;
    //会员类型
    private Integer userType;
    //实名认证状态
    private Integer useStatus;
    //身份认证状态
    private Integer userAuthStatus;
    //头像认证状态
    private Integer iconStatus;
    //性别
    private Integer sex;
    //钱包ID
    private Integer walletId;
    //是否放空承保 0关1开
    private Integer isGuaranteed;
    //是否开启匿名 0关1开',
    private Integer isAnonymous;
    //注册时间
    private Date createTime;
    //删除标记
    private Integer deleted;

    public Integer getIconStatus() {
        return iconStatus;
    }

    public void setIconStatus(Integer iconStatus) {
        this.iconStatus = iconStatus;
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
}
