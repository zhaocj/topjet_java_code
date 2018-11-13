package com.topjet.manage.service;

import com.topjet.manage.domain.model.IntegralRruleInfoModel;

/**
 * @Author: zhaocj
 * @Description: 积分任务service
 * @Date: 2017-11-01 12:30
 */

public interface IntegralRuleInfoService {

    IntegralRruleInfoModel selectIntegralRuleInfoByRule(Integer ruleId);

    Integer updateIntegralRuleInfo(Integer ruleId, String ruleName, String ruleDescription);

}
