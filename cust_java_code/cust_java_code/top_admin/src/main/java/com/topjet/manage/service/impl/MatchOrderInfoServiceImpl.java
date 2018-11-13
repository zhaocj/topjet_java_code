package com.topjet.manage.service.impl;

import com.topjet.common.ExceptionEnum;
import com.topjet.common.ResultBaseMsg;
import com.topjet.common.SessionUtils;
import com.topjet.common.exception.pms.TopjetExceptionHandler;
import com.topjet.common.topjetlog.TopJetLog;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.domain.model.MatchOrderInfo;
import com.topjet.manage.mapper.writeMapper.MatchOrderInfoMapper;
import com.topjet.manage.service.MatchOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liyj on 2017/8/25.
 */
@Service
public class MatchOrderInfoServiceImpl implements MatchOrderInfoService {

    @Autowired
    private MatchOrderInfoMapper matchOrderInfoMapper;


    @Override
    public Object hideOrder(Integer id) throws TopjetExceptionHandler {
        ResultBaseMsg msg = new ResultBaseMsg();
        MatchOrderInfo matchOrderInfo = new MatchOrderInfo();
        matchOrderInfo.setOrderId(id);
        matchOrderInfo.setCreateTime(DateUtil.now());
        matchOrderInfo.setSysUserId(SessionUtils.getSysUserSession().getId());
        try {
            matchOrderInfoMapper.insert(matchOrderInfo);
        }
        catch (Exception e) {
            TopJetLog.error("插入匹配中心已处理订单异常");
            throw new TopjetExceptionHandler(ExceptionEnum.E_BUSINESS_MSG.getStatus(),ExceptionEnum.E_BUSINESS_MSG.getMsg());
        }
        return msg;
    }
}
