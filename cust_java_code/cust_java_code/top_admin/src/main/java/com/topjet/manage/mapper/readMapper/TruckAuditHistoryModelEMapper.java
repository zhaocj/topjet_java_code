package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.TruckAuditHistoryModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TruckAuditHistoryModelEMapper extends BaseEMapper<TruckAuditHistoryModel> {

    /**
     * 根据sourceId查询 车辆日志
     */
    @Select("SELECT th.id,th.sourceType,th.sourceId,th.statusAfter,th.createBy,th.createTime,th.auditName,th.remark,th.deleted\n" +
            "from manageDB.truckAuditHistory th where th.sourceType = 2 and th.statusAfter = 3 and th.sourceId = ${sourceId} and th.id = (SELECT MAX(id) from  manageDB.truckAuditHistory)")
    public TruckAuditHistoryModel getAuditHistoryRemark(@Param("sourceId") Integer sourceId);
}