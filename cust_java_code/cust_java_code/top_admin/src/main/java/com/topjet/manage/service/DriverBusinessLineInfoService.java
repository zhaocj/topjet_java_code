package com.topjet.manage.service;

import com.topjet.manage.domain.model.DriverBusinessLineInfoModel;

/**
 * Created by liyj on 2017/12/11.
 */
public interface DriverBusinessLineInfoService {
    /**
     * 根据driverId查询司机常跑路线
     */
    public DriverBusinessLineInfoModel getDriverBusinessLineById(Integer id);
}
