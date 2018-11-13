package com.topjet.manage.service.impl;

import com.topjet.manage.domain.model.DriverBusinessLineInfoModel;
import com.topjet.manage.mapper.readMapper.DriverBusinessLineInfoModelEMapper;
import com.topjet.manage.service.DriverBusinessLineInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liyj on 2017/12/11.
 */
@Service
public class DriverBusinessLineInfoServiceImpl implements DriverBusinessLineInfoService{

    @Autowired
    private DriverBusinessLineInfoModelEMapper driverBusinessLineInfoModelEMapper;

    @Override
    public DriverBusinessLineInfoModel getDriverBusinessLineById(Integer id) {
        return driverBusinessLineInfoModelEMapper.getDriverBusinessLineById(id);
    }
}
