package com.topjet.common.auth.service.impl;

/**
 * 身份证webservice接口配置
 * 
 * @author pengtao
 *
 */
public class IDCheckWSProperties {

    /**
     * 类型
     */
    protected static final String TYPE = "1A020202";

    /**
     * KEY
     */
    protected static final String KEY = "12345678";

    /**
     * 姓名身份证分隔符
     */
    protected static final String DELIMITER = ",";

    /**
     * 账号
     */
    protected static final String ID_WS_ACCOUNT = "shtjxx";

    /**
     * 测试账号
     */
    protected static final String ID_WS_TEST_ACCOUNT = "shtjxxtest";

    /**
     * 密码
     */
    protected static final String ID_WS_PWD = "vmad8dz2i2";

    /**
     * URL测试地址
     */
//    protected static final String ID_WS_TEST_URL = "http://211.147.7.46/zcxy/services/QueryValidatorServices";

    /**
     * URL
     */
    protected static final String ID_WS_TEST_URL = "http://211.147.7.47/zcxy/services/QueryValidatorServices?wsdl";

}
