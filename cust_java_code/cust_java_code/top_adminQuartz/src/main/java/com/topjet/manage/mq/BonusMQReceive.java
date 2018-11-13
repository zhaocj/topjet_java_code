package com.topjet.manage.mq;

import com.topjet.manage.service.OrderBonusInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhaocj on 2017/9/27.
 */
@Component
public class BonusMQReceive {

    private static final Logger LOG = LoggerFactory.getLogger(BonusMQReceive.class);

    @Autowired
    private OrderBonusInfoService orderBonusInfoService;

    // 运费补贴生成
//    @RabbitHandler
//    @RabbitListener(queues = CommonConstant.BONUS_MQ_ARTIFICAL)
    /*public void gainScore( OrderBonusMQBean orderBonusMQBean){
        LOG.debug(" 收到MQ请求信息: "+CommonConstant.BONUS_MQ_ARTIFICAL+":" + orderBonusMQBean );
        Integer orderId = orderBonusMQBean.getOrderId();
        LOG.debug("开始校验运费补贴是否符合生成条件");
        if(orderBonusInfoService.verifyIsOrderBonus(orderId+"",1)){
            orderBonusInfoService.createOrderBonusInfo(orderId+"",1);
        }

    }*/



}
