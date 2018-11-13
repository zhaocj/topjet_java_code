package com.topjet.manage.service.impl;

import com.topjet.manage.domain.model.CallLogModel;
import com.topjet.manage.mapper.readMapper.CallLogModelEMapper;
import com.topjet.manage.mapper.writeMapper.CallLogModelMapper;
import com.topjet.manage.service.CallLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yuzh on 2017/8/24.
 */
@Service
public class CallLogServiceImpl implements CallLogService {
    @Autowired
    private CallLogModelMapper callLogModelMapper;
    @Autowired
    private CallLogModelEMapper callLogModelEMapper;
    @Override
    public List<CallLogModel> getCallLogList(String mobile) {
        return callLogModelEMapper.getCallLogList(mobile);
    }

    @Override
    public Integer insertCallLog(CallLogModel callLogModel) {
        return callLogModelMapper.insertCallLog(callLogModel);
    }

    @Override
    public int getCallLogCount(String mobile) {
        return callLogModelEMapper.getCallLogCount(mobile);
    }
}
