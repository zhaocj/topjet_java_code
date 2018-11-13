package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.bean.ScoreNewSettingBean;
import com.topjet.manage.domain.model.ScoreNewSettingModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-10-12 15:44
 */

@Mapper
public interface ScoreNewSettingModelEMapper {

    /**
     * 根据deleted值获取积分奖励配置列表
     * @param paramMap
     * @return
     */
//    @Select("SELECT * FROM resourceDB.scoreNewSetting WHERE deleted = #{deleted}")
    List<ScoreNewSettingBean> selectScoreSettingList(Map<String,Object> paramMap);

//    @Select("SELECT * FROM resourceDB.scoreNewSetting WHERE deleted = #{deleted} AND scoreType = #{scoreType}")
//    List<ScoreNewSettingModel> selectScoreSettingLists(Map<String,Object> paramMap);

    @Select("SELECT * FROM resourceDB.scoreNewSetting WHERE id = #{id}")
    ScoreNewSettingModel selectScoreNewSettingByPrimaryKey(@Param("id") Integer id);



}
