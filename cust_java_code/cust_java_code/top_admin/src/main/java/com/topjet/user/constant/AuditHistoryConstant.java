package com.topjet.user.constant;

public class AuditHistoryConstant {

	/*----------------------------------------------- sourceType 操作类型-------------------------------------------*/
	/*
	 * 用户实名审核
	 */
	public static final Integer AUDIT_USER = 1;
	/**
	 * 用户身份审核
	 */
	public static final Integer AUDIT_IDENTITY_USER = 2;
	/**
	 * 用户头像审核
	 */
	public static final Integer AUDIT_HEAD_USER = 3;
	/**
	 * 三元素审核
	 */
	public static final Integer  AUDIT_CHECK_ID = 4;
	/*
     * 推荐费审核一审
     */
	public static final Integer AUDIT_FIRST_RECOMMENDATION_BONUS= 5;
	/*
     * 推荐费审核二审
     */
	public static final Integer AUDIT_SECOND_RECOMMENDATION_BONUS= 6;
	/*
     * 推荐费审核三审
     */
	public static final Integer AUDIT_THIRD_RECOMMENDATION_BONUS= 7;
	/**
	 * 用户冻结
	 */
	public static final Integer AUDIT_MEMBER_FROZEN = 8;
	/**
	 * 用户解冻
	 */
	public static final Integer AUDIT_MEMBER_REMARK = 9;

	/**
	 * 企业认证审核
	 */
	public static final Integer AUDIT_COMPANY = 11;




	/*===============================车辆审核=================================================*/
	public static final Integer AUDIT_TRUCK = 1;





	/*===========================================================================================*/

	/*
     * 运费中介费审核
     */
	public static final Integer AUDIT_ORDER_BONUS = 4;
	/*
     * 退款审核
     */
	public static final Integer AUDIT_REFUND = 5;
	/*
     * 积分兑换审核
     */
	public static final Integer AUDIT_SOCRE_EXCHANGE = 6;
	/*
     * 投诉审核
     */
	public static final Integer AUDIT_COMPLAINT = 7;

	/*
     * 车辆操作备注
     */
	public static final Integer AUDIT_TRUCK_REMARK = 2;

	/**
	 * 评价操作备注
	 */
	public static final Integer AUDIT_COMMENT_REMARK = 10;

	/**
	 * 订单修改/解冻备注
	 */
	public static final Integer AUDIT_ORDER_REMARK = 1;
	/**
	 * 订单冻结管理
	 */
	public static final Integer AUDIT_ORDER_FROZEN = 2;
	/**
	 * 订单操作备注
	 */
	public static final Integer AUDIT_ORDER_UPDATE = 3;

	/**
	 * 用户头像审核
	 */
	public static final Integer AUDIT_USER_HEAD = 11;

	/**
	 * 560服务站审核
	 */
	public static final Integer AUDIT_STATION_INFO = 13;
	/**
	 * 手机三元素审核  CheckIdcardAndMobile
	 */

	/*----------------------------------------------- statusAfter 操作后的状态-------------------------------------------*/

	/*
     * 实名认证审核通过
     */
	public static final Integer AUDIT_SUCCESS = 1;
	/*
     * 审核失败
     */
	public static final Integer AUDIT_FAILED = 2;
	/**
	 * 用户身份审核通过
	 */
	public static final Integer USER_ID_AUDIT_SUCCESS = 3;
	/**
	 * 用户身份审核失败
	 */
	public static final Integer USER_ID_AUDIT_FAIL = 4;
	/**
	 * 用户头像审核通过
	 */
	public static final Integer USER_ICON_AUDIT_SUCCESS = 5;
	/**
	 * 用户头像审核失败
	 */
	public static final Integer USER_ICON__AUDIT_FAIL = 6;
	/*
	 * 用户冻结
	 */
	public static final Integer AUDIT_FROZEN = 7;
	/*
	 * 用户修改备注
	 */
	public static final Integer AUDIT_MEMBER_MODIFY = 13;
	/*
	 * 待审核
	 */
	public static final Integer RECOMMENDATION_NEED_CHECK=8;
	/*
	 * 假一罚三
	 */
	public static final Integer RECOMMENDATION_PAY_THREE=9;
	/*
	 * 无效推荐
	 */
	public static final Integer RECOMMENDATION_USELES=10;
	/*
	 * 审核通过
	 */
	public static final Integer RECOMMENDATION_PASS=11;
	/*
	 * 待定
	 */
	public static final Integer RECOMMENDATION_NOT_CONFIRM=12;

	/*
	 * 企业认证审核通过
	 */
	public static final Integer COMPANY_AUDIT_SUCCESS = 15;
	/*
     * 企业认证审核失败
     */
	public static final Integer COMPANY_AUDIT_FAILED = 16;











	/*
	 * 订单冻结
	 */
	public static final Integer ORDER_AUDIT_FROZEN = 1;

	/*
	 * 修改资料 //解冻备注
	 */
	public static final Integer AUDIT_MODIFY = 2;

	/*
	 *订单操作备注
	 */
	public static final Integer AUDIT_UPDATE = 3;

	/*
	 * 一审通过
	 */
	public static final Integer AUDIT_FIRST_PASS = 5;
	/*
	 * 一审驳回
	 */
	public static final Integer AUDIT_FIRST_REJECT = 6;
	/*
	 * 一审无效推荐
	 */
	public static final Integer AUDIT_FIRST_INVALID = 7;
	/*
	 * 一审假一罚三
	 */
	public static final Integer AUDIT_FIRST_FINED = 8;
	/*
	 * 二审通过
	 */
	public static final Integer AUDIT_SECOND_PASS = 9;
	/*
	 * 二审驳回
	 */
	public static final Integer AUDIT_SECOND_REJECT = 10;
	/*
	 * 二审无效推荐
	 */
	public static final Integer AUDIT_SECOND_INVALID = 11;
	/*
	 * 二审假一罚三
	 */
	public static final Integer AUDIT_SECOND_FINED = 12;
	/*
	 * 三审驳回
	 */
	public static final Integer AUDIT_THIRD_REJECT = 13;
	/*
	 * 三审通过
	 */
	public static final Integer AUDIT_THIRD_PASS = 14;
	/*
	 * 退款失效
	 */
	public static final Integer AUDIT_REFUND_FAILED = 15;
	/*
	 * 客服关闭退款
	 */
	public static final Integer AUDIT_REFUND_CLOSED = 16;
	/*
	 * 客服同意退款
	 */
	public static final Integer AUDIT_REFUND_AGREE = 17;
	/*
	 * 审核驳回
	 */
	public static final Integer AUDIT_REJECT = 18;
	/*
	 * 无效推荐
	 */
	public static final Integer AUDIT_RECOMMEND_INVALID = 19;
	/*
	 * 假一罚三
	 */
	public static final Integer AUDIT_FINED = 20;
	/*
	 * 发放
	 */
	public static final Integer AUDIT_GRANT = 21;
	/*
	 * 投诉属实
	 */
	public static final Integer AUDIT_COMPLAINT_TRUE = 22;
	/*
	 * 投诉和解
	 */
	public static final Integer AUDIT_COMPLAINT_PEACE = 23;
	/*
	 * 虚假投诉
	 */
	public static final Integer AUDIT_COMPLAINT_FALSE = 24;

	/**
	 * 车辆修改操作备注 update
	 */
	public static final Integer AUDIT_TRUCK_REMARK_UPDATE = 3;

	/**
	 * 评价修改操作备注 update
	 */
	public static final Integer AUDIT_COMMENT_REMARK_UPDATE = 14;

	/**
	 * 订单操作备注 update
	 */
	public static final Integer AUDIT_ORDER_REMARK_UPDATE = 27;

	/**
	 * 560服务站审核通过
	 */
	public static final Integer AUDIT_STATION_SUCCESS = 30;
	/**
	 * 560服务站审核驳回
	 */
	public static final Integer AUDIT_STATION_FAIR = 31;
	/**
	 * 560服务站未审核
	 */
	public static final Integer AUDIT_STATION_NO = 32;



}
