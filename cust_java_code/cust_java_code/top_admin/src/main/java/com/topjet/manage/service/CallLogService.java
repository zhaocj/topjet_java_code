package com.topjet.manage.service;

import com.topjet.manage.domain.model.CallLogModel;

import java.util.List;

/**
 * Created by liyj on 2017/8/24.
 */
public interface CallLogService {
    //插入联系记录
    Integer insertCallLog(CallLogModel callLogModel);
    //根据手机号查询联系记录
    public List<CallLogModel> getCallLogList(String mobile);
    //根据手机号查询页数
    public int getCallLogCount(String mobile);
}
