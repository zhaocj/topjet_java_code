package com.topjet.manage.domain.bean;

import com.topjet.common.ResultBaseMsg;

import java.util.Date;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-11-08 15:08
 */

public class RegularActivitiesResponseBean extends ResultBaseMsg {

    private Integer id;

    private String pictureUrl;

    private String redirectURL;

    private String title;

    private Date beginDate;

    private Date endDate;

    private Integer appType;

    private String city;

    private String showPage;

    private Date iconBenginDate;

    private Date iconEndDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getShowPage() {
        return showPage;
    }

    public void setShowPage(String showPage) {
        this.showPage = showPage;
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
}