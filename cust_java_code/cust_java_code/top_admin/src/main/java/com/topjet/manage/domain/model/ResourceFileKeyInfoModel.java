package com.topjet.manage.domain.model;

/**
 * Created by liyj on 2018/3/12.
 */
public class ResourceFileKeyInfoModel {
    private Integer id;

    private Integer resourceType;

    private Integer systemType;

    private String systemVersion;

    private Integer innoVersion;

    private String resourceKey;

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

    public String getResourceKey() {
        return resourceKey;
    }

    public void setResourceKey(String resourceKey) {
        this.resourceKey = resourceKey;
    }
}
