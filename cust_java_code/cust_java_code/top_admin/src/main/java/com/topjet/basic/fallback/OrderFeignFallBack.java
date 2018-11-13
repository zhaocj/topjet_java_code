package com.topjet.basic.fallback;

import com.topjet.basic.OrderFeignService;
import com.topjet.cloud.manage.custservice.order.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liyj on 2017/9/1.
 */
public class OrderFeignFallBack implements OrderFeignService{
    private static Logger log = LoggerFactory.getLogger(OrderFeignFallBack.class);

    public HiddenOrShowOrderVRU hiddenOrShowOrder(HiddenOrShowOrderRTS rts){
        log.error("OrderFeignFallBack ERROR: hiddenOrShowOrder" + rts );
        log.error("请求订单服务发生错误 ERROR: hiddenOrShowOrder" + rts );
        return null;
    }
    public FrostOrUnfreezeOrderVRU frostOrUnfreezeOrder(HrostOrUnfreezeOrderRTS rts){
        log.error("OrderFeignFallBack ERROR: frostOrUnfreezeOrder"+ rts);
        log.error("请求订单服务发生错误 ERROR: frostOrUnfreezeOrder"+ rts);
        return  null;
    }


}
