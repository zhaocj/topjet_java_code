package com.topjet.manage.domain.bean;

import com.topjet.manage.domain.model.BaseModel;

import java.util.Date;

/**
 * Created by liyj on 2017/7/25.
 */
public class SensitiveWordInfoBean extends BaseModel {
    private Integer id;

    private String name;

    private Integer count;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private Integer version;

    private Integer deleted;

    private String createName;

    public SensitiveWordInfoBean() {
    }

    public SensitiveWordInfoBean(Integer id, String name, Integer count, Integer createBy, Date createTime, Integer updateBy, Date updateTime, Integer version, Integer deleted, String createName) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.version = version;
        this.deleted = deleted;
        this.createName = createName;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Integer getUpdateBy() {
        return updateBy;
    }

    @Override
    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }
}
