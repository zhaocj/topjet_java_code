package com.topjet.manage.domain.dto;

/**
 * Created by tsj010 on 2018/5/21.
 */
public class RequestTms {
    /**
     * 应用程序ID
     */
    private String appId ;

    /**
     * 具体参数的Json字符串
     */
    private String parameter;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
