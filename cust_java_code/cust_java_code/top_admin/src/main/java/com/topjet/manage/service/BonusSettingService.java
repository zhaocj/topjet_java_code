package com.topjet.manage.service;

import com.topjet.manage.domain.model.BonusSettingModel;

import java.util.List;

/**
 * Created by liyj on 2017/10/17.
 */
public interface BonusSettingService {

    /**
     *查询有效的所有补贴设值
     */
    public List<BonusSettingModel> getBounsSettingList(BonusSettingModel bonusSettingModel);

    /**
     * 添加补贴配置
     */
    public Integer insertBonusSetting(BonusSettingModel bonusSettingModel);

    /**
     * 修改补贴配置
     */
    public Integer updateBonusSetting(BonusSettingModel bonusSettingModel);

    /**
     * 根据bonusType查询补贴配置deleted = 2 的数据
     */
    public List<BonusSettingModel> getBonusSettingByBonusType(Integer bonusType);

    /**
     * 根据id查询补贴配置
     */
    public BonusSettingModel findBonusSettingById(Integer id);


}
