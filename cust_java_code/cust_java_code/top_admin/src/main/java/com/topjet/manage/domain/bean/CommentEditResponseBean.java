package com.topjet.manage.domain.bean;

/**
 * Created by Tym on 2016/8/25.
 */
public class CommentEditResponseBean {
    private String id;
    private String orderNo;
    private String commentName;
    private String commentedName;
    private String commentMobile;
    private String commentedMobile;
    private String commentType;
    private String commentedType;
    private String content;
    private String createTime;
    private String total;
    private String inTime;//结款及时/送货及时
    private String facticity;//如实描述/货物良好
    private String attitude;//态度良好
    private String picture1;
    private String picture2;
    private String picture3;
    private String picture4;
    private Integer version;
    private String operatorRemark;
    private String pictureUrl;

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getOperatorRemark() {
        return operatorRemark;
    }

    public void setOperatorRemark(String operatorRemark) {
        this.operatorRemark = operatorRemark;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAttitude() {
        return attitude;
    }

    public void setAttitude(String attitude) {
        this.attitude = attitude;
    }

    public String getCommentedMobile() {
        return commentedMobile;
    }

    public void setCommentedMobile(String commentedMobile) {
        this.commentedMobile = commentedMobile;
    }

    public String getCommentedName() {
        return commentedName;
    }

    public void setCommentedName(String commentedName) {
        this.commentedName = commentedName;
    }

    public String getCommentedType() {
        return commentedType;
    }

    public void setCommentedType(String commentedType) {
        this.commentedType = commentedType;
    }

    public String getCommentMobile() {
        return commentMobile;
    }

    public void setCommentMobile(String commentMobile) {
        this.commentMobile = commentMobile;
    }

    public String getCommentName() {
        return commentName;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName;
    }

    public String getCommentType() {
        return commentType;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFacticity() {
        return facticity;
    }

    public void setFacticity(String facticity) {
        this.facticity = facticity;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPicture1() {
        return picture1;
    }

    public void setPicture1(String picture1) {
        this.picture1 = picture1;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    public String getPicture3() {
        return picture3;
    }

    public void setPicture3(String picture3) {
        this.picture3 = picture3;
    }

    public String getPicture4() {
        return picture4;
    }

    public void setPicture4(String picture4) {
        this.picture4 = picture4;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
