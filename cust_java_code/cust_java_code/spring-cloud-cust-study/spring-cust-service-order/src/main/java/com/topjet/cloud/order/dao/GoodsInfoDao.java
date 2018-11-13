package com.topjet.cloud.order.dao;

import com.topjet.cloud.order.dao.model.GoodsInfoModel;
import org.apache.ibatis.annotations.*;


/**
 * Created by zhangn on 2017/8/24.
 */
@Mapper
public interface GoodsInfoDao {

    /**
     * 查询订单是否被隐藏
     * @param id
     * @return
     */
    @Select("SELECT id , isHidden FROM goodsInfo WHERE id = ${id}")
    GoodsInfoModel selectGoodInfoModeById(@Param("id") Integer id);

    /**
     * 货源隐藏或者显示
     * @param model
     * @return
     */
    @Update("UPDATE goodsInfo SET isHidden = ${isHidden} , version = version+1  WHERE id = ${id}")
    Integer updateHiddenStatus(GoodsInfoModel model);
}
