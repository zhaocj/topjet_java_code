package com.topjet.cloud.user.constant;

/**
 * Created by hongtaoer-win on 2017/8/14.
 */
public enum WebUserConstant {

    WEB_MSG_1(1,"操作成功"),

    WEB_MSG_2(2,"操作失败"),

    WEB_MSG_3(3,"请求参数有误"),

    WEB_MSG_4(4,"请求参数为空"),

    WEB_MSG_5(5,"当前用户状态无法进行认证操作"),

    WEB_MSG_6(6,"当前用户已被冻结"),

    WEB_MSG_7(7,"当前用户已未冻结");






    private Integer code;
    private String msg;

    WebUserConstant(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
