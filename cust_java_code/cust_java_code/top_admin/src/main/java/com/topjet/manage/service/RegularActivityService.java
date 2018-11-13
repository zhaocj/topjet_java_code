package com.topjet.manage.service;

import com.topjet.common.exception.TopjetErrorCodeException;
import com.topjet.manage.domain.bean.RegularActivitiesResponseBean;
import com.topjet.manage.domain.model.ActivityCityInfoModel;
import com.topjet.manage.domain.model.ActivityPageInfoModel;
import com.topjet.manage.domain.model.RegularActivityModel;

import java.util.List;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-11-08 14:51
 */

public interface RegularActivityService {

    List<RegularActivitiesResponseBean> listRegularActivity(Integer appType)throws TopjetErrorCodeException;

    int saveRegularActivity(RegularActivityModel regularActivityModel)throws TopjetErrorCodeException;

    void deleteActivityCity(Integer id);

    List<ActivityPageInfoModel> listShowPage(Integer regularActivityId)throws TopjetErrorCodeException;

    void updateOrInserShowPage(Integer id,Integer tid,Integer deleteflag) throws TopjetErrorCodeException;

    List<ActivityCityInfoModel> listActivityCity(Integer regularActivityId)throws TopjetErrorCodeException;

    void insertOrUpdateCity(String cityCode,Integer id,Integer tid) throws TopjetErrorCodeException;

    RegularActivitiesResponseBean queryRegularDetail(Integer id) throws  TopjetErrorCodeException;

    void updateRegularActivity(RegularActivityModel regularActivityModel);

    void deleteRegularActivity(Integer id);
}
