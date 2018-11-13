package com.topjet.manage.service.base;

import com.topjet.manage.domain.model.IntegralRruleInfoModel;
import com.topjet.manage.mapper.readMapper.IntegralRuleInfoModelEMapper;
import com.topjet.manage.mapper.writeMapper.IntegralRuleInfoModelMapper;
import com.topjet.manage.service.IntegralRuleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-11-01 12:33
 */


@Transactional
@Service
public class IntegralRuleInfoServiceImpl implements IntegralRuleInfoService{
    @Autowired
    private IntegralRuleInfoModelEMapper integralRuleInfoModelEMapper;

    @Autowired
    private IntegralRuleInfoModelMapper integralRuleInfoModelMapper;

    @Override
    public IntegralRruleInfoModel selectIntegralRuleInfoByRule(Integer ruleId) {
        return integralRuleInfoModelEMapper.selectIntegralRuleInfoByRuleId(ruleId);
    }

    @Override
    public Integer updateIntegralRuleInfo(Integer ruleId, String ruleName, String ruleDescription) {
        return integralRuleInfoModelMapper.updateIntegralRuleInfo(ruleId,ruleName,ruleDescription);
    }
}
