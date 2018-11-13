package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.UserTrailModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserTrailModelMapper extends BaseMapper<UserTrailModel> {

}