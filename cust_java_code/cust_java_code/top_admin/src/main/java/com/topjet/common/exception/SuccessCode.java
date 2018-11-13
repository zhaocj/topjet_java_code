package com.topjet.common.exception;

/**
 * Created by pengtao on 1/7/16.
 */
public enum SuccessCode {


    /**
     * 处理成功
     */
    S_0("0", "处理成功"),

    /**
     * 重置密码成功！
     */
    S_USER_1("S_USER_1", "重置密码成功");


    private String code;

    private String msg;

    public String getCode() {
        return this.code;
    }

    public String getMsg() {

        return this.msg;
    }

    SuccessCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
