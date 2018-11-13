package com.topjet.manage.domain.bean;


import com.topjet.manage.domain.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

public class UserCommentBean extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3898929455015275959L;

	private Integer version;
	
	private String commentedUserType;

	private BigDecimal total;

	private String content;

	private Date createTime;

	private Integer id;

	private String commentedUserName;

	private Integer commentId;

	private Integer commentedId;

	private String commentedUserMobile;

	private String commentUserResident1;

	private String commentUserResident2;

	private String commentUserName;

	private String commentUserMobile;

	private String commentUserType;
     
	private Integer inTime;

    private Integer facticity;

    private Integer rationality;

    private Integer attitude;
    
    private Integer  deleted ;
    
    private Date startTime ;
    
    private Date endTime ;
    
    private String serialNo ;

    private Integer orderId ;

	private String reditRemark;

	private String pictureUrl;

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getReditRemark() {
		return reditRemark;
	}

	public void setReditRemark(String reditRemark) {
		this.reditRemark = reditRemark;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Integer getInTime() {
		return inTime;
	}

	public void setInTime(Integer inTime) {
		this.inTime = inTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getFacticity() {
		return facticity;
	}

	public void setFacticity(Integer facticity) {
		this.facticity = facticity;
	}

	public Integer getRationality() {
		return rationality;
	}

	public void setRationality(Integer rationality) {
		this.rationality = rationality;
	}

	public Integer getAttitude() {
		return attitude;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setAttitude(Integer attitude) {
		this.attitude = attitude;
	}

	public String getCommentedUserType() {
		return commentedUserType;
	}

	public void setCommentedUserType(String commentedUserType) {
		this.commentedUserType = commentedUserType;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCommentedUserName() {
		return commentedUserName;
	}

	public void setCommentedUserName(String commentedUserName) {
		this.commentedUserName = commentedUserName;
	}

	public String getCommentedUserMobile() {
		return commentedUserMobile;
	}

	public void setCommentedUserMobile(String commentedUserMobile) {
		this.commentedUserMobile = commentedUserMobile;
	}

	public String getCommentUserResident1() {
		return commentUserResident1;
	}

	public void setCommentUserResident1(String commentUserResident1) {
		this.commentUserResident1 = commentUserResident1;
	}

	public String getCommentUserResident2() {
		return commentUserResident2;
	}

	public void setCommentUserResident2(String commentUserResident2) {
		this.commentUserResident2 = commentUserResident2;
	}

	public String getCommentUserName() {
		return commentUserName;
	}

	public void setCommentUserName(String commentUserName) {
		this.commentUserName = commentUserName;
	}

	public String getCommentUserMobile() {
		return commentUserMobile;
	}

	public void setCommentUserMobile(String commentUserMobile) {
		this.commentUserMobile = commentUserMobile;
	}

	public String getCommentUserType() {
		return commentUserType;
	}

	public void setCommentUserType(String commentUserType) {
		this.commentUserType = commentUserType;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Integer getCommentedId() {
		return commentedId;
	}

	public void setCommentedId(Integer commentedId) {
		this.commentedId = commentedId;
	}
}
