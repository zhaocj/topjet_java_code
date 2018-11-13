package com.topjet.manage.service.impl;

import com.topjet.common.SessionUtils;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.constants.CommonConstant;
import com.topjet.manage.domain.model.SysUserModel;
import com.topjet.manage.domain.model.TaskBucketInfoModel;
import com.topjet.manage.domain.model.TaskInfoModel;
import com.topjet.manage.mapper.readMapper.TaskBucketInfoModelEMapper;
import com.topjet.manage.mapper.readMapper.TaskInfoModelEMapper;
import com.topjet.manage.mapper.writeMapper.TaskBucketInfoModelMapper;
import com.topjet.manage.mapper.writeMapper.TaskInfoModelMapper;
import com.topjet.manage.service.SysUserService;
import com.topjet.manage.service.TaskAssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liyj on 2017/8/10.
 */
@Service
@Transactional
public class TaskAssignServiceImpl implements TaskAssignService {
    @Autowired
    private TaskBucketInfoModelMapper taskBucketInfoModelMapper;

    @Autowired
    private TaskBucketInfoModelEMapper taskBucketInfoModelEMapper;

    @Autowired
    private TaskInfoModelMapper taskInfoModelMapper;

    @Autowired
    private TaskInfoModelEMapper taskInfoModelEMapper;

    @Override
    public List<TaskBucketInfoModel> getTaskList(TaskBucketInfoModel taskBucketInfoModel) {
        return taskBucketInfoModelEMapper.getTaskList(taskBucketInfoModel);
    }

    @Override
    public int getTaskCount(TaskBucketInfoModel taskBucketInfoModel) {
        return taskBucketInfoModelEMapper.getTaskCount(taskBucketInfoModel);
    }

    @Override
    public List<SysUserModel> queryUser(TaskBucketInfoModel taskBucketInfoModel) {
        return taskBucketInfoModelEMapper.queryUser(taskBucketInfoModel);
    }

    @Override
    public List<TaskBucketInfoModel> findByTypeAndUserId(TaskBucketInfoModel taskBucketInfoModel) {
        return taskBucketInfoModelEMapper.findByTypeAndUserId(taskBucketInfoModel);
    }

    @Override
    public List<TaskInfoModel> findByBuckId(Integer bucketId) {
        return taskBucketInfoModelEMapper.findByBuckId(bucketId);
    }

    @Override
    public void updateTasks(Integer sysUserId, Integer type, Integer sourceId) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("sysUserId", sysUserId);
        paramMap.put("deleted",0);
        paramMap.put("type", type);

        List<TaskBucketInfoModel> tbList = taskBucketInfoModelEMapper.selectListByParam(paramMap);

        //当前客服有权限审核头像审核类型任务
        if(!tbList.isEmpty()){
            TaskBucketInfoModel tb = tbList.get(0);
            tb.setTaskCount(tb.getTaskCount()-1);
            tb.setUpdateTime(DateUtil.now());
            tb.setVersion(tb.getVersion()+1);
            tb.setUpdateBy(SessionUtils.getSysUserSession().getId());
            taskBucketInfoModelMapper.update(tb);
            paramMap.clear();
            paramMap.put("bucketId",tb.getId());
            paramMap.put("type",type);
            paramMap.put("sourceId",sourceId);
            List<TaskInfoModel> tiList = taskInfoModelEMapper.selectListByParam(paramMap);

            if(!tiList.isEmpty()){
                TaskInfoModel ti = tiList.get(0);
                ti.setUpdateTime(DateUtil.now());
                ti.setUpdateBy(SessionUtils.getSysUserSession().getId());
                ti.setVersion(tb.getVersion()+1);
                ti.setDeleted(1);
                taskInfoModelMapper.update(ti);
            }
        }else{//当前客服无权限审核头像审核类型任务
            paramMap.clear();
            paramMap.put("type",type);
            paramMap.put("sourceId",sourceId);
            paramMap.put("deleted",0);
            List<TaskInfoModel> tiList = taskInfoModelEMapper.selectListByParam(paramMap);

            Integer bucketId = 0;
            if(!tiList.isEmpty()){
                TaskInfoModel ti = tiList.get(0);
                bucketId = ti.getBucketId();
                ti.setUpdateTime(DateUtil.now());
                ti.setVersion(ti.getVersion()+1);
                ti.setDeleted(1);
                ti.setUpdateBy(SessionUtils.getSysUserSession().getId());
                taskInfoModelMapper.update(ti);
            }
            if(!bucketId.equals(0)){
                TaskBucketInfoModel tb = taskBucketInfoModelEMapper.selectByPrimaryKey(bucketId);
                tb.setTaskCount(tb.getTaskCount()-1);
                tb.setUpdateTime(DateUtil.now());
                tb.setVersion(tb.getVersion()+1);
                tb.setUpdateBy(SessionUtils.getSysUserSession().getId());
                taskBucketInfoModelMapper.update(tb);
            }
        }
    }

    @Override
    public void deleteTaskBySysUserId(Integer sysUserId) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("sysUserId",sysUserId);
        paramMap.put("deleted", CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
        List<TaskBucketInfoModel> taskBucketInfoModels = taskBucketInfoModelEMapper.selectListByParam(paramMap);
        if(taskBucketInfoModels != null && taskBucketInfoModels.size()>0){
            for (TaskBucketInfoModel taskBucketInfoModel : taskBucketInfoModels) {
                paramMap.clear();
                paramMap.put("bucketId",taskBucketInfoModel.getId());
                paramMap.put("type",taskBucketInfoModel.getType());
                List<TaskInfoModel> taskInfoModels = taskInfoModelEMapper.selectListByParam(paramMap);
                if(taskInfoModels != null && taskBucketInfoModels.size()>0){
                    for (TaskInfoModel taskInfoModel : taskInfoModels) {
                        taskInfoModel.setDeleted(CommonConstant.DB_RECORD_DELETED_STATUS_TURE);
                        taskInfoModel.setUpdateTime(DateUtil.now());
                        taskInfoModel.setUpdateBy(SessionUtils.getSysUserSession().getId());
                        taskInfoModelMapper.update(taskInfoModel);
                    }
                }
                taskBucketInfoModel.setDeleted(CommonConstant.DB_RECORD_DELETED_STATUS_TURE);
                taskBucketInfoModel.setUpdateTime(DateUtil.now());
                taskBucketInfoModel.setUpdateBy(SessionUtils.getSysUserSession().getId());
                taskBucketInfoModelMapper.update(taskBucketInfoModel);
            }
        }
    }
}
