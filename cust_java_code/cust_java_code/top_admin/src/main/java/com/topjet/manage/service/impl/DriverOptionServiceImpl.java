package com.topjet.manage.service.impl;

import com.topjet.manage.domain.bean.MatchCenterDriverBean;
import com.topjet.manage.mapper.readMapper.DriverTruckInfoModelEMapper;
import com.topjet.manage.service.DriverOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyj on 2017/8/23.
 */
@Service
public class DriverOptionServiceImpl implements DriverOptionService {

    @Autowired
    private DriverTruckInfoModelEMapper driverTruckInfoModelEMapper;

    @Override
    public List<MatchCenterDriverBean> getDriverOptionList(MatchCenterDriverBean matchCenterDriverBean) {
        return driverTruckInfoModelEMapper.getDriverOptionList(matchCenterDriverBean);
    }

    @Override
    public int getDriverOptionCount(MatchCenterDriverBean matchCenterDriverBean) {
        return driverTruckInfoModelEMapper.getDriverOptionCount(matchCenterDriverBean);
    }
}
