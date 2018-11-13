package com.topjet.manage.mapper.writeMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-11-01 12:26
 */

@Mapper
public interface IntegralRuleInfoModelMapper {

    @Update("UPDATE resourceDB.integralRruleInfo SET ruleName = #{ruleName}, ruleDescription = #{ruleDescription} WHERE id = #{ruleId}")
    Integer updateIntegralRuleInfo(@Param("ruleId") Integer ruleId, @Param("ruleName") String ruleName, @Param("ruleDescription") String ruleDescription);
}
