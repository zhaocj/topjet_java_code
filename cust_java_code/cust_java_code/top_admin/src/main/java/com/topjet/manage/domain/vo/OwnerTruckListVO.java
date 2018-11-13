package com.topjet.manage.domain.vo;

/**
 * Created by zhaocj on 2017/09/12.
 * 熟车管理列表查询结果对象
 */
public class OwnerTruckListVO {

    private Integer id;
    private String ownerId;//车主Id
    private String ownerName;//添加人姓名
    private String ownerMobile;//添加人手机
    private String driverId;//司机ID
    private String driverName;//车主姓名
    private String driverMobile;//车主手机
    private String createTime;//添加车辆时间
    private String plateNo;//车牌号
    private String typeId;//车型车长id
    private String typeAndLength;
    private String dealCount;//交易次数
    private String truckAge;//年限
    private String truckColor;//颜色
    private String version;
    private String reditRemark;

    public String getReditRemark() {
        return reditRemark;
    }

    public void setReditRemark(String reditRemark) {
        this.reditRemark = reditRemark;
    }

    public String getTypeAndLength() {
        return typeAndLength;
    }

    public void setTypeAndLength(String typeAndLength) {
        this.typeAndLength = typeAndLength;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
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


    public String getDealCount() {
        return dealCount;
    }

    public void setDealCount(String dealCount) {
        this.dealCount = dealCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerMobile() {
        return ownerMobile;
    }

    public void setOwnerMobile(String ownerMobile) {
        this.ownerMobile = ownerMobile;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }


    public String getTruckAge() {
        return truckAge;
    }

    public void setTruckAge(String truckAge) {
        this.truckAge = truckAge;
    }

    public String getTruckColor() {
        return truckColor;
    }

    public void setTruckColor(String truckColor) {
        this.truckColor = truckColor;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

}
