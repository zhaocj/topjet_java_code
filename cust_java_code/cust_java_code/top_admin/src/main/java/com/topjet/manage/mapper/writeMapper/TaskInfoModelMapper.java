package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.TaskInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TaskInfoModelMapper extends BaseMapper<TaskInfoModel> {

}