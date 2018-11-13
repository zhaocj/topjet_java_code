package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.SysUserModel;
import com.topjet.manage.domain.model.TaskBucketInfoModel;
import com.topjet.manage.domain.model.TaskInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TaskBucketInfoModelMapper extends BaseMapper<TaskBucketInfoModel> {
}