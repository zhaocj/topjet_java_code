package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.bean.OrderBonusBean;
import com.topjet.manage.domain.model.OrderBonusInfoModel;
import com.topjet.manage.domain.vo.OrderBonusListQuery;
import com.topjet.manage.domain.vo.OrderBonusListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderBonusInfoModelEMapper extends BaseEMapper<OrderBonusInfoModel> {

    /**
     * 查询运费补贴列表
     * @param query
     * @return
     */
    List<OrderBonusListVO> listOrderBonusInfo(OrderBonusListQuery query);

    /**
     * 查询运费补贴数据
     * @param query
     * @return
     */
    Integer countOrderBonusInfo(OrderBonusListQuery query);

    /**
     * 查询待审核运费补贴列表
     * @param orderBonusBean
     * @return
     */
    List<OrderBonusBean> listAuditOrderBonusInfo(OrderBonusBean orderBonusBean);

    /**
     * 查询待审核运费补贴数量
     * @param orderBonusBean
     * @return
     */
    int countAuditOrderBonusInfo(OrderBonusBean orderBonusBean);

    @Select("SELECT * FROM orderBonusInfo WHERE id = #{id} AND deleted = #{deleted} AND version = #{version}")
    OrderBonusInfoModel selectOrderBonusInfoById(@Param("id")Integer id, @Param("deleted")Integer deleted, @Param("version")Integer version);



}