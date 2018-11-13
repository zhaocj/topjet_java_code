package com.topjet.manage.service;

import com.topjet.manage.domain.bean.MatchCenterOrderBean;
import com.topjet.manage.domain.bean.MatchCenterUserBean;
import com.topjet.manage.domain.model.GoodsInfoModel;
import com.topjet.manage.domain.model.OrderAffiliateModel;
import com.topjet.manage.domain.model.OrderInfoModel;

import java.util.List;

/**
 * @Author: zhaocj
 * @Description: 订单service
 * @Date: 2017-08-23 15:52
 */

public interface OrderInfoService {

    /**
     * 匹配中心用户查询
     * @param query
     * @return
     */
    List<MatchCenterUserBean> userQueryList(MatchCenterUserBean query);

    /**
     * 查询匹配中心用户数量
     * @param query
     * @return
     */
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
     * 根据订单id查询
     */
    public OrderInfoModel selectByPrimaryKey(Integer id);
    /**
     * 根据orderId 查询冻结表OrderAffiliateModel
     */
    public List<OrderAffiliateModel> getOrderAffiliateList(Integer orderId);

    /**
     *货源查询
     */
    List<MatchCenterOrderBean> goodQuertList(MatchCenterOrderBean matchCenterOrderBean);

    /**
     *货源查询页数
     */
    Integer goodQuertCount(MatchCenterOrderBean matchCenterOrderBean);

}
