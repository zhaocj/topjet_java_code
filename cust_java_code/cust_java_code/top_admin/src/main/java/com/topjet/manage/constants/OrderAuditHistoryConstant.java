package com.topjet.manage.constants;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-10-17 14:35
 */

public class OrderAuditHistoryConstant {

    /*----------------------------------------------- sourceType 操作类型-------------------------------------------*/
    /**
     * 订单解冻/资料修改
     */
    public static final Integer ORDER_DEFROZEN = 1;
    /**
     * 订单冻结
     */
    public static final Integer ORDER_FROZEN = 2;
    /**
     * 运费补贴一审
     */
    public static final Integer ORDER_BONUS_FIRST_AUDIT = 3;
    /**
     * 运费补贴二审
     */
    public static final Integer ORDER_BONUS_SECOND_AUDIT = 4;
    /**
     * 运费补贴三审
     */
    public static final Integer ORDER_BONUS_THIRD_AUDIT = 5;



    /*----------------------------------------------- statusAfter 操作后状态-------------------------------------------*/

    /**
     *订单已冻结
     */
    public static final Integer ORDER_AUDIT_FROZEN = 1;
    /**
     *订单已解冻
     */
    public static final Integer ORDER_AUDIT_DEFROZEN = 2;
    /**
     *审核待定
     */
    public static final Integer AUDIT_UNDETERMINED = 3;
    /**
     *审核通过
     */
    public static final Integer AUDIT_SUCCESS = 4;
    /**
     *审核驳回
     */
    public static final Integer AUDIT_FAIL = 5;
    /**
     *审核驳回
     */
    public static final Integer AUDIT_OTHER = 6;







}
