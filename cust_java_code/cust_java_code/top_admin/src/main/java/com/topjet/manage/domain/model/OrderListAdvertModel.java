package com.topjet.manage.domain.model;

import java.util.Date;

public class OrderListAdvertModel {
    private Integer id;

    private String webTitle;

    private String text;

    private Integer indexNumber;

    private Integer reorder;

    private String pictureUrl;

    private String pictureKey;

    private String turnURL;

    private Date beginDate;

    private Date endDate;

    private Date createTime;

    private Date updateTime;

    private Integer createBy;

    private Integer version;

    private Integer appType;

    private Integer valid;

    private Integer deleted;

    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(Integer indexNumber) {
        this.indexNumber = indexNumber;
    }

    public Integer getReorder() {
        return reorder;
    }

    public void setReorder(Integer reorder) {
        this.reorder = reorder;
    }

    public String getTurnURL() {
        return turnURL;
    }

    public void setTurnURL(String turnURL) {
        this.turnURL = turnURL;
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

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getPictureKey() {
        return pictureKey;
    }

    public void setPictureKey(String pictureKey) {
        this.pictureKey = pictureKey;
    }
}