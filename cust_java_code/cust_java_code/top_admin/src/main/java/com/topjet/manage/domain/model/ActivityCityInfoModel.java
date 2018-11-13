package com.topjet.manage.domain.model;

public class ActivityCityInfoModel {
    private Integer id;

    private String cityCode;

    private Integer regularActivityId;

    private Integer deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getRegularActivityId() {
        return regularActivityId;
    }

    public void setRegularActivityId(Integer regularActivityId) {
        this.regularActivityId = regularActivityId;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}