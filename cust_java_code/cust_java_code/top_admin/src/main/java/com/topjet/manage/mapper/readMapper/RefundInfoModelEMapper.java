package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.bean.RefundDetail2Bean;
import com.topjet.manage.domain.model.RefundInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface RefundInfoModelEMapper extends BaseEMapper<RefundInfoModel>{

    //根据传入条件获得所有符合条件的退款账单
    public List<RefundDetail2Bean> getRefundsList(RefundDetail2Bean refundDetail2Bean);

    //根据传入条件获得所有符合条件的退款账单数量
    public Integer getRefundsCount(RefundDetail2Bean refundDetail2Bean);
}