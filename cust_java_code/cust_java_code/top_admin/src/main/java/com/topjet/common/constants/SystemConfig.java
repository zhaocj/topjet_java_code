package com.topjet.common.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 系统开关配置
 * @author zhaocj
 * @create 2017-08-17 19:41
 **/
@Configuration
public class SystemConfig {

    /**
     * 消息中心相关配置
     */
    @Value("${msgcenterrequest.redirect.url}")
    public String baseServiceUrl;

    @Value("${msgcenterrequest.version}")
    public String version;

    @Value("${msgcenterrequest.key}")
    public String key;

    @Value("${msgcenterrequest.appId}")
    public String appId;

    /*
    手机三元素核查配置
     */
    @Value("${baseservice.actionName}")
    public String actionName;

    @Value("${baseservice.messageType}")
    public String messageType;

    /**
     * 实名认证接口相关配置
     */

    @Value("${CheckAuthInfoService.actionName}")
    public String CheckAuthActionName;

    @Value("${CheckAuthInfoService.messageType}")
    public String CheckAuthMethod;




    @Value("${appslide.url}")
    public String appSlideUrl;

    @Value("${dynamicpassword.switch}")
    public Integer dynamicPassWordSwitch;

    @Value("${dynamicpassword.url}")
    public String dynamicPassWordUrl;

    /**
     * tms地址
     */
    @Value("${tmsRequest.redirect.url}")
    public String tmsRquestUrl;

    @Value("${tmsRequest.tmsAppid}")
    public String tmsAppid;

    public String getTmsAppid() {
        return tmsAppid;
    }

    public void setTmsAppid(String tmsAppid) {
        this.tmsAppid = tmsAppid;
    }

    public String getTmsRquestUrl() {
        return tmsRquestUrl;
    }

    public void setTmsRquestUrl(String tmsRquestUrl) {
        this.tmsRquestUrl = tmsRquestUrl;
    }

    public String getCheckAuthActionName() {
        return CheckAuthActionName;
    }

    public void setCheckAuthActionName(String checkAuthActionName) {
        CheckAuthActionName = checkAuthActionName;
    }

    public String getCheckAuthMethod() {
        return CheckAuthMethod;
    }

    public void setCheckAuthMethod(String checkAuthMethod) {
        CheckAuthMethod = checkAuthMethod;
    }


//实名审核接口WebService接口开关

    public static String idCheckWSSwitch;


    public static String logSwitch;


    public static String log4jSwitch;


    public static String logLevel;


    public static String log4jLevel;

    public String getIdCheckWSSwitch() {
        return idCheckWSSwitch;
    }

    @Value("${systemConfiguration.idCheckWSSwitch}")
    public void setIdCheckWSSwitch(String idCheckWSSwitch) {
        this.idCheckWSSwitch = idCheckWSSwitch;
    }

    public String getLogSwitch() {
        return logSwitch;
    }

    @Value("${systemConfiguration.topjetLogSwitch}")
    public void setLogSwitch(String logSwitch) {
        this.logSwitch = logSwitch;
    }

    public String getLog4jSwitch() {
        return log4jSwitch;
    }

    @Value("${systemConfiguration.topjetLog4jSwitch}")
    public void setLog4jSwitch(String log4jSwitch) {
        this.log4jSwitch = log4jSwitch;
    }

    public String getLogLevel() {
        return logLevel;
    }

    @Value("${systemConfiguration.topjetLogLevel}")
    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getLog4jLevel() {
        return log4jLevel;
    }

    @Value("${systemConfiguration.topjetLog4jLevel}")
    public void setLog4jLevel(String log4jLevel) {
        this.log4jLevel = log4jLevel;
    }

    public String getBaseServiceUrl() {
        return baseServiceUrl;
    }

    public void setBaseServiceUrl(String baseServiceUrl) {
        this.baseServiceUrl = baseServiceUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getAppSlideUrl() {
        return appSlideUrl;
    }

    public void setAppSlideUrl(String appSlideUrl) {
        this.appSlideUrl = appSlideUrl;
    }

    public Integer getDynamicPassWordSwitch() {
        return dynamicPassWordSwitch;
    }

    public void setDynamicPassWordSwitch(Integer dynamicPassWordSwitch) {
        this.dynamicPassWordSwitch = dynamicPassWordSwitch;
    }

    public String getDynamicPassWordUrl() {
        return dynamicPassWordUrl;
    }

    public void setDynamicPassWordUrl(String dynamicPassWordUrl) {
        this.dynamicPassWordUrl = dynamicPassWordUrl;
    }
}
