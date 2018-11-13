package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.OrderListAdvertModel;

public interface OrderListAdvertModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderListAdvertModel record);

    int insertSelective(OrderListAdvertModel record);

    OrderListAdvertModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderListAdvertModel record);

    int updateByPrimaryKey(OrderListAdvertModel record);
}