package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.BlackListInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BlackListInfoModelEMapper extends BaseEMapper<BlackListInfoModel> {

    @Select("SELECT * FROM blackListInfo WHERE userId = #{userId} AND deleted = 0")
    BlackListInfoModel selectBlackListInfoByUserId(@Param("userId") Integer userId);

    @Select("SELECT * FROM blackListInfo WHERE userId = #{userId} AND type = #{type} AND deleted = 0")
    BlackListInfoModel selectBlackListInfoByCretirea(@Param("userId") Integer userId ,@Param("type") Integer type);

}