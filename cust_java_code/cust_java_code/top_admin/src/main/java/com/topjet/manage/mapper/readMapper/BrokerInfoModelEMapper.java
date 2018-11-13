package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.BrokerInfoModel;
import com.topjet.manage.domain.model.BrokerRouteInfoModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-11-06 13:51
 */

@Mapper
public interface BrokerInfoModelEMapper {

    List<Map<String,Object>> listBroker(BrokerInfoModel brokerInfoModel);

    List<BrokerInfoModel> getBrokerInfo(BrokerInfoModel brokenInfo);

    public List<BrokerRouteInfoModel> listBrokerRouteInfo(BrokerRouteInfoModel brokerRouteInfo);

}
