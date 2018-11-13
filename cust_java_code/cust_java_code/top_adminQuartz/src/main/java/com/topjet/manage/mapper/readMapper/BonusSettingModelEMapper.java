package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.BonusSettingModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-11-09 18:24
 */

@Mapper
public interface BonusSettingModelEMapper {

    @Select("SELECT * FROM resourceDB.bonusSetting WHERE deleted = 0 AND bonusType = #{bonusType}")
    BonusSettingModel selectTransportFeeSetting(@Param("bonusType") Integer bonusType);
}
