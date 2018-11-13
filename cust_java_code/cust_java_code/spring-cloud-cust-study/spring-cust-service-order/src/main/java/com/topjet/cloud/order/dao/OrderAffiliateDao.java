package com.topjet.cloud.order.dao;

import com.topjet.cloud.order.dao.model.OrderAffiliateModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by hongtaoer-win on 2017/8/30.
 * 订单附属表
 */
@Mapper
public interface OrderAffiliateDao {

    /**
     * 查询订单冻结状态
     * @param orderId
     * @return
     */
    @Select("SELECT id,orderFrozenState FROM orderAffiliate WHERE orderId = ${orderId}")
    OrderAffiliateModel selectOrderById(@Param("orderId") Integer orderId);

    /**
     * 第一次冻结订单
     * @param orderAffiliateModel
     * @return
     */
    @Update("INSERT INTO  orderAffiliate(orderId,orderFrozenState,createBy,createTime) VALUES(#{orderId},#{orderFrozenState},#{createBy},#{createTime})")
    Integer fristFrostOrder(OrderAffiliateModel orderAffiliateModel);


    /**
     * 冻结订单
     * @param orderAffiliateModel
     * @return
     */
    @Update("UPDATE orderAffiliate SET  orderFrozenState = 1 , updateBy = #{updateBy} , updateTime = #{updateTime}  WHERE id = ${id} AND  deleted = 0")
    Integer frostOrder(OrderAffiliateModel orderAffiliateModel);


    /**
     * 解除订单冻结
     * @param orderAffiliateModel
     * @return
     */
    @Update("UPDATE orderAffiliate SET  orderFrozenState = 0 , updateBy = ${updateBy} , updateTime = #{updateTime}  WHERE id = ${id} AND  deleted = 0")
    Integer unfreezeOrder(OrderAffiliateModel orderAffiliateModel);


    class OrderAffiliateDaoClass{
    }
}
