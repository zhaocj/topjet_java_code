package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.GoodsDetailInfoModel;
import com.topjet.manage.domain.model.GoodsInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GoodsInfoModelEMapper extends BaseEMapper<GoodsInfoModel> {

    //根据货物id查询
    @Select("SELECT g.* from orderDB.goodsInfo g WHERE g.id = #{goodId} ")
    public GoodsInfoModel findGoodInfoById(@Param("goodId") Integer goodId);
    @Select("SELECT g.* from orderDB.goodsDetailInfo g WHERE g.goodsId = #{id} ")
    public GoodsDetailInfoModel findGoodDetailById(@Param("id") Integer id);



}