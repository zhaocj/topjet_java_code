package com.topjet.manage.service.impl;

import com.topjet.common.SessionUtils;
import com.topjet.manage.common.dynamicpassword.bean.SysUserResonseBean;
import com.topjet.manage.common.dynamicpassword.service.DynamicPasswordService;
import com.topjet.manage.constants.TaskConstants;
import com.topjet.manage.domain.bean.RemindBean;
import com.topjet.manage.domain.model.SysUserModel;
import com.topjet.manage.domain.model.TaskBucketInfoModel;
import com.topjet.manage.mapper.readMapper.SysUserModelEMapper;
import com.topjet.manage.mapper.writeMapper.SysUserModelMapper;
import com.topjet.manage.service.SysUserService;
import com.topjet.manage.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaocj
 * @create 2017-08-04 16:59
 **/
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserModelMapper sysUserModelMapper;

    @Autowired
    private SysUserModelEMapper sysUserModelEMapper;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private DynamicPasswordService dynamicPasswordService;

    @Override
    public List<Integer> getRoleAllUserId(Integer roleId) {
        return null;
    }

    @Override
    public List<SysUserModel> listPageSysUser(Map<String, Object> paramMap) {
        return sysUserModelEMapper.selectPageListByParam(paramMap);
    }

    @Override
    public Integer countSysUser(Map<String, Object> paramMap) {
        return sysUserModelEMapper.getCountByParam(paramMap);
    }

    @Override
    public List<SysUserModel> selectAllRecord(SysUserModel sym) {
        return sysUserModelEMapper.selectAllRecord(sym);
    }

    @Override
    public Integer countSelectAllRecord(Integer userId) {
        return sysUserModelEMapper.countSelectAllRecord(userId);
    }

    @Override
    public SysUserModel selectByPrimaryKey(Integer id) {
        return sysUserModelEMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Integer> getUserId(Integer createBy) {
        List<Integer> updateList = new ArrayList<>();
        List<Integer> list = sysUserModelEMapper.getUserId(createBy);
        if(!list.isEmpty()){
            updateList.addAll(list);
        }
        if(!updateList.isEmpty()){
            for (int i = 0; i <updateList.size() ; i++) {
                List<Integer> list2=sysUserModelEMapper.getUserId(updateList.get(i));
                if(!list2.isEmpty()){
                    updateList.addAll(list2);
                }

            }
/*            for (Integer id:update) {
                    List<Integer> list2=getMapperE().getUserId(id);
                    if(!list2.isEmpty()){
                        update.addAll(list);
                    }
            }*/
        }
        return updateList;
    }

    @Override
    public int deleteByPrimarykey(Integer id) {
        return sysUserModelMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int getUserCountByEmail(String email) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("email",email);
        return sysUserModelEMapper.getCountByParam(paramMap);
    }

    @Override
    public int insertAndGetId(SysUserModel sym) {
        return sysUserModelMapper.insert(sym);
    }

    @Override
    public int updateByPrimaryKey(SysUserModel sysUserModel) {
        return sysUserModelMapper.update(sysUserModel);
    }

    @Override
    public List<SysUserModel> selectSysUser(Map<String, Object> paramMap) {

        return sysUserModelEMapper.selectListByParam(paramMap);
    }

    @Override
    public RemindBean getAllRemindCount() {
        RemindBean rb = new RemindBean();
        List<TaskBucketInfoModel> timList = userInfoService.getTaskSysUser( SessionUtils.getSysUserSession().getId());
        if(!timList.isEmpty()){
            for(TaskBucketInfoModel tb:timList){
                if(tb.getType().equals(TaskConstants.USER_REGISTER_AUDIT)){
                    rb.setUserRegisterRemind(true);
                    rb.setUserRegisterCount(tb.getTaskCount());
                }
                if(tb.getType().equals(TaskConstants.USER_TRUCK_AUDIT)){
                    rb.setUserTruckRemind(true);
                    rb.setUserTruckCount(tb.getTaskCount());
                }
                if(tb.getType().equals(TaskConstants.AGENCY_FIRST_AUDIT)){
                    rb.setAgencyFirstRemind(true);
                    rb.setAgencyFirstCount(tb.getTaskCount());
                }
                if(tb.getType().equals(TaskConstants.AGENCY_SECOND_AUDIT)){
                    rb.setAgencySecondRemind(true);
                    rb.setAgencySecondCount(tb.getTaskCount());
                }
                if(tb.getType().equals(TaskConstants.FRIGHT_FIRST_AUDIT)){
                    rb.setFrightFirstRemind(true);
                    rb.setFrightFirstCount(tb.getTaskCount());
                }
                if(tb.getType().equals(TaskConstants.FRIGHT_SECOND_AUDIT)){
                    rb.setFrightSecondRemind(true);
                    rb.setFrightSecondCount(tb.getTaskCount());
                }
                if(tb.getType().equals(TaskConstants.RECO_FIRST_AUDIT)){
                    rb.setRecoFirstRemind(true);
                    rb.setRecoFirstCount(tb.getTaskCount());
                }
                if(tb.getType().equals(TaskConstants.RECO_SECOND_AUDIT)){
                    rb.setRecoSecondRemind(true);
                    rb.setRecoSecondCount(tb.getTaskCount());
                }
            }
            if(rb.isUserRegisterRemind() || rb.isUserTruckRemind() || rb.isAgencyFirstRemind() || rb.isAgencySecondRemind() || rb.isFrightFirstRemind() || rb.isFrightSecondRemind() || rb.isRecoFirstRemind() || rb.isRecoSecondRemind()){
                rb.setAllRemind(true);
            }
            rb.setAllCount(rb.getUserRegisterCount()+rb.getUserTruckCount()+rb.getAgencyFirstCount()+rb.getAgencySecondCount()+rb.getFrightFirstCount()+rb.getFrightSecondCount()+rb.getRecoFirstCount()+rb.getRecoSecondCount());
        }
        return rb; }

    @Override
    public SysUserResonseBean getDynamicPasswordRQ(String path) {

        SysUserResonseBean sysUserResonseBean=new SysUserResonseBean();
        Integer userId = (Integer)SessionUtils.getAttr("userId");
        Integer code = dynamicPasswordService.isbind(userId);

        if(code==null){
            return null;
        }
        if (code.intValue() == 0) {
            sysUserResonseBean.setDynamicpasswordBind(0);
            Integer bindResult = dynamicPasswordService.bind(userId,path);
            if (bindResult != null && bindResult.intValue() == 0) {
                sysUserResonseBean.setIsBindSuccess(1);
            }
            else{
                sysUserResonseBean.setIsBindSuccess(0);
            }
        }
        else{

            sysUserResonseBean.setDynamicpasswordBind(1);
        }

        return sysUserResonseBean;
    }


}
