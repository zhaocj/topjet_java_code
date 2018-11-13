package com.topjet.manage.domain.model;

import java.util.Date;

public class RegularActivityModel {
    private Integer id;

    private Integer appType;

    private String pictureKey;

    private String pictureUrl;

    private String redirectURL;

    private String title;

    private Date beginDate;

    private Date endDate;

    private Date iconBenginDate;

    private Date iconEndDate;

    private Date createTime;

    private Date updateTime;

    private Integer createBy;

    private Integer version;

    private Integer deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public String getPictureKey() {
        return pictureKey;
    }

    public void setPictureKey(String pictureKey) {
        this.pictureKey = pictureKey;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getRedirectURL() {
        return redirectURL;
    }

    public void setRedirectURL(String redirectURL) {
        this.redirectURL = redirectURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getIconBenginDate() {
        return iconBenginDate;
    }

    public void setIconBenginDate(Date iconBenginDate) {
        this.iconBenginDate = iconBenginDate;
    }

    public Date getIconEndDate() {
        return iconEndDate;
    }

    public void setIconEndDate(Date iconEndDate) {
        this.iconEndDate = iconEndDate;
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

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
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
}