package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.UserInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
@Mapper
public interface UserInfoModelEMapper extends BaseEMapper<UserInfoModel> {

    List<UserInfoModel> selectListByParams(Map<String, Object> paramMap);

    @Select("SELECT * FROM userDB.userInfo WHERE deleted = 0 AND SUBSTR(idNo,11,4) = #{birthday} AND useStatus = 2 AND userAuthStatus = 3 AND id>#{id} LIMIT 100")
    List<UserInfoModel> selectBirthdayUser(@Param("birthday") String birthday,@Param("id") Integer id);

}