package com.topjet.manage.service;

import com.topjet.manage.domain.model.RecommendRelationshipModel;
import com.topjet.manage.domain.model.RecommendationBonusInfoModel;

import java.util.List;

/**
 * @Author: zhaocj
 * @Description: 推荐补贴service
 * @Date: 2017-09-04 16:51
 */

public interface RecommendationBonusInfoService {

    boolean verificationBinus(RecommendRelationshipModel model);

    int insert(RecommendationBonusInfoModel recommendationBonusInfoModel);

    /**
     * 获得所有有满足条件的推荐补贴用户
     * @return
     */
    List<RecommendRelationshipModel> getSuitableRecoBonusAllUser();}
