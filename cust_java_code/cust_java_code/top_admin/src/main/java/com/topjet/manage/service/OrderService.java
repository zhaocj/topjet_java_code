package com.topjet.manage.service;

import com.topjet.common.ResultBaseMsg;
import com.topjet.manage.domain.model.*;
import com.topjet.manage.domain.vo.OrderDetailAdminVO;
import com.topjet.manage.domain.vo.OrderInfoBean;
import com.topjet.manage.domain.vo.OrderListQuery;
import com.topjet.manage.domain.vo.OrderListVO;

import java.util.List;

/**
 * Created by liyj on 2017/8/26.
 */
public interface OrderService {

    /**
     * 订单管理list
     */
    public List<OrderListVO> getOrderList(OrderListQuery orderListQuery);

    /**
     * 订单管理列表页数
     */
    public Integer getOrderListCount(OrderListQuery orderListQuery);
    /**
     * 根据订单号查询
     */
    public OrderInfoModel findByOrderNo(String orderNo);
    /**
     * 订单详情
     */
    public OrderDetailAdminVO orderDetail(Integer id);
    /**
     * 货源详情
     */
    public OrderDetailAdminVO goodsDetail(Integer id);
    /**
     *根据订单id查询货源id
     */
    public Integer findGoodIdByOrderId(Integer id);
    /**
     * 根据货源id查询
     */
    public GoodsInfoModel selectByPrimaryKey(Integer id);

    public GoodsDetailInfoModel findGoodDetailById(Integer id);

    /**
     *隐藏订单
     */
    public Object isHidden(Integer id,Integer version,Integer isHidden);
    /**
     * 根据sourceID sourceType获取日志
     */
    public List<OrderAuditHistoryModel> getOperationLog(OrderAuditHistoryModel orderAuditHistoryModel);

    /**
     * 订单冻结
     */
    public ResultBaseMsg addOrUpdateOrder(OrderInfoBean orderInfoBean);

    /**
     *获取司机运行轨迹
     */
    public List<UserGpsInfoHistoryModel> getRunninnTrack(OrderInfoBean orderInfoBean );

    /**
     *获取订单状态轨迹
     */
    public List<OrderParameterInfoModel> getOrderTrack(Integer orderId);
}
