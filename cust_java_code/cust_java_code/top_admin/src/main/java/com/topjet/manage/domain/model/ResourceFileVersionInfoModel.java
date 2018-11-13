package com.topjet.manage.domain.model;

import java.util.Date;

public class ResourceFileVersionInfoModel {
    private Integer id;

    private Integer resourceType;

    private Integer systemType;

    private String systemVersion;

    private Integer innoVersion;

    private String description;

    private Date startTime;

    private Date endTime;

    private Integer deleted;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getSystemType() {
        return systemType;
    }

    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public Integer getInnoVersion() {
        return innoVersion;
    }

    public void setInnoVersion(Integer innoVersion) {
        this.innoVersion = innoVersion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}