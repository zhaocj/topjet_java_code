package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.IntegralRruleInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-11-01 12:23
 */

@Mapper
public interface IntegralRuleInfoModelEMapper {


    @Select("SELECT * FROM resourceDB.integralRruleInfo WHERE id = #{ruleId} AND deleted = 0")
    IntegralRruleInfoModel selectIntegralRuleInfoByRuleId(@Param("ruleId") Integer ruleId);

}
