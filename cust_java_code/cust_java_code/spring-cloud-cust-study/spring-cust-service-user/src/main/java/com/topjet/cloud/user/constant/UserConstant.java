package com.topjet.cloud.user.constant;

/**
 * Created by hongtaoer-win on 2017/8/4.
 *
 */
public class UserConstant {

    /*==============  通用标识  =======================*/

    //操作结果 1.成功 2.失败
    public static final Integer USER_OPERATION_SUCCESS=1;
    public static final Integer USER_OPERATION_FAILURE=2;


    //用户登录方式 1.密码  2.验证码
    public static final Integer USER_LOGIN_TYPE_PASS=1;
    public static final Integer USER_LOGIN_TYPE_CODE=2;

    //用户类型  1.司机   2.货主
    public static final Integer USER_TYPE_DRIVER = 1;
    public static final Integer USER_TYPE_OWNER = 2;


    /**=========== 身份认证状态 ======================*/
    /** 身份认证状态：0默认  未认证 */
    public static final Integer AUTH_STATUS_NOTAUTH=0;

    /** 身份认证状态：1 认证中 */
    public static final Integer AUTH_STATUS_ONGOING=1;

    /** 身份认证状态：2 认证失败 */
    public static final Integer AUTH_STATUS_FAILURE=2;

    /** 身份认证状态：3 认证通过 */
    public static final Integer AUTH_STATUS_SUCCESSFUL=3;


    /**=========== 头像认证状态 ======================*/
    /** 头像认证状态：0默认  未认证 */
    public static final Integer ICON_STATUS_NOTICON=0;

    /** 头像认证状态：1 认证中 */
    public static final Integer ICON_STATUS_ONGOING=1;

    /** 头像认证状态：2 认证失败 */
    public static final Integer ICON_STATUS_FAILURE=2;

    /** 头像认证状态：3 认证通过 */
    public static final Integer ICON_STATUS_SUCCESSFUL=3;




    /**=========== 实名认证状态 ======================*/
    /** 实名认证状态：0默认  证件未提交 */
    public static final Integer USER_STATUS_NOTCOMMIT =0;

    /** 实名认证状态：1 待审核 */
    public static final Integer USER_STATUS_WAITAUCIT=1;

    /** 实名认证状态：2 审核通过 */
    public static final Integer USER_STATUS_APPROVED=2;

    /** 实名认证状态：3 资料修改待审核 */
    public static final Integer USER_STATUS_DATA_WAIT_REVIEWED=3;

    /** 实名认证状态：4 认证失败 */
    public static final Integer USER_STATUS_FAILURE=4;


    /**=========== 黑名单类型 ======================*/
    /** 1: 冻结  */
    public static final Integer USER_BLACK_TYPE_FREEZE = 1;




}
