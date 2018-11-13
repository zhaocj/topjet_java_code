package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.TaskInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TaskInfoModelEMapper extends BaseEMapper<TaskInfoModel> {

}