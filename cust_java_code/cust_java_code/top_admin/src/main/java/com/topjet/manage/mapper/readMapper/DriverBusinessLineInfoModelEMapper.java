package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.DriverBusinessLineInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface DriverBusinessLineInfoModelEMapper extends BaseEMapper<DriverBusinessLineInfoModel> {
    /**
     * 根据driverId查询司机常跑路线
     */
    @Select("select  id, driverId, businessLine1, businessLine2, businessLine3, businessLineCityId, createTime,updateTime, deleted from truckDB.driverBusinessLineInfo where driverId = #{id}")
    public DriverBusinessLineInfoModel getDriverBusinessLineById(@Param("id") Integer id);
}