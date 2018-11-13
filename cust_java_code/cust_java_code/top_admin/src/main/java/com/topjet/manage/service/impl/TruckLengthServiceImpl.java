package com.topjet.manage.service.impl;

import com.topjet.manage.domain.model.TruckLengthDictionaryModel;
import com.topjet.manage.mapper.readMapper.TruckLengthDictionaryModelEMapper;
import com.topjet.manage.service.TruckLengthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by liyj on 2017/9/14.
 */
@Service
public class TruckLengthServiceImpl implements TruckLengthService{

    @Autowired
    private TruckLengthDictionaryModelEMapper truckLengthDictionaryModelEMapper;

    @Override
    public List<TruckLengthDictionaryModel> getTruckLengthList(TruckLengthDictionaryModel truckTypeDictionaryModel) {
        return truckLengthDictionaryModelEMapper.getTruckLengthList(truckTypeDictionaryModel);
    }

    @Override
    public Integer queryTruckLengthCount(TruckLengthDictionaryModel truckTypeDictionaryModel) {
        return truckLengthDictionaryModelEMapper.queryTruckLengthCount(truckTypeDictionaryModel);
    }

    @Override
    public List<TruckLengthDictionaryModel> findTruckLengthByLength(BigDecimal length) {
        return truckLengthDictionaryModelEMapper.findTruckLengthByLength(length);
    }

    @Override
    public TruckLengthDictionaryModel findTruckLengthById(Integer id) {
        return truckLengthDictionaryModelEMapper.findTruckLengthById(id);
    }

    @Override
    public List<TruckLengthDictionaryModel> getTruckLength(TruckLengthDictionaryModel truckTypeDictionaryModel) {
        return truckLengthDictionaryModelEMapper.getTruckLength(truckTypeDictionaryModel);
    }
}
