package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.BlackListInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-10-23 11:25
 */

@Mapper
public interface BlackListInfoModelEMapper {

    @Select("SELECT * FROM blackListInfo WHERE userId = #{userId} AND deleted = 0")
    BlackListInfoModel selectBlackListInfoByUserId(@Param("userId") Integer userId);

    @Select("SELECT * FROM blackListInfo WHERE userId = #{userId} AND type = #{type} AND deleted = 0")
    BlackListInfoModel selectBlackListInfoByCretirea(@Param("userId") Integer userId ,@Param("type") Integer type);
}
