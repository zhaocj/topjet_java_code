package com.topjet.manage.domain.model;

import java.util.Date;

public class SysUserModel extends BaseModel {
    private Integer id;

    private String email;

    private String pwd;

    private String nickName;

    private Integer state;

    private Integer loginCount;

    private Date loginTime;

    private Integer deleted;

    private Date createTime;

    private Date updateTime;

    private Integer createBy;

    private Integer updateBy;

    private Integer superAdmin;

    private Integer version;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public Integer getCreateBy() {
        return createBy;
    }

    @Override
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    @Override
    public Integer getUpdateBy() {
        return updateBy;
    }

    @Override
    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(Integer superAdmin) {
        this.superAdmin = superAdmin;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}