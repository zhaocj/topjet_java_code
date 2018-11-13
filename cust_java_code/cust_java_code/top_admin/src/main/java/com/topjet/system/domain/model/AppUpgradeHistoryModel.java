package com.topjet.system.domain.model;

import com.topjet.manage.domain.model.BaseModel;

import java.util.Date;

public class AppUpgradeHistoryModel extends BaseModel{
    private Integer id;

    private String systemVersion;

    private String downloadAddress;

    private Integer isForced;

    private String type;

    private String description;

    private Date createTime;

    private Integer deleted;

    private Integer isNotified;

    private String innoVersion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getDownloadAddress() {
        return downloadAddress;
    }

    public void setDownloadAddress(String downloadAddress) {
        this.downloadAddress = downloadAddress;
    }

    public Integer getIsForced() {
        return isForced;
    }

    public void setIsForced(Integer isForced) {
        this.isForced = isForced;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getIsNotified() {
        return isNotified;
    }

    public void setIsNotified(Integer isNotified) {
        this.isNotified = isNotified;
    }

    public String getInnoVersion() {
        return innoVersion;
    }

    public void setInnoVersion(String innoVersion) {
        this.innoVersion = innoVersion;
    }
}