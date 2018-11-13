package com.topjet.manage.service.impl;

import com.topjet.manage.domain.model.BonusSettingModel;
import com.topjet.manage.mapper.readMapper.BonusSettingModelEMapper;
import com.topjet.manage.mapper.writeMapper.BonusSettingModelMapper;
import com.topjet.manage.service.BonusSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyj on 2017/10/17.
 */
@Service
public class BonusSettingServiceImpl implements BonusSettingService {

    @Autowired
    private BonusSettingModelEMapper bonusSettingModelEMapper;
    @Autowired
    private BonusSettingModelMapper bonusSettingModelMapper;

    @Override
    public List<BonusSettingModel> getBounsSettingList(BonusSettingModel bonusSettingModel) {
        return bonusSettingModelEMapper.getBounsSettingList(bonusSettingModel);
    }

    @Override
    public Integer insertBonusSetting(BonusSettingModel bonusSettingModel) {
        return bonusSettingModelMapper.insertBonusSetting(bonusSettingModel);
    }

    @Override
    public Integer updateBonusSetting(BonusSettingModel bonusSettingModel) {
        return bonusSettingModelMapper.updateBonusSetting(bonusSettingModel);
    }

    @Override
    public List<BonusSettingModel> getBonusSettingByBonusType(Integer bonusType) {
        return bonusSettingModelEMapper.getBonusSettingByBonusType(bonusType);
    }

    @Override
    public BonusSettingModel findBonusSettingById(Integer id) {
        return bonusSettingModelEMapper.findBonusSettingById(id);
    }
}
