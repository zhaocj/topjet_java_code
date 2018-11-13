package com.topjet.manage.service;

import com.topjet.system.domain.model.AssertSettingInfoModel;

import java.util.List;

/**
 * Created by liyj on 2017/8/22.
 */
public interface MaintenanceService {
    /**
     * 查询所有公告
     */
    public List<AssertSettingInfoModel> getAllAssertList();
    /**
     * 所有公告页数
     */
    public int getAssertCount();
    /**
     * 插入公告
     */
    public void addMaintenance(AssertSettingInfoModel assertSettingInfoModel);
    /**
     * 修改公告
     */
    public void updateMaintence(AssertSettingInfoModel assertSettingInfoModel);
    /**
     * 删除公告 修改公告状态
     */
    public void deleteMaintenance(Integer id);
    /**
     * 根据id查询公告详情
     */
    public AssertSettingInfoModel findAssertById(Integer id);
}
