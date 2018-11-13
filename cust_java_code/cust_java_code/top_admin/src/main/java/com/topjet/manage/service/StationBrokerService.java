package com.topjet.manage.service;

import com.topjet.manage.domain.model.BrokerInfoModel;
import com.topjet.manage.domain.model.BrokerRouteInfoModel;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-11-06 13:36
 */

public interface StationBrokerService {

    List<Map<String,Object>> listBroker(BrokerInfoModel brokenInfo);

    List<BrokerInfoModel> getBrokerInfo(BrokerInfoModel brokenInfo);

    int insertBroker(BrokerInfoModel brokenInfo);

    int updateBroker(BrokerInfoModel brokenInfo);

    int deleteBroker(BrokerInfoModel brokenInfo);

    List<BrokerRouteInfoModel> listBrokerRouteInfo(BrokerRouteInfoModel brokerRouteInfo);

    int insertBrokerRouteInfo(BrokerRouteInfoModel brokerRouteInfo);

    int updateBrokerRouteInfo(BrokerRouteInfoModel brokerRouteInfo);

    int deleteBrokerRouteInfo(BrokerRouteInfoModel brokerRouteInfo);

}
