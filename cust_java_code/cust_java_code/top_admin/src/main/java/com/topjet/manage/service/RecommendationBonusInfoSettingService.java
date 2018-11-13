package com.topjet.manage.service;

import com.topjet.manage.domain.model.RecommendationBonusSettingModel;

/**
 * @Author: zhaocj
 * @Description: 推荐补贴设置表
 * @Date: 2017-09-01 9:25
 */

public interface RecommendationBonusInfoSettingService {

    RecommendationBonusSettingModel selectByPrimaryKey(Integer id);
}
