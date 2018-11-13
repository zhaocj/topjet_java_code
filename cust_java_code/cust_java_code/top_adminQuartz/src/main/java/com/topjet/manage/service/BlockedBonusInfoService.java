package com.topjet.manage.service;

import com.topjet.manage.domain.model.BlockedBonusInfoModel;
import com.topjet.manage.domain.model.OrderBonusInfoModel;
import com.topjet.manage.domain.model.OrderInfoModel;

/**
 * @Author: zhaocj
 * @Description: 屏蔽补贴管理service
 * @Date: 2017-10-27 10:52
 */

public interface BlockedBonusInfoService {

    BlockedBonusInfoModel insterBlockedBonus(OrderInfoModel orderInfoModel, Integer userId, String reason, String type, String remark);


}
