package com.topjet.manage.service.impl;

import com.topjet.manage.domain.bean.AdvertisementBean;
import com.topjet.manage.domain.model.AdvertisementInfoModel;
import com.topjet.manage.mapper.readMapper.AdvertisementInfoModelEMapper;
import com.topjet.manage.mapper.writeMapper.AdvertisementInfoModelMapper;
import com.topjet.manage.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyj on 2017/10/12.
 */
@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementInfoModelEMapper advertisementInfoModelEMapper;
    @Autowired
    private AdvertisementInfoModelMapper advertisementInfoModelMapper;

    @Override
    public List<AdvertisementBean> getAdvertisementBeanList(AdvertisementBean adc) {
        return advertisementInfoModelEMapper.getAdvertisementBeanList(adc);
    }

    @Override
    public Integer getAdvertisementCount(AdvertisementBean adc) {
        return advertisementInfoModelEMapper.getAdvertisementCount(adc);
    }

    @Override
    public Integer insertAdvertisement(AdvertisementInfoModel advertisementInfoModel) {
        return advertisementInfoModelMapper.insertAdvertisement(advertisementInfoModel);
    }

    @Override
    public Integer updateAdvertisement(AdvertisementInfoModel advertisementInfoModel) {
        return advertisementInfoModelMapper.updateAdvertisement(advertisementInfoModel);
    }

    @Override
    public AdvertisementInfoModel findAdvertisementById(Integer id) {
        return advertisementInfoModelEMapper.findAdvertisementById(id);
    }
}
