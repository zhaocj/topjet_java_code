package com.topjet.manage.service;

import com.topjet.common.exception.pms.TopjetExceptionHandler;
import com.topjet.manage.domain.bean.OrderBonusBean;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.vo.OrderBonusListQuery;
import org.springframework.core.annotation.Order;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-10-16 19:42
 */

public interface OrderBonusInfoService {

    /**
     * 获取订单补贴列表
     * @param query
     * @return
     */
    PaginationBean listOrderBonusInfo(OrderBonusListQuery query);

    /**
     * 获取待审核订单补贴列表
     * @param orderBonusBean
     * @return
     */
    PaginationBean listAuditOrderBonusInfo(OrderBonusBean orderBonusBean);

    OrderBonusBean auditOrderBonus(OrderBonusBean orderBonusBean) throws TopjetExceptionHandler;

}
