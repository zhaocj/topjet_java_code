package com.topjet.manage.domain.bean;


import com.topjet.manage.domain.model.BaseModel;

import java.util.Date;

public class FeedbackInfoBean extends BaseModel {
    private Integer id;

    private String opinion;

    private Integer userId;

    private Integer createBy;

    private Integer updateBy;

    private Date createTime;
    
    private Date updateTime;

	private String remark;

	private String mobile;

	private String name;

	private String actionName;

    private Integer type;
    
    private static final long serialVersionUID = 1L;
    


    public FeedbackInfoBean() {
        super();
    }
    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
		
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion == null ? null : opinion.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    @Override
    public Integer getUpdateBy() {
        return updateBy;
    }

    @Override
    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }
}