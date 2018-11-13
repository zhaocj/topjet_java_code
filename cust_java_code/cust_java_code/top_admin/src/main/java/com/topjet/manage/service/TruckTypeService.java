package com.topjet.manage.service;

import com.topjet.manage.domain.bean.TruckType;
import com.topjet.manage.domain.model.ResourceFileVersionInfoModel;
import com.topjet.manage.domain.model.TruckTypeDictionaryModel;

import java.util.List;

/**
 * Created by liyj on 2017/9/14.
 */
public interface TruckTypeService {
    /**
     * 分页查询所有车型
     */
    public List<TruckTypeDictionaryModel> getTruckTypeList(TruckTypeDictionaryModel truckTypeDictionaryModel);
    /**
     * 所有车型页数
     */
    public Integer queryTruckTypeCount(TruckTypeDictionaryModel truckTypeDictionaryModel);
    /**
     * 根据车型名称查询
     */
    public List<TruckTypeDictionaryModel> findTruckTypeByName(String displayName);
    /**
     * 根据车型名称查询
     */
    public List<TruckTypeDictionaryModel> findTruckTypeByCode(String code);
    /**
     * 根据车型id查询
     */
    public TruckTypeDictionaryModel findTruckTypeById(Integer id);

    public List<TruckTypeDictionaryModel> getTruckType(TruckTypeDictionaryModel truckTypeDictionaryModel);

    //更新资源配置文件
   public void updateResourceFileVersionInfo(ResourceFileVersionInfoModel rfvModel) throws Exception;
    /** 获取车型数据*/
    List<TruckType> getTruckType();


}
