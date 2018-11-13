package com.topjet.cloud.order.dao.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhangn on 2017/8/24.
 */
public class GoodsInfoModel {
    //ID
    private Integer id;
    //用户ID
    private Integer userId;
    //货源状态
    private Integer goodsStatus;
    //支付方式
    private Integer payStyle;
    //是否可拼车
    private Integer isCarpool;
    //提货时间类型
    private Integer loadDateType;
    //提货时间
    private Date loadDate;
    //货物类型
    private String category;
    //包装方式
    private String packType;
    //装卸类型
    private String loadType;
    //数量类型 1:固定 2:范围
    private Integer quantityType;
    //数量最大值
    private BigDecimal quantityMax;
    //数量最小值
    private BigDecimal quantityMin;
    //单位
    private String unit;
    //货源号
    private String goodsNo;
    //货源请求来源
    private Integer sourceType;
    //是否托管运费 [ 0 : 否  1 :是] 这里托管只是暂时的标记,订单成交时可以再次处理托管属性
    private Integer isFreightFeeManaged;
    //是否隐藏
    private Integer isHidden;
    //是否城际
    private Integer isInner;
    //货源推送数量
    private Integer goodsPushNum;
    //发货时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //数据版本
    private Integer version;
    //删除标记
    private Integer deleted;


    public Integer getIsFreightFeeManaged() {
        return isFreightFeeManaged;
    }

    public void setIsFreightFeeManaged(Integer isFreightFeeManaged) {
        this.isFreightFeeManaged = isFreightFeeManaged;
    }

    public Integer getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(Integer goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

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

    public Integer getPayStyle() {
        return payStyle;
    }

    public void setPayStyle(Integer payStyle) {
        this.payStyle = payStyle;
    }

    public Integer getIsCarpool() {
        return isCarpool;
    }

    public void setIsCarpool(Integer isCarpool) {
        this.isCarpool = isCarpool;
    }

    public Integer getLoadDateType() {
        return loadDateType;
    }

    public void setLoadDateType(Integer loadDateType) {
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
