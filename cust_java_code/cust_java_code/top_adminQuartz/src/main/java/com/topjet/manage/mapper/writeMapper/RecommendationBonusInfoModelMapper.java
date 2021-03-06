package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.RecommendRelationshipModel;
import com.topjet.manage.domain.model.RecommendationBonusInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RecommendationBonusInfoModelMapper extends BaseMapper<RecommendationBonusInfoModel> {

}