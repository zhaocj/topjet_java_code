package com.topjet.manage.domain.vo;

import com.topjet.manage.domain.model.BaseModel;

/**
 * Created by pengtao on 2016/10/16.
 * <p>
 * 我的熟车查询条件
 */
public class OwnerTruckListQuery extends BaseModel {

    private String ownerMobile;//货主手机
    private String driverMobile;//车主手机
    private String plateNo1;//车辆号第一部分
    private String plateNo2;//车辆号第二部分
    private String plateNo3;//车辆号 第三部分
    private String type;//车型
    private String length;//车长
    private String reditRemark;

    public String getReditRemark() {
        return reditRemark;
    }

    public void setReditRemark(String reditRemark) {
        this.reditRemark = reditRemark;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getOwnerMobile() {
        return ownerMobile;
    }

    public void setOwnerMobile(String ownerMobile) {
        this.ownerMobile = ownerMobile;
    }

    public String getDriverMobile() {
        return driverMobile;
    }

    public void setDriverMobile(String driverMobile) {
        this.driverMobile = driverMobile;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
