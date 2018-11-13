package com.topjet.manage.service;

import com.topjet.manage.domain.model.OrderBonusInfoModel;

/**
 * @Author: zhaocj
 * @Description:运费补贴数据service
 * @Date: 2017-10-28 13:23
 */

public interface OrderBonusInfoService {

    boolean verifyIsOrderBonus(String orderNo,Integer bonusType);

    OrderBonusInfoModel createOrderBonusInfo(String orderNo, Integer bonusType);

}
