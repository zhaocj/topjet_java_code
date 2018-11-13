package com.topjet.cloud.user.dao.model;

import java.util.Date;

/**
 * Created by hongtaoer-win on 2017/8/8.
 * UserAccount 登录附属表映射Moel
 */
public class UserAccountModel {
    //主键
    private Integer id;
    //用户ID
    private Integer userId;
    //用户手机
    private String mobile;
    //用户登录密码
    private String userPass;
    //手机唯一码
    private String imei;
    //登录系统类型 1 Android司机 2.Android货主 3 iOS司机 4 ios货主)
    private Integer systemType;
    //登录时获取的详细地址
    private String loginAddress;
    //数据更新时间
    private Date updateTime;
    //数据创建时间
    private Date createTime;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Integer getSystemType() {
        return systemType;
    }

    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
    }

    public String getLoginAddress() {
        return loginAddress;
    }

    public void setLoginAddress(String loginAddress) {
        this.loginAddress = loginAddress;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
