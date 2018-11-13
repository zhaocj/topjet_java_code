package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.SysUserModel;
import com.topjet.manage.domain.model.TaskBucketInfoModel;
import com.topjet.manage.domain.model.TaskInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TaskBucketInfoModelEMapper extends BaseEMapper<TaskBucketInfoModel> {
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
}