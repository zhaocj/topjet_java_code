package com.topjet.manage.service.impl;

import com.topjet.manage.domain.model.RecommendationBonusSettingModel;
import com.topjet.manage.mapper.readMapper.RecommendationBonusSettingModelEMapper;
import com.topjet.manage.service.RecommendationBonusInfoSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-09-01 9:27
 */

@Transactional
@Service
public class RecommendationBonusInfoSettingServiceImpl implements RecommendationBonusInfoSettingService {

    @Autowired
    private RecommendationBonusSettingModelEMapper recommendationBonusSettingModelMapper;

    @Override
    public RecommendationBonusSettingModel selectByPrimaryKey(Integer id) {
        return recommendationBonusSettingModelMapper.selectByPrimaryKey(id);
    }
}
