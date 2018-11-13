package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.OrderAffiliateModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderAffiliateModelEMapper extends BaseEMapper<OrderAffiliateModel> {
    /**
     * 根据orderId 查询
     */
    public List<OrderAffiliateModel> getOrderAffiliateList(Integer orderId);
}