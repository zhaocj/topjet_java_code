package com.topjet.manage.service;

import com.topjet.manage.domain.model.SysUserModel;
import com.topjet.manage.domain.model.TaskBucketInfoModel;
import com.topjet.manage.domain.model.TaskInfoModel;

import java.util.List;

/**
 * Created by liyj on 2017/8/10.
 */
public interface TaskAssignService {

    /**
     * 查询任务列表
     */
    public List<TaskBucketInfoModel> getTaskList(TaskBucketInfoModel taskBucketInfoModel);
    /**
     * 任务列表页数
     */
    public int getTaskCount(TaskBucketInfoModel taskBucketInfoModel);
    /**
     * 查询所有SysUser
     *
     */
    public List<SysUserModel> queryUser(TaskBucketInfoModel taskBucketInfoModel);
    /**
     * 根据任务类型，用户id查询
     */
    public List<TaskBucketInfoModel> findByTypeAndUserId(TaskBucketInfoModel taskBucketInfoModel);
    /**
     * 根据buckId查询
     */
    public List<TaskInfoModel> findByBuckId(Integer bucketId);


    /**
     * 处理完任务后其更新task
     * @return
     */
    public void updateTasks(Integer sysUserId,Integer type,Integer SoruceId);

    void deleteTaskBySysUserId(Integer sysUserId);



}
