package com.topjet.cloud.order.controller;

import com.topjet.cloud.manage.custservice.order.OrderCustService;
import com.topjet.cloud.manage.custservice.order.bean.FrostOrUnfreezeOrderVRU;
import com.topjet.cloud.manage.custservice.order.bean.HiddenOrShowOrderRTS;
import com.topjet.cloud.manage.custservice.order.bean.HiddenOrShowOrderVRU;
import com.topjet.cloud.manage.custservice.order.bean.HrostOrUnfreezeOrderRTS;
import com.topjet.cloud.order.serivce.Orderservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhangn on 2017/8/2.
 */
@RestController
public class OrderController implements OrderCustService {

    private static Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private Orderservice orderservice;

    /**
     *  隐藏或者显示某订单
     * @param hiddenOrShowOrderRTS
     * @return
     */
    @PostMapping("/hiddenorshoworder")
    public HiddenOrShowOrderVRU hiddenOrShowOrder(@RequestBody HiddenOrShowOrderRTS hiddenOrShowOrderRTS){
        return orderservice.hiddenOrShowOrder(hiddenOrShowOrderRTS);
    }


    /**
     * 冻结或者解冻某订单
     * @param hrostOrUnfreezeOrderRTS
     * @return
     */
    @PostMapping("/frostorunfreezeorder")
    public FrostOrUnfreezeOrderVRU frostOrUnfreezeOrder(@RequestBody HrostOrUnfreezeOrderRTS hrostOrUnfreezeOrderRTS){

        return orderservice.frostOrUnfreezeOrder(hrostOrUnfreezeOrderRTS);
    }
}
