package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.BonusSettingModel;
import com.topjet.manage.domain.model.ScoreNewSettingModel;
import org.apache.ibatis.annotations.Update;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-10-25 15:58
 */

public interface BonusAndScoreNewSettingModelMapper {

    @Update("Update resourceDB.scoreNewSetting SET deleted = #{deleted} WHERE id = #{id}")
    int updateScoreNewSetting(ScoreNewSettingModel scoreNewSettingModel);

    /**
     * 修改补贴配置任务
     */
    @Update("Update resourceDB.bonusSetting SET deleted = #{deleted} WHERE id = #{id}")
    Integer updateBonusSetting(BonusSettingModel bonusSettingModel);

}
