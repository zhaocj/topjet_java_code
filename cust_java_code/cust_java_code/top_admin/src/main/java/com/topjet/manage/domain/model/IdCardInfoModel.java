package com.topjet.manage.domain.model;

import java.util.Date;

public class IdCardInfoModel {
    private Integer id;

    private String username;

    private String idNo;

    private Date createTime;

    private String remark;

    private String idAddress;

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

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(String idAddress) {
        this.idAddress = idAddress;
    }
}