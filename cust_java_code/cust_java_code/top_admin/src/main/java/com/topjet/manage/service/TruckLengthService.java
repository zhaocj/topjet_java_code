package com.topjet.manage.service;

import com.topjet.manage.domain.model.TruckLengthDictionaryModel;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by liyj on 2017/9/14.
 */
public interface TruckLengthService {
    /**
     * 分页查询所有车长
     * @param truckTypeDictionaryModel
     * @return
     */
    public List<TruckLengthDictionaryModel> getTruckLengthList(TruckLengthDictionaryModel truckTypeDictionaryModel);
    /**
     * 所有车长页数
     */
    public Integer queryTruckLengthCount(TruckLengthDictionaryModel truckTypeDictionaryModel);
    /**
     * 根据车长查询
     */
    public List<TruckLengthDictionaryModel> findTruckLengthByLength(BigDecimal length);
    /**
     * 根据车长id查询
     */
    public TruckLengthDictionaryModel findTruckLengthById(Integer id);

    public List<TruckLengthDictionaryModel> getTruckLength(TruckLengthDictionaryModel truckTypeDictionaryModel);
}
