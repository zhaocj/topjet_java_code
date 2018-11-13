package com.topjet.manage.domain.bean;

import com.topjet.manage.domain.model.BaseModel;

import java.util.Date;

/**
 * Created by liyj on 2017/12/6.
 */
public class HelpCenterBean extends BaseModel {
    private Integer helpCategoryID;

    private Integer version;

    private String name;

    private Integer sortNo;

    private Integer isVisible;

    private String icon;

    private Integer helpQuestionID;

    private String title;

    private Date createTime;

    private Date lastUpdateTime;

    private Integer viewCount;

    private String content;

    private Date startDate;

    private Date endDate;

    private Integer hcSortNo;

    private Integer hpSortNo;

    private Integer versionType;

    private Integer versionFa;

    private Integer versionJie;

    public Integer getVersionFa() {
        return versionFa;
    }

    public void setVersionFa(Integer versionFa) {
        this.versionFa = versionFa;
    }

    public Integer getVersionJie() {
        return versionJie;
    }

    public void setVersionJie(Integer versionJie) {
        this.versionJie = versionJie;
    }

    public Integer getVersionType() {
        return versionType;
    }

    public void setVersionType(Integer versionType) {
        this.versionType = versionType;
    }

    public Integer getHpSortNo() {
        return hpSortNo;
    }

    public void setHpSortNo(Integer hpSortNo) {
        this.hpSortNo = hpSortNo;
    }

    public Integer getHcSortNo() {
        return hcSortNo;
    }

    public void setHcSortNo(Integer hcSortNo) {
        this.hcSortNo = hcSortNo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getHelpCategoryID() {
        return helpCategoryID;
    }

    public void setHelpCategoryID(Integer helpCategoryID) {
        this.helpCategoryID = helpCategoryID;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Integer getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Integer isVisible) {
        this.isVisible = isVisible;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getHelpQuestionID() {
        return helpQuestionID;
    }

    public void setHelpQuestionID(Integer helpQuestionID) {
        this.helpQuestionID = helpQuestionID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
