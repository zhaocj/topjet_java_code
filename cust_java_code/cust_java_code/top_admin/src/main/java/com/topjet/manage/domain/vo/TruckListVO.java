package com.topjet.manage.domain.vo;


/**
 * Created by Tym on 2016/8/25.
 */
public class TruckListVO {
    private Integer id;//车辆id
    private String username;//车主姓名
    private String mobile;//手机
    private String createTime;//添加车辆时间
    private String plateNo;//车牌号
    private String typeAndLength;//车型车长
    private Integer orderCount;//运输次数
    private String truckAge;//年限
    private String truckColor;//颜色
    private Integer auditStatus;//认证状态
    private String auditName;//审核人
    private Integer uid;//车主Id
    private Integer driverId;//司机id
    private String plateNo1;
    private String plateNo2;
    private String plateNo3;
    private String reditRemark;

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public String getReditRemark() {
        return reditRemark;
    }

    public void setReditRemark(String reditRemark) {
        this.reditRemark = reditRemark;
    }


    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getPlateNo1() {
        return plateNo1;
    }

    public void setPlateNo1(String plateNo1) {
        this.plateNo1 = plateNo1;
    }

    public String getPlateNo2() {
        return plateNo2;
    }

    public void setPlateNo2(String plateNo2) {
        this.plateNo2 = plateNo2;
    }

    public String getPlateNo3() {
        return plateNo3;
    }

    public void setPlateNo3(String plateNo3) {
        this.plateNo3 = plateNo3;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
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

    public String getTypeAndLength() {
        return typeAndLength;
    }

    public void setTypeAndLength(String typeAndLength) {
        this.typeAndLength = typeAndLength;
    }
}
