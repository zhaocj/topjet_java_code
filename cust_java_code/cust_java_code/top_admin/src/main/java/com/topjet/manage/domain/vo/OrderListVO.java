package com.topjet.manage.domain.vo;

/**
 * Created by tsj006 on 2016/9/1.
 */
public class OrderListVO {
    private Integer id;
    private Integer version;//版本
    private String orderNo;//订单号
    private String createDate;//创建时间
    private String username;//创建人
    private String mobile;//手机号
    private String orderStatus;//订单状态
    private String isFreightFeeManaged;//运费托管状态
    private String departCity;//提货地址
    private String destinationCity;//收货地址
    private String sysName;//操作人
    private Integer isHidden;//0显示1隐藏
    private String uid;//创建人id
    private String freightFee;//订单运费总金额
    private String deliveryFee;//货到付款金额
    private String deliveryFeeStatus;//货到付款状态
    private String isDeliveryFeeManaged;//货到付款是否托管
    private String backFee;//回单付金额
    private String backFeeStatus;//回单付状态
    private String aheadFee;//提单付金额
    private String aheadFeeStatus;//提单付状态
    private String isAheadFeeManaged;//提单付金额是否托管
    private Integer orderFrozenState;//订单冻结状态
    private Integer orderAffiliateId;//订单附属表orderAffiliate 主键
    private Integer orderAffiliateVersion;//订单附属表orderAffiliate 版本号
    private Integer goodsId;//货源id
    private String reditRemark;

    public String getReditRemark() {
        return reditRemark;
    }

    public void setReditRemark(String reditRemark) {
        this.reditRemark = reditRemark;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getOrderAffiliateVersion() {
        return orderAffiliateVersion;
    }

    public void setOrderAffiliateVersion(Integer orderAffiliateVersion) {
        this.orderAffiliateVersion = orderAffiliateVersion;
    }

    public Integer getOrderFrozenState() {
        return orderFrozenState;
    }

    public void setOrderFrozenState(Integer orderFrozenState) {
        this.orderFrozenState = orderFrozenState;
    }

    public Integer getOrderAffiliateId() {
        return orderAffiliateId;
    }

    public void setOrderAffiliateId(Integer orderAffiliateId) {
        this.orderAffiliateId = orderAffiliateId;
    }

    public String getFreightFee() {
        return freightFee;
    }

    public void setFreightFee(String freightFee) {
        this.freightFee = freightFee;
    }

    public String getIsDeliveryFeeManaged() {
        return isDeliveryFeeManaged;
    }

    public void setIsDeliveryFeeManaged(String isDeliveryFeeManaged) {
        this.isDeliveryFeeManaged = isDeliveryFeeManaged;
    }

    public String getIsAheadFeeManaged() {
        return isAheadFeeManaged;
    }

    public void setIsAheadFeeManaged(String isAheadFeeManaged) {
        this.isAheadFeeManaged = isAheadFeeManaged;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }
    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }
    public String getDeliveryFeeStatus() {
        return deliveryFeeStatus;
    }

    public void setDeliveryFeeStatus(String deliveryFeeStatus) {
        this.deliveryFeeStatus = deliveryFeeStatus;
    }

    public String getBackFee() {
        return backFee;
    }

    public void setBackFee(String backFee) {
        this.backFee = backFee;
    }

    public String getBackFeeStatus() {
        return backFeeStatus;
    }

    public void setBackFeeStatus(String backFeeStatus) {
        this.backFeeStatus = backFeeStatus;
    }

    public String getAheadFee() {
        return aheadFee;
    }

    public void setAheadFee(String aheadFee) {
        this.aheadFee = aheadFee;
    }

    public String getAheadFeeStatus() {
        return aheadFeeStatus;
    }

    public void setAheadFeeStatus(String aheadFeeStatus) {
        this.aheadFeeStatus = aheadFeeStatus;
    }


    public String getIsFreightFeeManaged() {
        return isFreightFeeManaged;
    }
    public void setIsFreightFeeManaged(String isFreightFeeManaged) {
        this.isFreightFeeManaged = isFreightFeeManaged;
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Integer isHidden) {
        this.isHidden = isHidden;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDepartCity() {
        return departCity;
    }

    public void setDepartCity(String departCity) {
        this.departCity = departCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }
}
