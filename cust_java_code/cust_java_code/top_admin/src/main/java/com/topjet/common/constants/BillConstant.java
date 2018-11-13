package com.topjet.common.constants;

/**
 * Created by zhangn on 6/1/16. 2.0 账单相关常量
 *
 * @version 3.0
 */
public class BillConstant {
	/** -------账单来源-------------------------------------- */
	/**
	 * 推荐补贴表
	 */

	public static final Integer BILL_SOURCE_TYPE_RECOMMENDATION_BONUS_INFO = 1;

	/**
	 * 订单补贴表
	 */
	public static final Integer BILL_SOURCE_TYPE_ORDER_BONUS_INFO = 2;


	/**
	 * 其他补贴表
	 */
	public static final Integer BILL_SOURCE_TYPE_OTHER_BONUS_INFO = 4;

	/**
	 * 身份证查询记录表
	 */
	public static final Integer BILL_SOURCE_TYPE_ID_CHECK_HISTORY = 5;

	/**
	 * 红包补贴
	 */
	public static final Integer BILL_SOURCE_TYPE_REDBAG_INFO = 6;

	/** -------账单状态-------------------------------------- */
	/**
	 * 未支付
	 */
	public static final Integer BILL_STATUS_NOT_PAID = 1;

	/**
	 * 支付中
	 */
	public static final Integer BILL_STATUS_PAYING = 2;

	/**
	 * 已支付
	 */
	public static final Integer BILL_STATUS_GUARANTEED = 3;

	/**
	 * 未托管
	 */
	public static final Integer BILL_STATUS_NOT_GUARANTEED = 4;



	/**
	 * 冻结
	 */
	public static final Integer BILL_STATUS_FREEZE = 6;

	/**
	 * 关闭
	 */
	public static final Integer BILL_STATUS_CLOSED = 7;

	/**
	 * 未托管的运费状态,给客户端使用
	 */
	public static final String UNMANAGED_TRANSPORTFEE_STATUS_YES = "1";

	/**
	 * 未托管的运费状态,给客户端使用
	 */
	public static final String UNMANAGED_TRANSPORTFEE_STATUS_NO = "0";

	/**
	 * 退款发起人司机 -客户端使用
	 */
	public static final String REFUND_TRIGGER_DRIVER = "1";

	/**
	 * 退款发起人货主-客户端使用
	 */

	public static final String REFUND_TRIGGER_OWNER = "2";

	/** -------账单类型-------------------------------------- */

	/**
	 * 推荐补贴
	 */
	public static final Integer BILL_TYPE_RECOMMAND_BONUS = 1;
	/**
	 * 运费补贴
	 */
	public static final Integer BILL_TYPE_TRANSPORT_FEE_BONUS = 2;

	/**
	 * 信息费补贴
	 */
	public static final Integer BILL_TYPE_AGENCY_FEE_BONUS = 3;
	/**
	 * 其他补贴
	 */
	public static final Integer BILL_TYPE_OTHER_BONUS = 4;
	
	/**
	 * 提付费
	 */
	public static final Integer BILL_TYPE_AHEAD_FEE = 5;

	/**
	 * 信息费
	 */
	public static final Integer BILL_TYPE_AGENCY_FEE = 6;
	
	 /**
     * 运费
     */
    public static final Integer BILL_TYPE_TRANSPORT_FEE = 7;

	/**
	 * 货到付款费
	 */
	public static final Integer BILL_TYPE_DELIVERY_FEE = 8;

	/**
	 * 回单付运费
	 */
	public static final Integer BILL_TYPE_BACK_FEE = 9;
	
	/**
	 * 查询身份证费
	 */
	public static final Integer BILL_TYPE_IDCHECK_FEE = 10;
	

	/** -------账单类型(接口中与app约定的)-------------------------------------- */
	/**
	 * 信息费
	 */
	public static final Integer BILLTYPE_AGENCY = 1;
	/**
	 * 运费
	 */
	public static final Integer BILLTYPE_FREIGHT = 2;
	/**
	 * 提付费
	 */
	public static final Integer BILLTYPE_AHEADFEE = 3;
	/**
	 * 到付费
	 */
	public static final Integer BILLTYPE_DELIVERYFEE = 4;

	/** -------账单支付类型------------------------------------- */
	/**
	 * 担保
	 */
	public static final Integer BILLPAY_TYPE_ASSURE = 1;
	/**
	 * 即时
	 */
	public static final Integer BILLPAY_TYPE_TIMELY = 2;

	/** -------订单补贴表类型------------------------------------- */
	/**
	 * 信息费
	 */
	public static final String ORDER_BONUS_TYPE_AGENCY = "2";
	/**
	 * 运费
	 */
	public static final String ORDER_BONUS_TYPE_TRANSPORT = "1";

	/** -------是否可发放补贴------------------------------------- */
	/**
	 * 是
	 */
	public static final Integer BONUS_VALID_TRUE = 1;
	/**
	 * 否
	 */
	public static final Integer BONUS_VALID_FALSE = 0;

	/** -------不能发放补贴的类型------------------------------------- */
	/**
	 * 黑名单
	 */
	public static final String BLOCKED_BONUS_REASON_BLACK = "1";
	/**
	 * 收货地与目的地不符合
	 */
	public static final String BLOCKED_BONUS_REASON_SITE = "2";
	/**
	 * 手机标识符不符
	 */
	public static final String BLOCKED_BONUS_REASON_MOBILE = "3";
	/**
	 * 超时自动收货
	 */
	public static final String BLOCKED_BONUS_REASON_OUTTIME = "4";
	/**
	 * 同类型用户
	 */
	public static final String BLOCKED_BONUS_REASON_USER = "5";
	/**
	 * 次数限制
	 */
	public static final String BLOCKED_BONUS_REASON_COUNT = "6";

	/*--------费用类型给钱包托管中的交易使用---------*/

	/**
	 * 信息费
	 */
	public static final String FEE_TYPE_AGENCY = "1";

	/**
	 * 运费
	 */
	public static final String FEE_TYPE_TRANSPORT = "2";

	/**
	 * 未接受 交易协议同意与否标志
	 */
	public static final int ORDER_AGREEMENT_ACCEPT_FLAG_NO = 0;

	/**
	 * 已接受 交易协议同意与否标志
	 */
	public static final int ORDER_AGREEMENT_ACCEPT_FLAG_YES = 1;

	/** ------屏蔽补贴类型------------------------------------- */
	/**
	 * 信息费补贴
	 */
	public static final String BLOCKED_LIST_TYPE_AGENCY_FEE_BONUS = "2";
	/**
	 * 运费补贴
	 */
	public static final String BLOCKED_LIST_TYPE_TRANSPORT_FEE_BONUS = "3";
	/**
	 * 推荐费补贴
	 */
	public static final String BLOCKED_LIST_TYPE_RECOMMENDATION_BONUS = "4";
	/**
	 * 积分
	 */
	public static final String BLOCKED_LIST_TYPE_SOCRE = "1";

	/** ------退款类型------------------------------------- */
	/**
	 * 系统运费退款
	 */
	public static final String REFUNDTYPE_TRANSPORT_FEE_SYS = "1";
	/**
	 * 用户信息费退款
	 */
	public static final String REFUND_TYPE_AGENCY_FEE = "4";
	/**
	 * 系统信息费退款
	 */
	public static final String REFUND_TYPE_AGENCY_FEE_SYS = "3";
	/**
	 * 用户运费退款
	 */
	public static final String REFUND_TYPE_TRANSPORT_FEE = "2";

	/**
	 * 报价是否自动成交
	 */
	public static final String PREORDER_ATUO_ORDER_YES = "1";

	/***
	 * 报价是否自动成交
	 */
	public static final String PREORDER_ATUO_ORDER_NO = "0";

	/** ------退款单状态------------------------------------- */
	/**
	 *  1.发起方申请退款 
	 */
	public static final Integer REFUND_STATUS_INITIATOR = 1;
	
	/**
	 * 2.发起方取消退款 
	 */
	public static final Integer REFUND_STATUS_INITIATOR_CANCEL = 2;
	
	/**
	 * 3.接收方同意退款 
	 */
	public static final Integer REFUND_STATUS_RECEIVE_AGREED = 3;
	
	/**
	 * 4.接收方拒绝退款 
	 */
	public static final Integer REFUND_STATUS_RECEIVE_REFUSED = 4;
	
	/**
	 *  5.退款失效
	 */
	public static final Integer REFUND_STATUS_FAILURE = 5;
	
	/** ------是否本人发起的退款(与app交互使用)------------------------------------- */
	
	/**
	 * 是否本人发起的退款 0.否 
	 */
	public static final Integer REFUND_MYSELF_NO = 0;
	
	/**
	 * 是否本人发起的退款  1.是 
	 */
	public static final Integer REFUND_MYSELF_YES = 1;
	
	
	
	/** ------是否有退款(与app交互使用)------------------------------------- */
	 /**
     * 是否有退款(YES)
     */
    public static final String REFUNDTYPE_ISREFUND_SYS = "1";
    
    /**
     * 是否有退款(NO)
     */
    public static final String REFUNDTYPE_ISREFUND_NO = "0";
    
	/** ------退款客服状态------------------------------------- */

	/**
	 * 0. 默认客服不介入
	 */
	public static final Integer CUSTOMER_SERVICE_STATUS_DEFAULT = 0;
	/**
	 * 1. 发起方申请客服介入
	 */
	public static final Integer CUSTOMER_SERVICE_STATUS_INITIATE = 1;
	
	/**
	 *  2 接收方申请客服介入
	 */
	public static final Integer CUSTOMER_SERVICE_STATUS_RECEIVE = 2;
	
	/**
	 * 3 客服同意退款
	 */
	public static final Integer CUSTOMER_SERVICE_STATUS_AGREED = 3;
	
	/**
	 * 4 客服关闭退款
	 */
	public static final Integer CUSTOMER_SERVICE_STATUS_REFUSED = 4;
	

}
