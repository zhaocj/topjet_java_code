package com.topjet.manage.service;

import com.topjet.common.exception.pms.TopjetExceptionHandler;
import com.topjet.system.domain.model.AppUpgradeHistoryModel;
import com.topjet.system.domain.model.AppUpgradeInfoModel;

import java.util.List;

/**
 * Created by liyj on 2017/8/8.
 */
public interface SysAppUpGradeService {
    /**
     *查询版本列表
     */
    public List<AppUpgradeInfoModel> getAppUpGradeList(AppUpgradeInfoModel appUpgradeInfoModel);
    /**
     * 版本页数
     */
    public Integer getCount(AppUpgradeInfoModel appUpgradeInfoModel);
    /**
     * 根据id查询版本
     */
    public AppUpgradeInfoModel findAppById(Integer id);
    /**
     * sava或者update
     */
    public void saveOrUpdate(AppUpgradeInfoModel appModel) throws TopjetExceptionHandler;
    /**
     * 版本历史
     */
    public List<AppUpgradeHistoryModel>  getAppHistoryList(AppUpgradeHistoryModel appUpgradeHistoryModel);
    /**
     * 版本历史页数
     */
    public Integer getHistoryCount(AppUpgradeHistoryModel appUpgradeHistoryModel);
    /**
     * 根据类型查询版本
     */
    public List<AppUpgradeInfoModel> findAppByType(String type);
    /**
     * 推送
     */
    public Integer send(AppUpgradeInfoModel appUpgradeInfoModel);


}
