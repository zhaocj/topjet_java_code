package com.topjet.manage.service;

import com.topjet.common.ResultBaseMsg;
import com.topjet.manage.domain.bean.CallCenterCallLogBean;
import com.topjet.manage.domain.model.CallCenterCallLogModel;
import com.topjet.manage.domain.model.CallCenterUserInfoModel;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-09-14 16:37
 */

public interface CallCenterCallLogService {

    /**
     * 根据用户id获取该客服通话记录
     */
    List<CallCenterCallLogModel> listCallCenterCallLog(Integer createBy);

    int countCallCencterCallLog(Integer createBy);

    List<CallCenterCallLogModel> getCallLogList(CallCenterCallLogBean callCenterCallLogBean);

    int getCallLogCount(CallCenterCallLogBean ccl);

    List<CallCenterCallLogModel> selectListByParam(Map<String,Object> paramMap);

    int insertSelective(CallCenterCallLogModel callCenterCallLogModel);
    /**
     * 获取客服列表
     */
    public  List<CallCenterUserInfoModel> getSysUser();

    /*
      修改通话记录备注
     */
    public ResultBaseMsg updateRemark(CallCenterCallLogBean callCenterCallLogBean);
}
