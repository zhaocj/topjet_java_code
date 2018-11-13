package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.vo.TruckListQuery;
import com.topjet.manage.domain.vo.TruckListVO;
import com.topjet.manage.domain.model.DriverTruckInfoModel;
import com.topjet.manage.domain.model.TruckInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TruckInfoModelEMapper extends BaseEMapper<TruckInfoModelEMapper>{
    /**
     * 车辆管理列表
     */
    public List<TruckListVO> getTruckList(TruckListQuery truckListQuery);
    /**
     * 车辆列表页数
     */
    public Integer getTruckCount(TruckListQuery truckListQuery);
    /***
     * 根据车牌号查询车辆信息
     */
    public List<TruckInfoModel> getTruckInfoByPlatNo(TruckInfoModel truckInfoModel);
    /**
     *根据车牌id查询车辆信息
     */
    public TruckInfoModel getTruckById(Integer id);
    /**
     * 司机车辆信息表DriverTruckInfoModel 根据truckInfo表中的id查询数据
     */
    public List<DriverTruckInfoModel> findTruckInfoById(Integer truckId);


}