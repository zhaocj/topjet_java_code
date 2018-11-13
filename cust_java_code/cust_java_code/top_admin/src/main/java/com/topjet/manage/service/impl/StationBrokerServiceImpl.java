package com.topjet.manage.service.impl;

import com.topjet.manage.domain.model.BrokerInfoModel;
import com.topjet.manage.domain.model.BrokerRouteInfoModel;
import com.topjet.manage.mapper.readMapper.BrokerInfoModelEMapper;
import com.topjet.manage.mapper.writeMapper.BrokerInfoModelMapper;
import com.topjet.manage.service.StationBrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-11-06 13:45
 */

@Transactional
@Service
public class StationBrokerServiceImpl implements StationBrokerService {

    @Autowired
    private BrokerInfoModelMapper brokerInfoModelMapper;

    @Autowired
    private BrokerInfoModelEMapper brokerInfoModelEMapper;

    @Override
    public List<Map<String, Object>> listBroker(BrokerInfoModel brokenInfo) {
        return brokerInfoModelEMapper.listBroker(brokenInfo);
    }

    @Override
    public List<BrokerInfoModel> getBrokerInfo(BrokerInfoModel brokenInfo) {
        return brokerInfoModelEMapper.getBrokerInfo(brokenInfo);
    }

    @Override
    public int insertBroker(BrokerInfoModel brokenInfo) {
        return brokerInfoModelMapper.insertBroker(brokenInfo);
    }

    @Override
    public int updateBroker(BrokerInfoModel brokenInfo) {
        return brokerInfoModelMapper.updateBroker(brokenInfo);
    }

    @Override
    public int deleteBroker(BrokerInfoModel brokenInfo) {
        return brokerInfoModelMapper.deleteBroker(brokenInfo);
    }

    @Override
    public List<BrokerRouteInfoModel> listBrokerRouteInfo(BrokerRouteInfoModel brokerRouteInfo) {
        return brokerInfoModelEMapper.listBrokerRouteInfo(brokerRouteInfo);
    }

    @Override
    public int insertBrokerRouteInfo(BrokerRouteInfoModel brokerRouteInfo) {
        return brokerInfoModelMapper.insertBrokerRouteInfo(brokerRouteInfo);
    }

    @Override
    public int updateBrokerRouteInfo(BrokerRouteInfoModel brokerRouteInfo) {
        return brokerInfoModelMapper.updateBrokerRouteInfo(brokerRouteInfo);
    }

    @Override
    public int deleteBrokerRouteInfo(BrokerRouteInfoModel brokerRouteInfo) {
        return brokerInfoModelMapper.deleteBrokerRouteInfo(brokerRouteInfo);
    }
}
