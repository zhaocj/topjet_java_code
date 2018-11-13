package com.topjet.manage.domain.dto;

/**
 * Created by liyj on 2017/11/15.
 */
public class Request {

    /**
     * 执行方法体名称
     */
    private String actionname;
    /**
     * 应用程序ID
     */
    private String appid ;

    /**
     * 具体参数的Json字符串
     */
    private String body;

    /**
     * 客户端IP
     */
    private String ip;

    /**
     * App端机器码
     */
    private String machinecode;

    /**
     * 消息类型
     */
    private String messagetype;

    /**
     * 签名
     */
    private Sign sign;

    /**
     * 请求接口时的时间
     */
    private String timestamp;

    /**
     * ApiVersion
     */
    private  String version = "v1";

    public String getActionname() {
        return actionname;
    }

    public void setActionname(String actionname) {
        this.actionname = actionname;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMachinecode() {
        return machinecode;
    }

    public void setMachinecode(String machinecode) {
        this.machinecode = machinecode;
    }

    public String getMessagetype() {
        return messagetype;
    }

    public void setMessagetype(String messagetype) {
        this.messagetype = messagetype;
    }

    public Sign getSign() {
        return sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
