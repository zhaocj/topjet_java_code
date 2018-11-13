package com.topjet.manage.service.impl;

import com.topjet.common.ResultBaseMsg;
import com.topjet.common.SessionUtils;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.domain.bean.CallCenterCallLogBean;
import com.topjet.manage.domain.model.CallCenterCallLogModel;
import com.topjet.manage.domain.model.CallCenterLogModel;
import com.topjet.manage.domain.model.CallCenterUserInfoModel;
import com.topjet.manage.mapper.readMapper.CallCenterCallLogModelEMapper;
import com.topjet.manage.mapper.writeMapper.CallCenterCallLogModelMapper;
import com.topjet.manage.service.CallCenterCallLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-09-14 16:43
 */

@Service
@Transactional
public class CallCenterCallLogServiceImpl implements CallCenterCallLogService{

    @Autowired
    private CallCenterCallLogModelEMapper callCenterCallLogModelEMapper;

    @Autowired
    private CallCenterCallLogModelMapper callCenterCallLogModelMapper;


    @Override
    public List<CallCenterCallLogModel> listCallCenterCallLog(Integer createBy) {
        return callCenterCallLogModelEMapper.listCallCenterCallLog(createBy);
    }

    @Override
    public int countCallCencterCallLog(Integer createBy) {
        return callCenterCallLogModelEMapper.countCallCenterCallLog(createBy);
    }

    @Override
    public List<CallCenterCallLogModel> getCallLogList(CallCenterCallLogBean callCenterCallLogBean) {
        return callCenterCallLogModelEMapper.getCallLogList(callCenterCallLogBean);
    }

    @Override
    public int getCallLogCount(CallCenterCallLogBean ccl) {
        return callCenterCallLogModelEMapper.getCallLogCount(ccl);
    }

    @Override
    public List<CallCenterCallLogModel> selectListByParam(Map<String, Object> paramMap) {
        return callCenterCallLogModelEMapper.selectListByParam(paramMap);
    }

    @Override
    public int insertSelective(CallCenterCallLogModel callCenterCallLogModel) {
        return callCenterCallLogModelMapper.insert(callCenterCallLogModel);
    }

    @Override
    public ResultBaseMsg updateRemark(CallCenterCallLogBean callCenterCallLogBean) {
        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();
        resultBaseMsg.setStatus(ExceptionEnum.E_0.getStatus());
        resultBaseMsg.setMsg(ExceptionEnum.E_0.getMsg());
        //无备注  insert一条记录 callCenterCallLog
        if((callCenterCallLogBean.getId()==0) && (callCenterCallLogBean.getVersion()==0)){
         //根据callSheetId 查询
          CallCenterLogModel ccu =  callCenterCallLogModelEMapper.getCallCenterLog(callCenterCallLogBean);
          CallCenterCallLogModel cccl = new CallCenterCallLogModel();
            cccl.setRingTime(ccu.getRing());
            cccl.setBeginTime(ccu.getBegin());
            cccl.setEndTime(ccu.getEnd());
            if(!ccu.getAgent().equals("undefined")){cccl.setAgent(ccu.getAgent());}
            if(!ccu.getAgentName().equals("undefined")){
                cccl.setAgentName(ccu.getAgentName());
            }
            if(!ccu.getQueue().equals("undefined")){
                cccl.setQueue(ccu.getQueue());
            }
            if(!ccu.getQueueName().equals("undefined")){
                cccl.setQueueName(ccu.getQueueName());
            }
            if(!ccu.getCallSheetId().equals("undefined")){
                cccl.setCallSheetId(ccu.getCallSheetId());
            }
            if(!ccu.getState().equals("undefined")){
                cccl.setStatus(ccu.getState());
            }
            if(!ccu.getCallType().equals("undefined")){
                cccl.setCallType(ccu.getCallType());
            }
            if(!ccu.getCalledNo().equals("undefined")){
                cccl.setOriginCalledNo(ccu.getCalledNo());
            }
            if(!ccu.getCallNo().equals("undefined")){
                cccl.setOriginCallNo(ccu.getCallNo());
            }
            cccl.setCreateTime(DateUtil.now());
            cccl.setCreateBy(SessionUtils.getSysUserSession().getId());
            cccl.setCreateByName(SessionUtils.getSysUserSession().getNickName());
            cccl.setBusinessType(callCenterCallLogBean.getBusinessType());
            cccl.setRemark(callCenterCallLogBean.getRemark());
            callCenterCallLogModelMapper.insert(cccl);
        }else{
            //备注表 中有数据  去校验版本号 进而去update
            CallCenterCallLogModel callCenterCallLogModel= callCenterCallLogModelEMapper.getCallCenterCallLogById(callCenterCallLogBean);
            if( callCenterCallLogModel!= null ){
               if(callCenterCallLogBean.getRemark() !=""){
                   callCenterCallLogModel.setRemark(callCenterCallLogBean.getRemark());
               }

                callCenterCallLogModel.setBusinessType(callCenterCallLogBean.getBusinessType());
                callCenterCallLogModel.setUpdateTime(DateUtil.now());
                callCenterCallLogModel.setUpdateBy(SessionUtils.getSysUserSession().getId());
                callCenterCallLogModel.setVersion(callCenterCallLogModel.getVersion()+1);
                callCenterCallLogModelMapper.update(callCenterCallLogModel);
            }else{
                resultBaseMsg.setStatus(ExceptionEnum.E_DATA_EXPIRED.getStatus());
                resultBaseMsg.setMsg(ExceptionEnum.E_DATA_EXPIRED.getMsg());
            }
        }
        return resultBaseMsg ;
    }

    @Override
    public List<CallCenterUserInfoModel> getSysUser() {
        return callCenterCallLogModelEMapper.getSysUser();
    }
}
