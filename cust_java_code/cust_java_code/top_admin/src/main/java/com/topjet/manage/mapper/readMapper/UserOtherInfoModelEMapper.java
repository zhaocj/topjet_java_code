package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.UserOtherInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserOtherInfoModelEMapper extends BaseEMapper<UserOtherInfoModel> {

    //根据user id查询
    public UserOtherInfoModel findUserOtherById(Integer userId);
}