package com.topjet.manage.service;

import com.topjet.manage.domain.vo.TruckListQuery;
import com.topjet.manage.domain.vo.TruckListVO;
import com.topjet.manage.domain.model.DriverTruckInfoModel;
import com.topjet.manage.domain.model.TruckAuditHistoryModel;
import com.topjet.manage.domain.model.TruckInfoModel;

import java.util.List;

/**
 * Created by liyj on 2017/9/11.
 */
public interface TruckService {
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
    /**
     * 车辆管理编辑
     */
    public Object edit(Integer id);
    /**
     * 根据sourceId查询 车辆日志
     */
    public TruckAuditHistoryModel getAuditHistoryRemark(Integer sourceId);
   /* *
     * 修改车辆信息
     */
    public Object updateTruckInfo(Integer id, String plateNo1,String plateNo2,String plateNo3, Integer truckColor, Integer truckType,Integer truckLength,String operatorRemark);
}
