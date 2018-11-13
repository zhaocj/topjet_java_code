package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.GoodsDetailInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-10-27 13:58
 */

@Mapper
public interface GoodsDetailInfoModelEMapper {

    @Select("SELECT * FROM orderDB.goodsDetailInfo WHERE goodsId = #{goodsId}")
    GoodsDetailInfoModel selectGoodsDetailByOrderId(@Param("goodsId") Integer goodsId);
}
