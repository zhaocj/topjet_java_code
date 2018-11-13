package com.topjet.manage.domain.vo;

/**
 * Created by tsj006 on 2016/9/1.
 */
public class OrderDetailAdminVO {
    private Integer id;
    private Integer version;
    private Integer driverId;
    private String truckInfo;//运输车辆信息
    private String isAheadFeeManaged;//提付费是否托管
    private String aheadFeeStatus;//提付费状态 1 未托管 2 已托管 3 已支付 4 退款中 5 已退款'
    private String aheadFeeTime;//提付费成交时间
    private String isDeliveryFeeManaged;//到付费是否托管 1 是 0 否
    private String deliveryFeeStatus;//到付状态 1 未托管 2 已托管 3 已支付 4 退款中 5 已退款
    private String deliveryFeeTime;//到付成交时间
    private String agencyStatus;//信息费状态 1 未托管 2 已托管 3 已支付 4 退款中 5 已退款',
    private String agencyFeeTime;//信息费成交时间
    private String orderNo;
    private String createDate;
    private String username;
    private String mobile;
    private String orderStatus;
    private String amtSum;
    private String amtSumTime;//运费总成交时间
    private String aheadFee;//提单付
    private String deliveryFee;//到付
    private String backFee;//回单付
    private String agencyFee;//信息费
    private String driverAmount;//承运补贴
    private String ownerAmount;//发货补贴
    private String category;//货物名称
    private String loadDateType;//提货时间类型
    private String loadDate;//提货时间
    private String packType;//包装方式
    private String loadType;//装卸方式
    private Integer quantityType;//数量类型
    private String quantityMax;//数量最大值
    private String quantityMin;//数量最小值
    private String unit;//单位
    private String truckType;//车辆需求 金杯/2.2米/可拼车
    private String textRemark;//文本备注
    private String audioRemarkUrl;//语音备注
    private String photoRemark;//图片备注
    private String sendName;
    private String sendMobile;
    private String sendAddress;
    private String sendPickupCode;//提货码
    private String receiveName;
    private String receiveMobile;
    private String receiveAddress;
    private String receiveUnloadCode;//卸货码
    private String driverName;//车主姓名
    private String driverMobile;//车主手机
    private String driverDetail;//当前位置
    private String payStyle;//运费支付方式
    private String freightFeeStatus;//运费状态


    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }
    public String getFreightFeeStatus() {
        return freightFeeStatus;
    }

    public void setFreightFeeStatus(String freightFeeStatus) {
        this.freightFeeStatus = freightFeeStatus;
    }

    private String signDate;//签收时间

    public String getPayStyle() {
        return payStyle;
    }

    public void setPayStyle(String payStyle) {
        this.payStyle = payStyle;
    }

    public Integer getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(Integer quantityType) {
        this.quantityType = quantityType;
    }

    public String getQuantityMax() {
        return quantityMax;
    }

    public void setQuantityMax(String quantityMax) {
        this.quantityMax = quantityMax;
    }

    public String getQuantityMin() {
        return quantityMin;
    }

    public void setQuantityMin(String quantityMin) {
        this.quantityMin = quantityMin;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAheadFeeTime() {
        return aheadFeeTime;
    }

    public void setAheadFeeTime(String aheadFeeTime) {
        this.aheadFeeTime = aheadFeeTime;
    }

    public String getDeliveryFeeTime() {
        return deliveryFeeTime;
    }

    public void setDeliveryFeeTime(String deliveryFeeTime) {
        this.deliveryFeeTime = deliveryFeeTime;
    }

    public String getAgencyFeeTime() {
        return agencyFeeTime;
    }

    public void setAgencyFeeTime(String agencyFeeTime) {
        this.agencyFeeTime = agencyFeeTime;
    }

    public String getAmtSumTime() {
        return amtSumTime;
    }

    public void setAmtSumTime(String amtSumTime) {
        this.amtSumTime = amtSumTime;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getPackType() {
        return packType;
    }

    public void setPackType(String packType) {
        this.packType = packType;
    }

    public String getLoadDateType() {
        return loadDateType;
    }

    public void setLoadDateType(String loadDateType) {
        this.loadDateType = loadDateType;
    }

    public String getIsAheadFeeManaged() {
        return isAheadFeeManaged;
    }

    public void setIsAheadFeeManaged(String isAheadFeeManaged) {
        this.isAheadFeeManaged = isAheadFeeManaged;
    }

    public String getAheadFeeStatus() {
        return aheadFeeStatus;
    }

    public void setAheadFeeStatus(String aheadFeeStatus) {
        this.aheadFeeStatus = aheadFeeStatus;
    }

    public String getIsDeliveryFeeManaged() {
        return isDeliveryFeeManaged;
    }

    public void setIsDeliveryFeeManaged(String isDeliveryFeeManaged) {
        this.isDeliveryFeeManaged = isDeliveryFeeManaged;
    }

    public String getDeliveryFeeStatus() {
        return deliveryFeeStatus;
    }

    public void setDeliveryFeeStatus(String deliveryFeeStatus) {
        this.deliveryFeeStatus = deliveryFeeStatus;
    }

    public String getAgencyStatus() {
        return agencyStatus;
    }

    public void setAgencyStatus(String agencyStatus) {
        this.agencyStatus = agencyStatus;
    }

    public String getTruckInfo() {
        return truckInfo;
    }

    public void setTruckInfo(String truckInfo) {
        this.truckInfo = truckInfo;
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

    public String getAmtSum() {
        return amtSum;
    }

    public void setAmtSum(String amtSum) {
        this.amtSum = amtSum;
    }

    public String getAheadFee() {
        return aheadFee;
    }

    public void setAheadFee(String aheadFee) {
        this.aheadFee = aheadFee;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getBackFee() {
        return backFee;
    }

    public void setBackFee(String backFee) {
        this.backFee = backFee;
    }

    public String getAgencyFee() {
        return agencyFee;
    }

    public void setAgencyFee(String agencyFee) {
        this.agencyFee = agencyFee;
    }

    public String getDriverAmount() {
        return driverAmount;
    }

    public void setDriverAmount(String driverAmount) {
        this.driverAmount = driverAmount;
    }

    public String getOwnerAmount() {
        return ownerAmount;
    }

    public void setOwnerAmount(String ownerAmount) {
        this.ownerAmount = ownerAmount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLoadDate() {
        return loadDate;
    }

    public void setLoadDate(String loadDate) {
        this.loadDate = loadDate;
    }

    public String getLoadType() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType;
    }

    public String getTruckType() {
        return truckType;
    }

    public void setTruckType(String truckType) {
        this.truckType = truckType;
    }

    public String getTextRemark() {
        return textRemark;
    }

    public void setTextRemark(String textRemark) {
        this.textRemark = textRemark;
    }

    public String getAudioRemarkUrl() {
        return audioRemarkUrl;
    }

    public void setAudioRemarkUrl(String audioRemarkUrl) {
        this.audioRemarkUrl = audioRemarkUrl;
    }

    public String getPhotoRemark() {
        return photoRemark;
    }

    public void setPhotoRemark(String photoRemark) {
        this.photoRemark = photoRemark;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getSendMobile() {
        return sendMobile;
    }

    public void setSendMobile(String sendMobile) {
        this.sendMobile = sendMobile;
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public String getSendPickupCode() {
        return sendPickupCode;
    }

    public void setSendPickupCode(String sendPickupCode) {
        this.sendPickupCode = sendPickupCode;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceiveMobile() {
        return receiveMobile;
    }

    public void setReceiveMobile(String receiveMobile) {
        this.receiveMobile = receiveMobile;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getReceiveUnloadCode() {
        return receiveUnloadCode;
    }

    public void setReceiveUnloadCode(String receiveUnloadCode) {
        this.receiveUnloadCode = receiveUnloadCode;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverMobile() {
        return driverMobile;
    }

    public void setDriverMobile(String driverMobile) {
        this.driverMobile = driverMobile;
    }

    public String getDriverDetail() {
        return driverDetail;
    }

    public void setDriverDetail(String driverDetail) {
        this.driverDetail = driverDetail;
    }
}
