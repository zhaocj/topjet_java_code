package com.topjet.cloud.order.constant;

/**
 * Created by zhangn on 2017/8/24.
 * 货源常量
 */
public class GoodsConstant {


    /**===== 货物数量类型 =========================================****/
    /** 货物数量类型 : 1: 固定 */
    public static final Integer GOODS_QUANTITY_TYPE_FIXED = 1;
    /** 货物数量类型 : 2: 范围 */
    public static final Integer GOODS_QUANTITY_TYPE_LIMIT = 2;


    /*========================  车型车长类型   ============================================*/
    /**  车型：1  */
    public static  final Integer GOODSTRUCKINFO_TYPE_CARTYPE = 1;
    /**  车长:2  */
    public static  final Integer GOODSTRUCKINFO_TYPE_CARTLENGTH = 2;


    /**===== 是否可拼车 0 : 不可拼车/整车    1: 可拼车=========================================****/
    /** 是否可拼车 : 1: 可拼车 */
    public static final Integer GOODS_IS_CARPOOL_YES = 1;
    /** 是否可拼车 : 0: 不可拼车 */
    public static final Integer GOODS_IS_CARPOOL_NO = 0;


    /** ======城内/城际 标示================================================================================= */
    /**
     * 是否是城内订单-是  (城内)
     */
    public static final Integer IS_INNER_YES = 1;

    /**
     * 是否是城内订单-否 (城际)
     */
    public static final Integer IS_INNER_NO = 0;


    /** ====== 区分运费是否托管 ================================================================================= */

    /**
     * 区分运费是否托管 :  1.运费托管
     */
    public static final Integer FREIGHTFEE_MANAGED_YES = 1;
    /**
     * 区分运费是否托管 :  0.运费不托管
     */
    public static final Integer FREIGHTFEE_MANAGED_NO = 0;


    /** ====== 订单请求来源 =================================================================================*/
    /**
     * app
     */
    public static final Integer SOURCE_TYPE_APP = 1;
    /**
     * web
     */
    public static final Integer SOURCE_TYPE_WEB = 2;


    /** ====== 订单隐藏状态 =================================================================================*/
    /**
     * 0 正常状态
     */
    public static final Integer ORDER_ISHIDDEN_NO = 0;
    /**
     * 1 隐藏状态
     */
    public static final Integer ORDER_ISHIDDEN_YES = 1;



    /** ======提货时间类型====================================================================================================*/

    /**
     * 提货时间类型 0：具体时间 yyyy-mm-dd HH
     */
    public static final Integer ORDER_LOADDATETYPE_ZERO=0;
    /**
     * 提货时间类型 1：今定今装 （过期时间今天的24点）
     */
    public static final Integer ORDER_LOADDATETYPE_TODAY=1;
    /**
     * 提货时间类型 2：今定明装 （过期时间明天的24点）
     */
    public static final Integer ORDER_LOADDATETYPE_TOMORROW=2;
    /**
     * 提货时间类型 3：随到随走 （过期时间7天后的24点）
     */
    public static final Integer ORDER_LOADDATETYPE_ANYTIME=3;


    /*===========================    运费支付方式     ============================================================================*/

    /**
     *  运费支付方式：1: 货到付款
     */

    public static final Integer GOODS_PAYSTYLE_CASHONDELIVERY =1;
    /**
     * 运费支付方式：2:提付全款
     */
    public static final Integer GOODS_PAYSTYLE_TOPAYFULL =2;
    /**
     * 运费支付方式：3:提付部分运费
     */
    public static final Integer GOODS_PAYSTYLE_PAYPARTOFTHEFREIGHT =3;
    /**
     * 运费支付方式：4:回单付运费
     */
    public static final Integer GOODS_PAYSTYLE_THERECEIPTTOPEYTHEFEIGHT =4;


    /*===========================    订单费用状态     ============================================================================*/

    /**
     * 运费状态：0.不托管
     */
    public static final Integer ORDER_COSTSTATUS_MANAGED_NO = 0;
    /**
     * 运费状态：1.未托管
     */
    public static final Integer ORDER_COSTSTATUS_MANAGED_YES = 1;
    /**
     * 运费状态：2.未支付
     */
    public static final Integer ORDER_COSTSTATUS_PAYSTATUS_NO = 2;
    /**
     * 运费状态： 3.已支付
     */
    public static final Integer ORDER_COSTSTATUS_PAYSTATUS_YES = 3;

    /*===========================    订单状态     ============================================================================*/

    /**
     * 订单状态：1.货主取消交易
     */
    public static final Integer ORDERSTATUS_OWNER_TRADING_CANCEL = 1;
    /**
     * 订单状态：2.货主确认成交
     */
    public static final Integer ORDERSTATUS_OWNER_TRADING_CONFIRM  = 2;
    /**
     * 订单状态：3.待支付定金
     */
    public static final Integer ORDERSTATUS_TO_PAY_A_DEPOSIT = 3;
    /**
     * 订单状态：4.待支付运费
     */
    public static final Integer ORDERSTATUS_TO_PAY_A_FREIGHT = 4;
    /**
     * 订单状态：5.提货中
     */
    public static final Integer ORDERSTATUS_PICKUP_THEGOODS = 5;
    /**
     * 订单状态：6.承运中
     */
    public static final Integer ORDERSTATUS_TRANSPORTING = 6;
    /**
     * 订单状态：7.已承运
     */
    public static final Integer ORDERSTATUS_IS_THE_CARRIER = 7;


    /*===========================    报价单状态（preGoodsInfo）     ============================================================================*/
    /**
     * 报价单状态：1:待货主成交 2: 货主已确认成交  3:司机撤销报价 4:订单已修改
     */
    public static final Integer OWNER_MAKE_A_BARGAIN_OF_WAIT = 1;
    /**
     * 报价单状态：2: 货主已确认成交
     */
    public static final Integer OWNER_MAKE_A_BARGAIN_OF_SUCCESS = 2;
    /**
     * 报价单状态：3:司机撤销报价
     */
    public static final Integer DRIVER_CANCEL_THE_BID = 3;
    /**
     * 报价单状态：4:订单已修改
     */
    public static final Integer ORDER_IS_UPDATE = 4;

     /*===========================    货源状态（goodsInfo）     ============================================================================*/
    /**
     * 货源状态：1:新建货源 2:生成订单  3: 货主撤销  4: 司机放弃订单 5: 货源过期
     */
    public static final Integer GOODS_STATUS_NEW = 1;
    /**
     * 报价单状态：2:生成订单
     */
    public static final Integer GOODS_STATUS_CREATE_ORDER = 2;
    /**
     * 报价单状态： 3: 货主撤销
     */
    public static final Integer GOODS_STATUS_CANCEL = 3;
    /**
     * 报价单状态：4: 司机放弃订单
     */
    public static final Integer GOODS_STATUS_DRIVER_ABANDON = 4;
    /**
     * 报价单状态：5: 货源过期
     */
    public static final Integer GOODS_STATUS_TIMEOUT = 5;
}
