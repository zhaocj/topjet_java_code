package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.bean.BonusBillInfoBean;
import com.topjet.manage.domain.bean.BonusBillInfoModelBean;
import com.topjet.manage.domain.bean.RecommendationFeeBounsBillDeatailBean;
import com.topjet.manage.domain.model.BonusBillInfoModel;
import com.topjet.manage.domain.model.RecommendationBonusInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface BonusBillInfoModelMapper extends BaseMapper<BonusBillInfoModel> {


    Integer updateByParentId(BonusBillInfoModel bonusBillInfoModel);

}