package com.topjet.manage.constants;

import java.util.concurrent.TimeUnit;

/**
 * Created by pengtao on 8/10/15.
 */
public class CommonConstant {

    public static final int DB_RECORD_DELETED_STATUS_TURE = 1;

    public static final int DB_RECORD_DELETED_STATUS_FALSE = 0;

    /**
     * 日志操作类型1. add 2 update 3.deleted
     */
    public static final int DB_OPERATION_ADD=1;

    public static final int DB_OPERATION_UPDATE=2;

    public static final int DB_OPERATION_DELETE=3;

    public static final String DB_QUERY_ORDER_BY_CREATETIME = "createTime desc";
    
    
    public static final String DB_QUERY_ORDER_BY_UPDATETIME = "updateTime desc";

    public static final String DB_QUERY_ORDER_BY_ID = "id desc";

    public static final String ADMIN_MATCH_CENTER_PHONE = "admin_match_center_phone";

    public static final String ADMIN_MATCH_CENTER_PHONE_ID = "match_center_phone_id";
    /**
     * session过期时间单位
     */
    public static final TimeUnit SESSION_EXPIRED_TIME_UNIT = TimeUnit.MILLISECONDS;

    /**
     * session过期时间 单位 TimeUnit.MILLISECONDS 1000  * 60 * 60 * 24
     */
    public static final long SESSION_EXPIRED_TIME = 1000 * 60 * 60 * 24 * 5;


    /**
     * 安卓司机版
     */
    public static final String SYSTEMTYPE_ANDROID_DRIVER = "1";

    /**
     * 安卓货主版
     */
    public static final String SYSTEMTYPE_ANDROID_OWNER = "2";

    /**
     * IOS司机版
     */
    public static final String SYSTEMTYPE_IOS_DRIVER = "3";

    /**
     * IOS货主版
     */
    public static final String SYSTEMTYPE_IOS_OWNER = "4";


    /** -------黑名单屏蔽类型------------------------------------- */
    /**
     * 信息费补贴
     */
    public static final String BLACK_LIST_TYPE_AGENCY_FEE_BONUS = "1";
    /**
     * 运费补贴
     */
    public static final String BLACK_LIST_TYPE_TRANSPORT_FEE_BONUS = "2";
    /**
     * 推荐费补贴
     */
    public static final String BLACK_LIST_TYPE_RECOMMENDATION_BONUS = "3";
    /**
     * 积分
     */
    public static final String SCORE_MQ_ARTIFICAL = "topjet.score.gain";

    /** -------推送Type------------------------------------- */
    /**
     * 推送给司机
     */
    public static final String BEINGPUSH_DRIVER = "1";
    /**
     * 推送给货主
     */
    public static final String BEINGPUSH_OWNER = "2";


    /** -------验证码短信 发送 Type------------------------------------- */
    /** 注册 */
    public static final Integer SENDTYPE_REGISTER = 1;

    /** 找回密码 */
    public static final Integer SENDTYPE_BACK = 2;

    /** 修改手机号 */
    public static final Integer SENDTYPE_UPDATEMOBILE = 3;

    /** 登陆验证码 */
    public static final Integer SENDTYPE_LOGIN = 4;

    /** 提货验证码 */
    public static final Integer SENDTYPE_PICKUP = 5;

    /** 签收验证码 */
    public static final Integer SENDTYPE_SIGN = 6;



}
