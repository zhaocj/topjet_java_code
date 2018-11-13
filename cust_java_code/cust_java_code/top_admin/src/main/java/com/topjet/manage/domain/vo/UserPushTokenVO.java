package com.topjet.manage.domain.vo;

/**
 * Created by zhangn on 2017/11/14.
 */
public class UserPushTokenVO {

    private Integer id;
    private String ownerToken;
    private String driverToken;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwnerToken() {
        return ownerToken;
    }

    public void setOwnerToken(String ownerToken) {
        this.ownerToken = ownerToken;
    }

    public String getDriverToken() {
        return driverToken;
    }

    public void setDriverToken(String driverToken) {
        this.driverToken = driverToken;
    }
}
