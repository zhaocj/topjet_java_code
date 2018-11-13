package com.topjet.manage.constants;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-10-27 11:04
 */

public class OrderConstant {

    /** -------订单补贴表类型------------------------------------- */

    /**
     * 运费
     */
    public static final Integer ORDER_BONUS_TYPE_TRANSPORT = 1;
    /**
     * 信息费
     */
    public static final Integer ORDER_BONUS_TYPE_AGENCY = 2;

    /*----------------------------------------------- sourceType 操作类型-------------------------------------------*/
    /**
     *订单冻结管理
     */
    public static final Integer AUDIT_ORDER_REMARK = 1;
    /**
     *  订单修改/解冻备注
     */
    public static final Integer AUDIT_ORDER_FROZEN = 2;
    /**
     * 运费补贴一审
     */
    public static final Integer AUDIT_ORDER_BONUS_FIRST = 3;
    /**
     * 运费补贴二审
     */
    public static final Integer AUDIT_ORDER_BONUS_SECOND = 4;
    /**
     * 运费补贴三审
     */
    public static final Integer AUDIT_ORDER_BONUS_THIRD = 5;
    /**
     * 货源隐藏
     */
    public static final Integer AUDIT_GOOD_HIDDEN = 6;


    /*----------------------------------------------- statusAfter 操作后的状态-------------------------------------------*/
    /*
	 * 订单冻结
	 */
    public static final Integer ORDER_AUDIT_FROZEN = 1;

    /*
     * 修改资料 //解冻备注
     */
    public static final Integer AUDIT_MODIFY = 2;
    /**
     * 待定
     */
    public static final Integer AUDIT_ORDER_BONUS_WARIT = 3;
    /**
     * 审核通过
     */
    public static final Integer AUDIT_ORDER_BONUS_SUCCESS = 4;
    /**
     * 审核驳回
     */
    public static final Integer AUDIT_ORDER_BONUS_FAIR = 5;
    /**
     * 货源隐藏
     */
    public static final Integer AUDIT_GOOD_HIDDEN_REMARK = 7;

}
