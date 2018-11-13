package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.RecommendationBonusSettingModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RecommendationBonusSettingModelEMapper extends BaseEMapper<RecommendationBonusSettingModel> {

    List<RecommendationBonusSettingModel> selectListByEntity(RecommendationBonusSettingModel recommendationBonusSettingModel);

}