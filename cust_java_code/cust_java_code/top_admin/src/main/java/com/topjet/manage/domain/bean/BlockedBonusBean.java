package com.topjet.manage.domain.bean;


import com.topjet.manage.domain.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

public class BlockedBonusBean extends BaseModel {
    private Integer id;

    private Integer orderId;

    private Integer reason;

    private String remark;

    private Boolean status;

    private Date createTime;

    private Integer deleted;

    private Integer version;

    private Date updateTime;
    private Date startDate;
    private Date endDate;

    private Integer updateBy;

    private BigDecimal amount;

    private Integer userId;

    private String userName;

    private Integer type;

    private Integer recommendationId;
    
    private String recoName;
    
    private String blockMobile;
    
    private String recoMobile;
    
    private String orderNo;
    private String depart1;
    private String depart2;
    private String depart3;
    private String destination1;
    private String destination2;
    private String destination3;
    private String resource;
    private String blockIMEI;
    private String recoIMEI;
    private String reditRemark;

    public String getReditRemark() {
        return reditRemark;
    }

    public void setReditRemark(String reditRemark) {
        this.reditRemark = reditRemark;
    }

    private static final long serialVersionUID = 1L;


    public BlockedBonusBean() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getRecoName() {
		return recoName;
	}

	public void setRecoName(String recoName) {
		this.recoName = recoName;
	}

	public String getBlockMobile() {
		return blockMobile;
	}

	public void setBlockMobile(String blockMobile) {
		this.blockMobile = blockMobile;
	}

	public String getRecoMobile() {
		return recoMobile;
	}

	public void setRecoMobile(String recoMobile) {
		this.recoMobile = recoMobile;
	}

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getReason() {
        return reason;
    }

    public void setReason(Integer reason) {
        this.reason = reason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(Integer recommendationId) {
        this.recommendationId = recommendationId;
    }

    public String getDepart1() {
        return depart1;
    }

    public void setDepart1(String depart1) {
        this.depart1 = depart1;
    }

    public String getDepart2() {
        return depart2;
    }

    public void setDepart2(String depart2) {
        this.depart2 = depart2;
    }

    public String getDepart3() {
        return depart3;
    }

    public void setDepart3(String depart3) {
        this.depart3 = depart3;
    }

    public String getDestination1() {
        return destination1;
    }

    public void setDestination1(String destination1) {
        this.destination1 = destination1;
    }

    public String getDestination2() {
        return destination2;
    }

    public void setDestination2(String destination2) {
        this.destination2 = destination2;
    }

    public String getDestination3() {
        return destination3;
    }

    public void setDestination3(String destination3) {
        this.destination3 = destination3;
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

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getRecoIMEI() {
        return recoIMEI;
    }

    public void setRecoIMEI(String recoIMEI) {
        this.recoIMEI = recoIMEI;
    }

    public String getBlockIMEI() {
        return blockIMEI;
    }

    public void setBlockIMEI(String blockIMEI) {
        this.blockIMEI = blockIMEI;
    }
}