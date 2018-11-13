package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.OrderAuditHistoryModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import javax.annotation.security.PermitAll;
import java.util.List;

@Mapper
public interface OrderAuditHistoryModelEMapper extends BaseEMapper<OrderAuditHistoryModel> {

    /**
     * 根据sourceID sourceType获取日志
     */
    public List<OrderAuditHistoryModel> getOperationLog(OrderAuditHistoryModel orderAuditHistoryModel);

    @Select("SELECT * FROM orderAuditHistory WHERE sourceId = #{sourceId} AND deleted = 0 AND sourceType in(3,4) ORDER BY id desc")
    List<OrderAuditHistoryModel> listOrderAuditHistory(@Param("sourceId") Integer sourceId);

}