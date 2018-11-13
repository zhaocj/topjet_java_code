package com.topjet.manage.service.impl;

import com.topjet.manage.domain.bean.MatchCenterOrderBean;
import com.topjet.manage.domain.bean.MatchCenterUserBean;
import com.topjet.manage.domain.model.GoodsInfoModel;
import com.topjet.manage.domain.model.OrderAffiliateModel;
import com.topjet.manage.domain.model.OrderInfoModel;
import com.topjet.manage.mapper.readMapper.OrderAffiliateModelEMapper;
import com.topjet.manage.mapper.readMapper.OrderInfoModelEMapper;
import com.topjet.manage.service.OrderInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-08-23 15:53
 */

@Transactional
@Service
public class OrderInfoServiceImpl implements OrderInfoService{

    @Autowired
    private OrderInfoModelEMapper orderInfoModelEMapper;


    @Autowired
    private OrderAffiliateModelEMapper orderAffiliateModelEMapper;

    private static Logger log = LoggerFactory.getLogger(OrderInfoServiceImpl.class);


    @Override
    public List<MatchCenterUserBean> userQueryList(MatchCenterUserBean query) {

        return orderInfoModelEMapper.userQueryList(query);
    }

    @Override
    public Integer userQueryCount(MatchCenterUserBean query) {
        return orderInfoModelEMapper.userQueryCount(query);
    }

    @Override
    public List<MatchCenterOrderBean> orderQuertList(MatchCenterOrderBean matchCenterOrderBean) {
        return orderInfoModelEMapper.orderQuertList(matchCenterOrderBean);
    }

    @Override
    public Integer orderQuertCount(MatchCenterOrderBean matchCenterOrderBean) {
        return orderInfoModelEMapper.orderQuertCount(matchCenterOrderBean);
    }
    @Override
    public List<GoodsInfoModel> getGoodsInfo(GoodsInfoModel goodsInfoModel){
        return orderInfoModelEMapper.getGoodsInfo(goodsInfoModel);
    }

    @Override
    public OrderInfoModel selectByPrimaryKey(Integer id) {
        return orderInfoModelEMapper.getOrderInfoById(id);
    }

    @Override
    public List<OrderAffiliateModel> getOrderAffiliateList(Integer orderId) {
        return orderAffiliateModelEMapper.getOrderAffiliateList(orderId);
    }

    @Override
    public Integer goodQuertCount(MatchCenterOrderBean matchCenterOrderBean) {
        return orderInfoModelEMapper.goodQuertCount(matchCenterOrderBean);
    }

    @Override
    public List<MatchCenterOrderBean> goodQuertList(MatchCenterOrderBean matchCenterOrderBean) {
        return orderInfoModelEMapper.goodQuertList(matchCenterOrderBean);
    }
}
