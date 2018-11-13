package com.topjet.common.constants;

public class GoodsConstants {

    /** -------货源状态-------------------------------------- */
    /**
     * 待报价
     */
    public static final Integer GOODS_STATUS_OFFER = 1;

    /**
     * 待成交
     */
    public static final Integer GOODS_STATUS_DEAL = 2;

    /**
     * 待支付
     */
    public static final Integer GOODS_STATUS_WAITING_PAY = 3;

    /**
     * 待承运
     */
    public static final Integer GOODS_STATUS_WAITING_TRANSPORT = 4;

    /**
     * 承运中
     */
    public static final Integer GOODS_STATUS_TRANSPORTING = 5;

    /**
     * 已承运
     */
    public static final Integer GOODS_STATUS_TRANSPORTED = 6;

    /**
     * 货主已撤销
     */
    public static final Integer GOODS_STATUS_CANCELED = 7;

    /**
     * 系统撤销
     */
    public static final Integer GOODS_STATUS_SYS_CANCELED = 8;

    /**
     * 订单撤销/或订单退款
     */
    public static final Integer GOODS_STATUS_ORDER_CANCELED = 9;

    /** -------货源来源-------------------------------------- */
    /**
     * 自发
     */
    public static final String GOODS_SOURCE_SELF = "1";

    /**
     * 转发
     */
    public static final String GOODS_SOURCE_FORWARD = "2";

    /**
     * 客服代发
     */
    public static final String GOODS_SOURCE_CS = "3";

    /** -------货源装卸类型-------------------------------------- */
    /**
     * 今定明装
     */
    public static final String LOAD_TYPE_NEXTDAY = "1";

    /**
     * 马上装车
     */
    public static final String LOAD_TYPE_NOW = "2";

    /**
     * 随车装
     */
    public static final String LOAD_TYPE_WITH_TRUCK = "3";

    /** -------运费付款方式-------------------------------------- */
    /**
     * 预付一半
     */
    public static final String GOODS_FEE_PAY_STYLE_HALF = "1";

    /**
     * 回单结运费
     */
    public static final String GOODS_FEE_PAY_STYLE_BILLBACK = "2";

    /**
     * 货到付款
     */
    public static final String GOODS_FEE_PAY_STYLE_ARRIVAL = "3";

    /**
     * 预付全款
     */
    public static final String GOODS_FEE_PAY_STYLE_PREALL = "4";

    /** -------报价状态-------------------------------------- */
    /**
     * 已报价
     */
    public static final Integer PREORDER_QUOTE = 1;

    /**
     * 已成交
     */
    public static final Integer PREORDER_DEAL = 2;

    /**
     * 货主撤销货源
     */
    public static final Integer PREORDER_OWNER_CANCELED = 3;

    /**
     * 司机撤销报价
     */
    public static final Integer PREORDER_DRIVER_CANCELED = 4;

    /**
     * 货主与他人成交
     */
    public static final Integer PREORDER_DEAL_OTHERS = 5;
    
    /**
     * 系统撤销货源
     */
    public static final Integer PREORDER_SYS_CANCELE_GOODS = 6;
    
    /**
     * 系统撤销订单
     */
    public static final Integer PREORDER_SYS_CANCELE_ORDER = 7;
    /**
     * 订单退款
     */
    public static final Integer PREORDER_ORDER_REFUND = 8;

    
    
    
    

    /** -------订单信息统计操作类型-------------------------------------- */
    /**
     * 订单完成
     */
    public static final String ORDER_INFO_COUNT_OPERATION_TYPE_FINISH = "1";


    /** -------货源发布时是否托管运费-------------------------------------- */
    /**
     * 托管运费
     */
    public static final String GOODS_TRANSPORT_FEE_MANAGED_TRUE = "1";

    /**
     * 不托管运费
     */
    public static final String GOODS_TRANSPORT_FEE_MANAGED_FALSE = "0";

    /**
     * 重新发布货源加载货源明细标志
     */
    public static final String LOAD_GOODSINFO_TYPE_FOR_RE_ISSUE = "1";

    /**
     * 重新发布货源加载货源明细标志
     */
    public static final String LOAD_GOODSINFO_TYPE_FOR_DETAIL = "0";

    /**
     * -------货物种类--------------------------------------
     */
    public static final String GSTYPE_ARGOPRO = "1";// 农副产品

    public static final String GSTYPE_FRUIT = "10";// 水果

    public static final String GSTYPE_FURNITURE = "11";// 家具

    public static final String GSTYPE_GENERAL = "12";// 普货

    public static final String GSTYPE_HEAVY = "13";// 重货

    public static final String GSTYPE_MATERIA = "14";// 建材

    public static final String GSTYPE_MEDICAL = "15";// 医药

    public static final String GSTYPE_MINIRAL = "16";// 矿石

    public static final String GSTYPE_OTH = "17";// 其他

    public static final String GSTYPE_SOS = "18";// 危险品

    public static final String GSTYPE_THEAMAL = "19";// 保温材料

    public static final String GSTYPE_BULKY = "2";// 泡货

    public static final String GSTYPE_VEGETABLE = "20";// 蔬菜

    public static final String GSTYPE_CARTON = "3";// 纸箱

    public static final String GSTYPE_CHEM = "4";// 化工

    public static final String GSTYPE_COAL = "5";// 煤

    public static final String GSTYPE_DEVICE = "6";// 设备

    public static final String GSTYPE_DRIFT = "7";// 漂货

    public static final String GSTYPE_FOOD = "8";// 食品

    public static final String GSTYPE_FROZEN = "9";// 冻品

    /**
     * ---------车辆长度-----------
     */

    public static final String TKLEN_OTH = "-1"; // 其他

    public static final String TKLEN_125 = "12.5";// 12.5米

    public static final String TKLEN_130 = "13"; // 13米

    public static final String TKLEN_135 = "13.5";// 13.5米

    public static final String TKLEN_175 = "17.5";// 17.5米

    public static final String TKLEN_40 = "4";// 4米"

    public static final String TKLEN_42 = "4.2";// 4.2米

    public static final String TKLEN_43 = "4.3";// 4.3米

    public static final String TKLEN_45 = "4.5";// 4.5米

    public static final String TKLEN_48 = "4.8";// 4.8米

    public static final String TKLEN_50 = "5";// 5米";

    public static final String TKLEN_55 = "5.5";// 5.5米

    public static final String TKLEN_58 = "5.8";// 5.8米

    public static final String TKLEN_60 = "6";// 6米

    public static final String TKLEN_62 = "6.2";// 6.2米

    public static final String TKLEN_68 = "6.8";// 6.8米

    public static final String TKLEN_70 = "7";// 7米

    public static final String TKLEN_72 = "7.2";// 7.2米

    public static final String TKLEN_74 = "7.4";// 7.4米

    public static final String TKLEN_78 = "7.8";// 7.8米

    public static final String TKLEN_80 = "8";// 8米

    public static final String TKLEN_87 = "8.7";// 8.7米

    public static final String TKLEN_88 = "8.8";// 8.8米

    public static final String TKLEN_90 = "9";// 9米

    public static final String TKLEN_96 = "9.6";// 9.6米

    /**
     * 车辆类型
     */
    public static final String TRUCK_TYPE_CABRIO = "1"; // 敞篷车

    public static final String TRUCK_TYPE_REFRIGER = "10"; // 冷藏车

    public static final String TRUCK_TYPE_SEMI = "11"; // 半挂车

    public static final String TRUCK_TYPE_XC = "12"; // 厢车

    public static final String TRUCK_TYPE_FYXC = "13"; // 飞翼厢车

    public static final String TRUCK_TYPE_COMMON = "2"; // 普通车

    public static final String TRUCK_TYPE_CONTAINER = "3"; // 集装箱

    public static final String TRUCK_TYPE_F4L6 = "4"; // 前四后六

    public static final String TRUCK_TYPE_F4L4 = "5"; // 前四后四

    public static final String TRUCK_TYPE_F4L8 = "6"; // 前四后八

    public static final String TRUCK_TYPE_FLAT = "7"; // 平板车

    public static final String TRUCK_TYPE_HIGHHURDLE = "8"; // 高栏车

    public static final String TRUCK_TYPE_INSULATED = "9"; // 保温车

    /**
     * 运单类型
     */
    public static final String GOODS_SEARIAL_TYPE_C = "C";// 固定运单

    public static final String GOODS_SEARIAL_TYPE_T = "T";// 普通运单
    /** -------信息费/运费状态-------------------------------------- */

    /**
     * 未托管
     */
    public static final String FEE_STATUS_UN_MANAGERED = "1";

    /**
     * 已托管
     */
    public static final String FEE_STATUS_MANAGERED = "2";

    /**
     * 退款中
     */
    public static final String FEE_STATUS_REFUNDING = "3";

    /**
     * 已退款
     */
    public static final String FEE_STATUS_REFUNDED = "4";

    /**
     * 已支付
     */
    public static final String FEE_STATUS_PAID = "5";

    /** -------货源是否自动成交-------------------------------------- */
    /**
     * 否
     */
    public static final Integer GOODS_AUTO_DEAL_FALSE = 0;

    /**
     * 是
     */
    public static final Integer GOODS_AUTO_DEAL_TRUE = 1;

    /** -------账单来源-------------------------------------- */
    /**
     * 订单表
     */
    public static final Integer BILL_SOURCE_TYPE_ORDER_INFO = 1;

    /**
     * 订单补贴表
     */
    public static final Integer BILL_SOURCE_TYPE_ORDER_BONUS_INFO = 2;

    /**
     * 推荐补贴表
     */
    public static final Integer BILL_SOURCE_TYPE_RECOMMENDATION_BONUS_INFO = 3;

    /**
     * 其他补贴表
     */
    public static final Integer BILL_SOURCE_TYPE_OTHER_BONUS_INFO = 4;

    /**
     * 身份证查询记录表
     */
    public static final Integer BILL_SOURCE_TYPE_ID_CHECK_HISTORY = 5;

    /** -------账单状态-------------------------------------- */
    /**
     * 已退款
     */
    public static final Integer BILL_STATUS_REFUND = 1;

    /**
     * 已支付
     */
    public static final Integer BILL_STATUS_PAID = 2;

    /**
     * 已托管
     */
    public static final Integer BILL_STATUS_GUARANTEED = 3;

    /**
     * 未托管
     */
    public static final Integer BILL_STATUS_NOT_GUARANTEED = 4;

    /**
     * 未支付
     */
    public static final Integer BILL_STATUS_NOT_PAID = 5;

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
     * 退款发起人司机-客户端使用
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
     * 运费
     */
    public static final Integer BILL_TYPE_TRANSPORT_FEE = 5;
    /**
     * 信息费
     */
    public static final Integer BILL_TYPE_AGENCY_FEE = 6;
    /**
     * 查询身份证费
     */
    public static final Integer BILL_TYPE_IDCHECK_FEE = 7;


    /** -------账单类型(接口中与app约定的)-------------------------------------- */
    /**
     * 信息费
     */
    public static final Integer BILLTYPE_AGENCY = 1;
    /**
     * 运费
     */
    public static final Integer BILLTYPE_TRANSPORT = 2;

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
    public static final Integer ORDER_BONUS_TYPE_AGENCY = 2;
    /**
     * 运费
     */
    public static final Integer ORDER_BONUS_TYPE_TRANSPORT = 1;

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
     * 提单付费
     */
    public static final String FEE_TYPE_AHEAD = "2";
    
    /**
     * 到付费
     */
    public static final String FEE_TYPE_DELIVERY = "3";
    

    /**
     * 运费
     */
    public static final String FEE_TYPE_TRANSPORT = "2";


    /**
     * 未接受	交易协议同意与否标志
     */
    public static final int ORDER_AGREEMENT_ACCEPT_FLAG_NO = 0;

    /**
     * 已接受	交易协议同意与否标志
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


    /**
     * 司机查找货源,返回结果的状态定义
     * <p/>
     * 1.未报价,2.已报价,3.司机撤销报价 4.货主撤销货源 5.货源与自己成交 6.货源与他人成交
     */
    public static final String FINDGOODS_FOR_DRIVER_STATUS_NOT_QUOTE = "1";

    public static final String FINDGOODS_FOR_DRIVER_STATUS_QUOTE = "2";

    public static final String FINDGOODS_FOR_DRIVER_STATUS_DRIVER_CANCEL_PRE = "3";

    public static final String FINDGOODS_FOR_DRIVER_STATUS_OWNER_CANCEL_GOODS = "4";

    public static final String FINDGOODS_FOR_DRIVER_STATUS_DEAL_SELF = "5";

    public static final String FINDGOODS_FOR_DRIVER_STATUS_DEAL_OTHERS = "6";

    /**
     * 司机报价是否存在多笔
     */

    public static final String DRIVER_MULTI_PREORDER_YES = "1";
    public static final String DRIVER_MULTI_PREORDER_NO = "0";
}
