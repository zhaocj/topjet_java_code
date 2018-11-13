package com.topjet.manage.domain.model;

import java.util.Date;

public class AnnouncementInfoModel extends BaseModel {
    private Integer id;

    private Integer priority;

    private String title;

    private String content;

    private Integer type;

    private Integer valid;

    private Date beginDate;

    private Date endDate;

    private Date createTime;

    private Integer createBy;

    private Integer deleted;

    private Date updateTime;

    private Integer updateBy;

    private Integer version;

    private String redirectURL;

    private String sendDate;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
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

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Integer getCreateBy() {
        return createBy;
    }

    @Override
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public Integer getUpdateBy() {
        return updateBy;
    }

    @Override
    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getRedirectURL() {
        return redirectURL;
    }

    public void setRedirectURL(String redirectURL) {
        this.redirectURL = redirectURL;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }
}