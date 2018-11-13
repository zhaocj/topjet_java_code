package com.topjet.manage.service;

import com.topjet.manage.domain.bean.AdvertisementBean;
import com.topjet.manage.domain.model.AdvertisementInfoModel;

import java.util.List;

/**
 * Created by liyj on 2017/10/12.
 */
public interface AdvertisementService {

    /**
     * 广告管理分页查询
     * @param adc
     * @return
     */
    public List<AdvertisementBean> getAdvertisementBeanList(AdvertisementBean adc);

    /**
     * 广告管理页数
     * @param adc
     * @return
     */
    public Integer getAdvertisementCount(AdvertisementBean adc);

    /**
     *添加广告
     */
    public Integer insertAdvertisement(AdvertisementInfoModel advertisementInfoModel);

    /**
     * 根据id查询广告
     */
    public AdvertisementInfoModel findAdvertisementById(Integer id);

    /**
     * 修改广告
     */
    public Integer updateAdvertisement(AdvertisementInfoModel advertisementInfoModel);

}
