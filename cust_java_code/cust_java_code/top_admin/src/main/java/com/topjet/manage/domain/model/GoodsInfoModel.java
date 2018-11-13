package com.topjet.manage.domain.model;

import java.math.BigDecimal;
import java.util.Date;

public class GoodsInfoModel {
    private Integer id;

    private Integer userId;

    private Byte goodsStatus;

    private Byte payStyle;

    private Boolean isCarpool;

    private Boolean loadDateType;

    private Date loadDate;

    private String category;

    private String packType;

    private String loadType;

    private Integer quantityType;

    private BigDecimal quantityMax;

    private BigDecimal quantityMin;

    private String unit;

    private String goodsNo;

    private Integer sourceType;

    private Integer isHidden;

    private Integer isInner;

    private Integer goodsPushNum;

    private Date createTime;

    private Date updateTime;

    private Integer version;

    private Integer deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(Byte goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public Byte getPayStyle() {
        return payStyle;
    }

    public void setPayStyle(Byte payStyle) {
        this.payStyle = payStyle;
    }

    public Boolean getIsCarpool() {
        return isCarpool;
    }

    public void setIsCarpool(Boolean isCarpool) {
        this.isCarpool = isCarpool;
    }

    public Boolean getLoadDateType() {
        return loadDateType;
    }

    public void setLoadDateType(Boolean loadDateType) {
        this.loadDateType = loadDateType;
    }

    public Date getLoadDate() {
        return loadDate;
    }

    public void setLoadDate(Date loadDate) {
        this.loadDate = loadDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPackType() {
        return packType;
    }

    public void setPackType(String packType) {
        this.packType = packType;
    }

    public String getLoadType() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType;
    }

    public Integer getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(Integer quantityType) {
        this.quantityType = quantityType;
    }

    public BigDecimal getQuantityMax() {
        return quantityMax;
    }

    public void setQuantityMax(BigDecimal quantityMax) {
        this.quantityMax = quantityMax;
    }

    public BigDecimal getQuantityMin() {
        return quantityMin;
    }

    public void setQuantityMin(BigDecimal quantityMin) {
        this.quantityMin = quantityMin;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Integer isHidden) {
        this.isHidden = isHidden;
    }

    public Integer getIsInner() {
        return isInner;
    }

    public void setIsInner(Integer isInner) {
        this.isInner = isInner;
    }

    public Integer getGoodsPushNum() {
        return goodsPushNum;
    }

    public void setGoodsPushNum(Integer goodsPushNum) {
        this.goodsPushNum = goodsPushNum;
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