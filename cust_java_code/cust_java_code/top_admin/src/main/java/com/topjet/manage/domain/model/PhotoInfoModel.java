package com.topjet.manage.domain.model;

import java.util.Date;

public class PhotoInfoModel {
    private Integer id;

    private Integer userId;

    private String url;

    private String urlTobe;

    private Integer type;

    private Date createTime;

    private Date updateTime;

    private String deleted;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlTobe() {
        return urlTobe;
    }

    public void setUrlTobe(String urlTobe) {
        this.urlTobe = urlTobe;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }
}