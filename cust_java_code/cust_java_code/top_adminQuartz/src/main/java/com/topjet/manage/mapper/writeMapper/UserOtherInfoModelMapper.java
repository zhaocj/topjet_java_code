package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.UserOtherInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserOtherInfoModelMapper extends BaseMapper<UserOtherInfoModel> {
}