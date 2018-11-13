package com.topjet.manage.domain.bean;

/**
 * Created by liyj on 2018/3/15.
 */
public class CityResourceBean {
    private String adcode;
    private String citycode;
    private String cityName;
    private String cityFullName;
    private String parentId;
    private String longitude;//经度
    private String latitude;//纬度


    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityFullName() {
        return cityFullName;
    }

    public void setCityFullName(String cityFullName) {
        this.cityFullName = cityFullName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
