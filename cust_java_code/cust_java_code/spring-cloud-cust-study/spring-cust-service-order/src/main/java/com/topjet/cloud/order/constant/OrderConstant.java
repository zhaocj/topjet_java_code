package com.topjet.cloud.order.constant;

/**
 * Created by zhangn on 2017/8/24.
 * 货源常量
 */
public class OrderConstant {


    /**===== 订单是否定向 =========================================****/
    /**  1: 是定向订单 */
    public static final Integer ORDER_IS_ASSIGNED_YES = 1;
    /**  0: 不是定向订单 */
    public static final Integer ORDER_IS_ASSIGNED_NO = 0;




    /**===== 订单账单类型 =========================================****/
    /**  1: 定金 */
    public static final Integer ORDER_BILL_TYPE_AGENCY = 1;
    /**  2: 运费 */
    public static final Integer ORDER_BILL_TYPE_FREIGHT = 2;
    /**  3: 提付费 */
    public static final Integer ORDER_BILL_TYPE_AHEAD = 3;
    /**  4: 到付费 */
    public static final Integer ORDER_BILL_TYPE_DELIVERY = 4;
    /**  5: 定金临时状态 */
    public static final Integer ORDER_BILL_TYPE_TEMP_AGENCY = 5;


    /**===== 账单状态 =========================================****/
    /**  1: 未托管 */
    public static final Integer ORDER_BILL_STATUS_UNMANAGED = 1;
    /**  2: 托管中 */
    public static final Integer ORDER_BILL_STATUS_MANAGEDING = 2;
    /**  3: 已托管 */
    public static final Integer ORDER_BILL_STATUS_MANAGED = 3;
    /**  4: 支付中 */
    public static final Integer ORDER_BILL_STATUS_UNPAY = 4;
    /**  5: 已支付 */
    public static final Integer ORDER_BILL_STATUS_PAY = 5;
    /**  6: 关闭 */
    public static final Integer ORDER_BILL_STATUS_CLOSE = 6;


    /**===== 账单支付类型 =========================================****/
    /**  1: 托管 */
    public static final Integer ORDER_BILL_PAYTYPE_MANAGED = 1;
    /**  2: 即时 */
    public static final Integer ORDER_BILL_PAYTYPE_IMMEDIATE = 2;


    /*================订单冻结状态  0：未冻结  1：已冻结============================================*/

    /**
     * 订单冻结状态  1：已冻结
     */
    public static final Integer ORDER_FROST_YES = 1;

    /**
     * 订单冻结状态  0：未冻结
     */
    public static final Integer ORDER_FROST_NO = 0;


}
