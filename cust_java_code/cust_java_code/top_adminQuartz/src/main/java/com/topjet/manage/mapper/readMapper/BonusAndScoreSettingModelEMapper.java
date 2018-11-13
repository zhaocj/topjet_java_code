package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.BonusSettingModel;
import com.topjet.manage.domain.model.ScoreNewSettingModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author: zhaocj
 * @Description:积分和补贴配置扫描mapper
 * @Date: 2017-10-25 13:43
 */

@Mapper
public interface BonusAndScoreSettingModelEMapper {

    /**
     * 获取尚未生效的积分奖励配置项
     * @return
     */
    @Select("SELECT * FROM resourceDB.scoreNewSetting WHERE deleted = 2")
    List<ScoreNewSettingModel> getUnResetScoreNewSetting();


    @Select("SELECT * FROM resourceDB.scoreNewSetting WHERE scoreType = #{scoreType} AND deleted = #{deleted}")
    ScoreNewSettingModel getScoreNewSettingModel(@Param("scoreType") Integer scoreType, @Param("deleted")Integer deleted);


    /**
     * 查询补贴配置未生效得 deleted= 2
     */
    @Select("SELECT * from resourceDB.bonusSetting where deleted = 2")
    List<BonusSettingModel> getResetBonusSetting();

    /**
     * 根据补贴类型 补贴对象 deleted 查询补贴数据
     */
    @Select("SELECT * FROM resourceDB.bonusSetting WHERE bonusType = #{bonusType} AND deleted = #{deleted} AND bonusTarget = #{bonusTarget}")
    BonusSettingModel getBonusSettingModel(@Param("bonusType") Integer bonusType,@Param("deleted")Integer deleted,@Param("bonusTarget")Integer bonusTarget);



}
