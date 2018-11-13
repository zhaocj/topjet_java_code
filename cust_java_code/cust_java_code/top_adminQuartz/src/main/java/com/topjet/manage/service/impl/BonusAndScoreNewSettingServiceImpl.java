package com.topjet.manage.service.impl;

import com.topjet.manage.domain.model.BonusSettingModel;
import com.topjet.manage.domain.model.ScoreNewSettingModel;
import com.topjet.manage.mapper.readMapper.BonusAndScoreSettingModelEMapper;
import com.topjet.manage.mapper.writeMapper.BonusAndScoreNewSettingModelMapper;
import com.topjet.manage.service.BonusAndScoreNewSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-10-25 15:16
 */

@Transactional
@Service
public class BonusAndScoreNewSettingServiceImpl implements BonusAndScoreNewSettingService{

    @Autowired
    private BonusAndScoreSettingModelEMapper bonusAndScoreSettingModelEMapper;

    @Autowired
    private BonusAndScoreNewSettingModelMapper bonusAndScoreNewSettingModelMapper;

    @Override
    public List<ScoreNewSettingModel> getUnResetScoreNewSetting() {
        return bonusAndScoreSettingModelEMapper.getUnResetScoreNewSetting();
    }

    @Override
    public ScoreNewSettingModel getScoreNewSettingModel(Integer scoreType, Integer delted) {
        return bonusAndScoreSettingModelEMapper.getScoreNewSettingModel(scoreType,delted);
    }

    @Override
    public Integer updateScoreNewSetting(ScoreNewSettingModel scoreNewSettingModel) {

        return bonusAndScoreNewSettingModelMapper.updateScoreNewSetting(scoreNewSettingModel);
    }

    @Override
    public List<BonusSettingModel> getResetBonusSetting() {
        return bonusAndScoreSettingModelEMapper.getResetBonusSetting();
    }

    @Override
    public BonusSettingModel getBonusSettingModel(Integer bonusType, Integer deleted, Integer bonusTarget) {
        return bonusAndScoreSettingModelEMapper.getBonusSettingModel(bonusType,deleted,bonusTarget);
    }

    @Override
    public Integer updateBonusSetting(BonusSettingModel bonusSettingModel) {
        return bonusAndScoreNewSettingModelMapper.updateBonusSetting(bonusSettingModel);
    }
}
