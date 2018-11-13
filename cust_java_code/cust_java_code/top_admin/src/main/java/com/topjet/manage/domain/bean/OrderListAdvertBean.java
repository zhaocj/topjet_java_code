package com.topjet.manage.domain.bean;


import com.topjet.manage.domain.model.BaseModel;

import java.util.Date;

public class OrderListAdvertBean extends BaseModel {

    private Integer id;

    private String webTitle;

    private String text;

    private Integer indexNumber;

    private Integer reorder;

    private String pictureUrl;

    private String pictureKey;

    private String turnURL;

    private Integer valid;

    private Date beginDate;

    private Date endDate;

    private Date createTime;

    private Date updateTime;

    private Integer appType;

    private Integer  rank;

    private Integer deleted;

    private Integer version;

    private Integer createBy;

    private Integer updateBy;

    private String createName;

    private String updateName;

    private String currentDate;

    private static final long serialVersionUID = -199L;

    public OrderListAdvertBean(Integer id, String webTitle, String text, Integer indexNumber, Integer reorder, String pictureUrl,String pictureKey, String turnURL, Integer valid, Date beginDate, Date endDate, Date createTime, Date updateTime, Integer appType, Integer rank, Integer deleted, Integer version, Integer createBy, Integer updateBy, String createName, String updateName) {
        this.id = id;
        this.webTitle = webTitle;
        this.text = text;
        this.indexNumber = indexNumber;
        this.reorder = reorder;
        this.pictureUrl = pictureUrl;
        this.pictureKey = pictureKey;
        this.turnURL = turnURL;
        this.valid = valid;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.appType = appType;
        this.rank = rank;
        this.deleted = deleted;
        this.version = version;
        this.createBy = createBy;
        this.updateBy = updateBy;
        this.createName = createName;
        this.updateName = updateName;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
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

    public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public OrderListAdvertBean() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTurnURL() {
        return turnURL;
    }

    public void setTurnURL(String turnURL) {
        this.turnURL = turnURL == null ? null : turnURL.trim();
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

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }
}