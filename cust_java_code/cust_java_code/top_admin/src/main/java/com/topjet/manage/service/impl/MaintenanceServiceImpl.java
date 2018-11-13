package com.topjet.manage.service.impl;

import com.topjet.common.util.DateUtil;
import com.topjet.manage.constants.CommonConstant;
import com.topjet.manage.service.MaintenanceService;
import com.topjet.system.domain.model.AssertSettingInfoModel;
import com.topjet.system.mapper.AssertSettingInfoModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyj on 2017/8/22.
 */
@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    private AssertSettingInfoModelMapper assertSettingInfoModelMapper;

    @Override
    public List<AssertSettingInfoModel> getAllAssertList() {
        return assertSettingInfoModelMapper.getAllAssertList();
    }

    @Override
    public int getAssertCount() {
        return assertSettingInfoModelMapper.getAssertCount();
    }

    @Override
    public void addMaintenance(AssertSettingInfoModel assertSettingInfoModel) {
        assertSettingInfoModel.setCreateTime(DateUtil.now());
        assertSettingInfoModel.setAppType(0);
        assertSettingInfoModelMapper.insertSelective(assertSettingInfoModel);
    }

    @Override
    public void updateMaintence(AssertSettingInfoModel assertSettingInfoModel) {
        assertSettingInfoModel.setUpdateTime(DateUtil.now());
        assertSettingInfoModelMapper.updateByPrimaryKeySelective(assertSettingInfoModel);
    }

    @Override
    public void deleteMaintenance(Integer id) {
        AssertSettingInfoModel assertSettingInfoModel = assertSettingInfoModelMapper.selectByPrimaryKey(id);
        assertSettingInfoModel.setDeleted(CommonConstant.DB_RECORD_DELETED_STATUS_TURE);
        assertSettingInfoModel.setUpdateTime(DateUtil.now());
        assertSettingInfoModelMapper.updateByPrimaryKeySelective(assertSettingInfoModel);
    }

    @Override
    public AssertSettingInfoModel findAssertById(Integer id) {
        return assertSettingInfoModelMapper.selectByPrimaryKey(id);
    }
}
