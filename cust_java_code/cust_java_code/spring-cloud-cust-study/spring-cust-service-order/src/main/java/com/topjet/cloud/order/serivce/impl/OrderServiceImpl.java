package com.topjet.cloud.order.serivce.impl;

import com.topjet.cloud.manage.custservice.order.bean.FrostOrUnfreezeOrderVRU;
import com.topjet.cloud.manage.custservice.order.bean.HiddenOrShowOrderRTS;
import com.topjet.cloud.manage.custservice.order.bean.HiddenOrShowOrderVRU;
import com.topjet.cloud.manage.custservice.order.bean.HrostOrUnfreezeOrderRTS;
import com.topjet.cloud.order.constant.GoodsConstant;
import com.topjet.cloud.order.constant.OrderConstant;
import com.topjet.cloud.order.dao.GoodsInfoDao;
import com.topjet.cloud.order.dao.OrderAffiliateDao;
import com.topjet.cloud.order.dao.model.GoodsInfoModel;
import com.topjet.cloud.order.dao.model.OrderAffiliateModel;
import com.topjet.cloud.order.serivce.Orderservice;
import com.topjet.tool.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.topjet.cloud.order.constant.WebOrderConstant.*;

/**
 * Created by hongtaoer-win on 2017/8/30.
 */
@Transactional
@Service
public class OrderServiceImpl implements Orderservice {

    @Autowired
    private OrderAffiliateDao orderAffiliateDao;

    @Autowired
    private GoodsInfoDao goodsInfoDao;

    /**
     * 隐藏或者显示某货源
     * @param hiddenOrShowOrderRTS
     * @return
     */
    @Override
    public HiddenOrShowOrderVRU hiddenOrShowOrder(HiddenOrShowOrderRTS hiddenOrShowOrderRTS) {

        //不为空
        if(hiddenOrShowOrderRTS.getIsHidden() == null && hiddenOrShowOrderRTS.getUserId() == null && hiddenOrShowOrderRTS.getGoodsId() == null){
            return new HiddenOrShowOrderVRU(WEB_MSG_4.getCode(),WEB_MSG_4.getMsg());
        }

        //参数校验
        if(!hiddenOrShowOrderRTS.getIsHidden().equals(GoodsConstant.ORDER_ISHIDDEN_NO) && !hiddenOrShowOrderRTS.getIsHidden().equals(GoodsConstant.ORDER_ISHIDDEN_YES)){
            return new HiddenOrShowOrderVRU(WEB_MSG_3.getCode(),WEB_MSG_3.getMsg());
        }


        GoodsInfoModel goodsInfoModel = goodsInfoDao.selectGoodInfoModeById(hiddenOrShowOrderRTS.getGoodsId());


        if(goodsInfoModel.getIsHidden().equals(hiddenOrShowOrderRTS.getIsHidden())){
            //参数错误
            return new HiddenOrShowOrderVRU(WEB_MSG_3.getCode(),WEB_MSG_3.getMsg());
        }

        goodsInfoModel.setIsHidden(hiddenOrShowOrderRTS.getIsHidden());
        goodsInfoModel.setUpdateTime(DateUtil.now());

        //修改
        Integer result = goodsInfoDao.updateHiddenStatus(goodsInfoModel);
        if(result >0){
            //操作成功
            return new HiddenOrShowOrderVRU(WEB_MSG_1.getCode(),WEB_MSG_1.getMsg());
        }
        return new HiddenOrShowOrderVRU(WEB_MSG_2.getCode(),WEB_MSG_2.getMsg());

    }

    /**
     * 冻结或者解冻某订单
     * @param hrostOrUnfreezeOrderRTS
     * @return
     */
    @Override
    public FrostOrUnfreezeOrderVRU frostOrUnfreezeOrder(HrostOrUnfreezeOrderRTS hrostOrUnfreezeOrderRTS) {
        //不为空
        if(hrostOrUnfreezeOrderRTS.getOrderFrozenState() == null && hrostOrUnfreezeOrderRTS.getOrderId() == null && hrostOrUnfreezeOrderRTS.getUserId() == null){
            return new FrostOrUnfreezeOrderVRU(WEB_MSG_4.getCode(),WEB_MSG_4.getMsg());
        }

        //参数校验
        if(!hrostOrUnfreezeOrderRTS.getOrderFrozenState().equals(OrderConstant.ORDER_FROST_YES) && !hrostOrUnfreezeOrderRTS.getOrderFrozenState().equals(OrderConstant.ORDER_FROST_NO)){
            return new FrostOrUnfreezeOrderVRU(WEB_MSG_3.getCode(),WEB_MSG_3.getMsg());
        }


        OrderAffiliateModel orderAffiliateModel = orderAffiliateDao.selectOrderById(hrostOrUnfreezeOrderRTS.getOrderId());

        //判断订单的订单状态和订单数据
        if(orderAffiliateModel !=null && orderAffiliateModel.getOrderFrozenState().equals(hrostOrUnfreezeOrderRTS.getOrderFrozenState())){
            //参数错误
            return new FrostOrUnfreezeOrderVRU(WEB_MSG_3.getCode(),WEB_MSG_3.getMsg());
        }

        orderAffiliateModel.setOrderFrozenState(hrostOrUnfreezeOrderRTS.getOrderFrozenState());
        orderAffiliateModel.setCreateBy(hrostOrUnfreezeOrderRTS.getUserId());
        orderAffiliateModel.setCreateTime(DateUtil.now());

        //第一次冻结
        if(orderAffiliateModel == null && orderAffiliateModel.getOrderFrozenState().equals(OrderConstant.ORDER_FROST_YES)){
            Integer result =  orderAffiliateDao.fristFrostOrder(orderAffiliateModel);
            if(result >0){
                //操作成功
                return new FrostOrUnfreezeOrderVRU(WEB_MSG_1.getCode(),WEB_MSG_1.getMsg());
            }
            return new FrostOrUnfreezeOrderVRU(WEB_MSG_2.getCode(),WEB_MSG_2.getMsg());
        }



        //继续组装
        orderAffiliateModel.setOrderId(hrostOrUnfreezeOrderRTS.getOrderId());
        orderAffiliateModel.setUpdateBy(hrostOrUnfreezeOrderRTS.getUserId());
        orderAffiliateModel.setUpdateTime(DateUtil.now());


        if (hrostOrUnfreezeOrderRTS.getOrderFrozenState().equals(OrderConstant.ORDER_FROST_YES)){
            //冻结
            Integer result =  orderAffiliateDao.frostOrder(orderAffiliateModel);
            if(result >0){
                //操作成功
                return new FrostOrUnfreezeOrderVRU(WEB_MSG_1.getCode(),WEB_MSG_1.getMsg());
            }
        }else if(hrostOrUnfreezeOrderRTS.getOrderFrozenState().equals(OrderConstant.ORDER_FROST_NO)){
            //解冻
            Integer result =  orderAffiliateDao.unfreezeOrder(orderAffiliateModel);
            if(result >0){
                //操作成功
                return new FrostOrUnfreezeOrderVRU(WEB_MSG_1.getCode(),WEB_MSG_1.getMsg());
            }
        }

        return new FrostOrUnfreezeOrderVRU(WEB_MSG_2.getCode(),WEB_MSG_2.getMsg());
    }

}
