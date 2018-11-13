package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.ScoreNewSettingModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-10-13 14:47
 */

@Mapper
public interface ScoreNewSettingModelMapper {

    @Update("UPDATE resourceDB.scoreNewSetting SET controlCount = #{controlCount}  , scoreMax = #{scoreMax} , everyTimeScore = #{everyTimeScore} , updateTime = #{updateTime} , updateBy = #{updateBy} ,version = #{version} WHERE id = #{id}")
    Integer updateScoreNewSetting(ScoreNewSettingModel scoreNewSettingModel);

    @Insert("INSERT INTO resourceDB.scoreNewSetting (ruleId,scoreType,controlCount,everyTimeScore,scoreMax,createBy,createTime,updateBy,updateTime,deleted,version) VALUES (#{ruleId},#{scoreType},#{controlCount},#{everyTimeScore},#{scoreMax},#{createBy},#{createTime},#{updateBy},#{updateTime},#{deleted},#{version})")
    Integer insertSelective(ScoreNewSettingModel scoreNewSettingModel);
}
