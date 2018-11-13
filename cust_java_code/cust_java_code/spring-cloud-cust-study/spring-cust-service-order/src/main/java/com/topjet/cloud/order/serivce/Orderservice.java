package com.topjet.cloud.order.serivce;

import com.topjet.cloud.manage.custservice.order.bean.FrostOrUnfreezeOrderVRU;
import com.topjet.cloud.manage.custservice.order.bean.HiddenOrShowOrderRTS;
import com.topjet.cloud.manage.custservice.order.bean.HiddenOrShowOrderVRU;
import com.topjet.cloud.manage.custservice.order.bean.HrostOrUnfreezeOrderRTS;

/**
 * Created by hongtaoer-win on 2017/8/30.
 */
public interface Orderservice {

    /**
     *  隐藏或者显示某订单
     * @param hiddenOrShowOrderRTS
     * @return
     */
    HiddenOrShowOrderVRU hiddenOrShowOrder(HiddenOrShowOrderRTS hiddenOrShowOrderRTS);


    /**
     * 冻结或者解冻某订单
     * @param hrostOrUnfreezeOrderRTS
     * @return
     */
    FrostOrUnfreezeOrderVRU frostOrUnfreezeOrder(HrostOrUnfreezeOrderRTS hrostOrUnfreezeOrderRTS);
}
