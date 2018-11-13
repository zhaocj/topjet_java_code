package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.BrokerInfoModel;
import com.topjet.manage.domain.model.BrokerRouteInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-11-06 13:51
 */

@Mapper
public interface BrokerInfoModelMapper {

    int insertBroker(BrokerInfoModel brokenInfo);


    @Update("UPDATE brokerInfo set userId=#{userId}, updateTime=NOW() where id=#{id}")
    int updateBroker(BrokerInfoModel brokenInfo);

    @Update("UPDATE brokerInfo set deleted=1 where id=#{id}")
    int deleteBroker(BrokerInfoModel brokenInfo);

    int insertBrokerRouteInfo(BrokerRouteInfoModel brokerRouteInfo);

    @Update("UPDATE brokerRouteInfo set beginCity=#{beginCity}, endCity=#{endCity}, updateTime=NOW() where id=#{id}")
    int updateBrokerRouteInfo(BrokerRouteInfoModel brokerRouteInfo);

    int deleteBrokerRouteInfo(BrokerRouteInfoModel brokerRouteInfo);

}
