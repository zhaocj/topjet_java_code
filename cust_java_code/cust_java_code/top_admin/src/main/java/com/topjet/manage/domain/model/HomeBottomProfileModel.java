package com.topjet.manage.domain.model;

import java.util.Date;

public class HomeBottomProfileModel extends BaseModel{
    private Integer id;

    private Integer appType;

    private String text;

    private String textDownColor;

    private String textNormalColor;

    private String iconDownUrl;

    private String iconNormalUrl;

    private Integer orderNum;

    private Date beginDate;
    private Date endDate;
    private Integer deleted;

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextDownColor() {
        return textDownColor;
    }

    public void setTextDownColor(String textDownColor) {
        this.textDownColor = textDownColor;
    }

    public String getTextNormalColor() {
        return textNormalColor;
    }

    public void setTextNormalColor(String textNormalColor) {
        this.textNormalColor = textNormalColor;
    }

    public String getIconDownUrl() {
        return iconDownUrl;
    }

    public void setIconDownUrl(String iconDownUrl) {
        this.iconDownUrl = iconDownUrl;
    }

    public String getIconNormalUrl() {
        return iconNormalUrl;
    }

    public void setIconNormalUrl(String iconNormalUrl) {
        this.iconNormalUrl = iconNormalUrl;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }
}