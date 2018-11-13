package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.OrderInfoModel;
import com.topjet.manage.domain.model.OrderParameterInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-10-28 14:09
 */

@Mapper
public interface OrderInfoModelEMapper {

    @Select("SELECT * FROM orderDB.orderInfo where id = #{orderNo}")
    OrderInfoModel selectOrderByOrderNo(@Param("orderNo") Integer orderNo);

    @Select("SELECT * FROM orderDB.orderParameterInfo WHERE orderId = #{orderId} AND parameterType = #{parameterType} AND deleted = 0")
    List<OrderParameterInfoModel> selectOrderParameterInfoByOrderNo(@Param("orderId") Integer orderId, @Param("parameterType") Integer parameterType);



}
