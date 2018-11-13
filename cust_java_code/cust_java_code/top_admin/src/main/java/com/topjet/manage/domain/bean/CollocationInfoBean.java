package com.topjet.manage.domain.bean;


import com.topjet.manage.domain.model.BaseModel;
import com.topjet.manage.domain.model.CollocationInfoModel;

import java.util.Date;

public class CollocationInfoBean extends CollocationInfoModel {

    private String appType;

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }
}