package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.bean.MatchCenterOrderBean;
import com.topjet.manage.domain.bean.MatchCenterUserBean;
import com.topjet.manage.domain.model.GoodsInfoModel;
import com.topjet.manage.domain.model.OrderInfoModel;
import com.topjet.manage.domain.model.OrderParameterInfoModel;
import com.topjet.manage.domain.model.UserGpsInfoHistoryModel;
import com.topjet.manage.domain.vo.OrderDetailAdminVO;
import com.topjet.manage.domain.vo.OrderInfoBean;
import com.topjet.manage.domain.vo.OrderListQuery;
import com.topjet.manage.domain.vo.OrderListVO;
import com.topjet.tool.common.constant.TopJetDBConstant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderInfoModelEMapper extends BaseEMapper<OrderInfoModel> {

    List<MatchCenterUserBean> userQueryList(MatchCenterUserBean bean);

    Integer userQueryCount(MatchCenterUserBean query);

    /**
     *货源管理
     */
    List<MatchCenterOrderBean> orderQuertList(MatchCenterOrderBean matchCenterOrderBean);

    /**
     *货源管理页数
     */
    Integer orderQuertCount(MatchCenterOrderBean matchCenterOrderBean);

    /**
     * 根据货物id，版本号查询数据
     */
    public List<GoodsInfoModel> getGoodsInfo(GoodsInfoModel goodsInfoModel);

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
     *根据订单id查询货源id
     */
    public Integer findGoodIdByOrderId(Integer orderId);
    /**
     * 订单详情
     */
    public OrderDetailAdminVO orderDetail(Integer id);
    /**
     * 货源详情
     */
    public OrderDetailAdminVO goodsDetail(Integer id);

    @Select("SELECT * FROM　orderDB.orderInfo where orderNo = #{orderNo}")
    OrderInfoModel selectOrderByOrderNo(@Param("orderNo") String orderNo);

    /**
     *货源查询
     */
    List<MatchCenterOrderBean> goodQuertList(MatchCenterOrderBean matchCenterOrderBean);

    /**
     *货源查询页数
     */
    Integer goodQuertCount(MatchCenterOrderBean matchCenterOrderBean);

    //获取订单运行轨迹
    public List<UserGpsInfoHistoryModel> getRunninnTrack(OrderInfoBean orderInfoBean );

    //获取订单提货签收轨迹
    @Select("SELECT * from "+ TopJetDBConstant.DB_NAME_ORDER+".orderParameterInfo    where   orderId = #{orderId}   and   deleted=0   and  parameterType in (7,8)")
    public List<OrderParameterInfoModel> getOrderTrack(@Param("orderId") Integer orderId);

    /**
     * 根据id查询
     */
    @Select(" SELECT o.* from orderDB.orderInfo o where o.goodsId = #{orderId}")
    public OrderInfoModel getOrderInfoById(@Param("orderId") Integer orderId);

}