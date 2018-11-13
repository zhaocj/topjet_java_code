package com.topjet.manage.domain.model;

import java.util.Date;

public class HelpQuestionModel {
    private Integer helpQuestionID;

    private Integer helpCategoryID;

    private String title;

    private Date createTime;

    private Date lastUpdateTime;

    private Integer viewCount;

    private Integer sortNo;

    private String content;

    private Integer version;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getHelpQuestionID() {
        return helpQuestionID;
    }

    public void setHelpQuestionID(Integer helpQuestionID) {
        this.helpQuestionID = helpQuestionID;
    }

    public Integer getHelpCategoryID() {
        return helpCategoryID;
    }

    public void setHelpCategoryID(Integer helpCategoryID) {
        this.helpCategoryID = helpCategoryID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

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

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}