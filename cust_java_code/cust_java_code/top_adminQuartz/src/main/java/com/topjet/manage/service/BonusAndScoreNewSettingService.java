package com.topjet.manage.service;

import com.topjet.manage.domain.model.BonusSettingModel;
import com.topjet.manage.domain.model.ScoreNewSettingModel;

import java.util.List;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-10-25 15:13
 */

public interface BonusAndScoreNewSettingService {

    List<ScoreNewSettingModel> getUnResetScoreNewSetting();

    ScoreNewSettingModel getScoreNewSettingModel(Integer scoreType, Integer deleted);

    Integer updateScoreNewSetting(ScoreNewSettingModel scoreNewSettingModel);

    /**
     * 查询补贴配置未生效得 deleted= 2
     */
     List<BonusSettingModel> getResetBonusSetting();

    /**
     * 根据补贴类型 补贴对象 deleted 查询补贴数据
     */
    BonusSettingModel getBonusSettingModel(Integer bonusType,Integer deleted,Integer bonusTarget);

    /**
     * 修改补贴配置任务
     */
    Integer updateBonusSetting(BonusSettingModel bonusSettingModel);
}
