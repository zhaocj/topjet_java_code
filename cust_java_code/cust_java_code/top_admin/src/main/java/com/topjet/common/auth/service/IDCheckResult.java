package com.topjet.common.auth.service;

public enum IDCheckResult {
    //1.无身份证信息 2.不一致 3.一致

    /**
     * 匹配
     */
    AUTH_ID_SUCC("3", "姓名与身份证号一致!"),

    /**
     * 不匹配
     */
    AUTH_ID_FAIL("2", "姓名与身份证号不一致!"),

    /**
     * 库中无此号
     */
    AUTH_ID_NA("1", "库中无此号码"),

    /**
     * 匹配，但无照片
     */
    CHECKID_ERROR("4","请求错误"),
    /**
     * 开关关闭
     */
    CHECKID_idCheckWSSwitch("6","开关关闭"),

    /**
     * 该身份证已绑定用户!
     */
    AUTH_USER_EXISTING("5", "此身份证号已存在，请驳回!");

    private String value;

    private String path;

    //身份认证请求路径本地
    public static final String PATH_LOCAL="1";

    //身份认证请求路径-第三方
    public static final String PATH_WS="2";

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    private String code;

    IDCheckResult(String code, String value) {
	this.code = code;
	this.value = value;
    }

}
