package com.topjet.manage.domain.model;

import java.math.BigDecimal;
import java.util.Date;

public class UserCommentInfoModel {
    private Integer id;

    private Integer commentUser;

    private Integer commentedUser;

    private Integer orderId;

    private Integer type;

    private Integer isAnonymous;

    private BigDecimal total;

    private String selectAssess;

    private String content;

    private Date createTime;

    private Date updateTime;

    private Integer version;

    private Integer deleted;

    private String pictureUrl;

    private String orderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(Integer commentUser) {
        this.commentUser = commentUser;
    }

    public Integer getCommentedUser() {
        return commentedUser;
    }

    public void setCommentedUser(Integer commentedUser) {
        this.commentedUser = commentedUser;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(Integer isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getSelectAssess() {
        return selectAssess;
    }

    public void setSelectAssess(String selectAssess) {
        this.selectAssess = selectAssess;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}