package com.topjet.manage.mapper.readMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-10-28 15:00
 */

@Mapper
public interface OrderBonusInfoModelEMapper {

    @Select("SELECT COUNT(id) FROM orderBonusInfo WHERE ownerId = #{userId} AND createTime BETWEEN #{startTime} AND #{endTime}")
    int countOwnerOrderBonusInfo(@Param("userId") Integer userId, @Param("startTime")Date startTime, @Param("endTime")Date endTime);

    @Select("SELECT COUNT(id) FROM orderBonusInfo WHERE driverId = #{userId} AND createTime BETWEEN #{startTime} AND #{endTime}")
    int countDriverOrderBonusInfo(@Param("userId") Integer userId, @Param("startTime")Date startTime, @Param("endTime")Date endTime);


    @Select("SELECT COUNT(id) FROM orderBonusInfo WHERE ownerId = #{ownerId} AND driverId = #{driverId} AND type = #{type} AND createTime BETWEEN #{startTime} AND #{endTime} AND deleted = 0")
    int countSameUserOrderBonusCountByToday1(@Param("ownerId") Integer ownerId,@Param("driverId") Integer driverId,@Param("type") Integer type, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    @Select("SELECT COUNT(id) FROM orderBonusInfo WHERE ownerId = #{driverId} AND driverId = #{ownerId} AND type = #{type} AND createTime BETWEEN #{startTime} AND #{endTime} AND deleted = 0")
    int countSameUserOrderBonusCountByToday2(@Param("ownerId") Integer ownerId,@Param("driverId") Integer driverId,@Param("type") Integer type, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    @Select("SELECT COUNT(id) FROM orderBonusInfo WHERE ownerId = #{ownerId} and type = #{type} AND deleted = 0")
    int selectOrderBonusRecord(@Param("type") Integer type, @Param("ownerId") Integer ownerId);


}
