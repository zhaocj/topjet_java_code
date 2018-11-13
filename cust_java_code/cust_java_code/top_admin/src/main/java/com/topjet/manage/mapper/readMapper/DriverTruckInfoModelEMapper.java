package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.bean.MatchCenterDriverBean;
import com.topjet.manage.domain.model.DriverTruckInfoModel;
import com.topjet.manage.domain.model.TruckInfoModel;
import com.topjet.manage.domain.vo.TruckListVerifyQuery;
import com.topjet.manage.domain.vo.TruckListVerifyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DriverTruckInfoModelEMapper extends BaseEMapper<DriverTruckInfoModel>{
    /**
     * 管理平台匹配中心 司机位置查询
     */
    public List<MatchCenterDriverBean> getDriverOptionList(MatchCenterDriverBean matchCenterDriverBean);
    /**
     * 司机位置查询页数
     */
    public int getDriverOptionCount(MatchCenterDriverBean matchCenterDriverBean);

    /**
     * 获取待审核车辆列表
     */
    List<TruckListVerifyVO> listTruckVerifyList(TruckListVerifyQuery query);

    Integer countTruckVerifyList(TruckListVerifyQuery query);


    @Select("select 1 as id,count(dr.id) as orderCount from truckDB.driverTruckInfo dr,truckDB.truckInfo tr\n" +
            "\t\t\twhere dr.truckId = tr.id and dr.auditStatus=2 and dr.createTime>date_format(now(),'%Y-%m') and tr.plateNo1=#{plateNo1} and tr.plateNo2=#{plateNo2} and tr.plateNo3=#{plateNo3}\n" +
            "\t\tUNION\n" +
            "\t\tselect 2 as id,count(dr.id) as orderCount from truckDB.driverTruckInfo dr,truckDB.truckInfo tr\n" +
            "\t\t\twhere dr.truckId = tr.id and dr.auditStatus=2 and tr.plateNo1=#{plateNo1} and tr.plateNo2=#{plateNo2} and tr.plateNo3=#{plateNo3}")
    List<DriverTruckInfoModel> countAuditPlateNo(@Param("plateNo1") String plateNo1, @Param("plateNo2") String plateNo2, @Param("plateNo3") String plateNo3);
}