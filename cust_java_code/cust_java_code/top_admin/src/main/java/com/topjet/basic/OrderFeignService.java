package com.topjet.basic;

import com.topjet.basic.fallback.OrderFeignFallBack;
import com.topjet.basic.fallback.UserFeignFallBack;
import com.topjet.cloud.manage.constant.ManageServiceConstant;
import com.topjet.cloud.manage.custservice.order.bean.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by liyj 2017/9/1.
 */
@FeignClient(name = ManageServiceConstant.ORDER_SERVICE_NAME ,fallback = OrderFeignFallBack.class)
public interface OrderFeignService {
    /**
     *  隐藏或者显示某订单
     * @param hiddenOrShowOrderRTS
     * @return
     */
    @PostMapping({"/cust-order/hiddenorshoworder"})
    HiddenOrShowOrderVRU hiddenOrShowOrder(@RequestBody HiddenOrShowOrderRTS hiddenOrShowOrderRTS);


    /**
     * 冻结或者解冻某订单
     * @param hrostOrUnfreezeOrderRTS
     * @return
     */
    @PostMapping({"/cust-order/frostorunfreezeorder"})
    FrostOrUnfreezeOrderVRU frostOrUnfreezeOrder(@RequestBody HrostOrUnfreezeOrderRTS hrostOrUnfreezeOrderRTS);


}
