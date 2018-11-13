package com.topjet.manage.service;

import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.model.AppSlideModel;

import java.util.List;

/**
 * @Author: zhaocj
 * @Description:轮播图管理
 * @Date: 2017-12-04 15:08
 */

public interface AppSlideService {

    PaginationBean listAppSlide (AppSlideModel appSlideModel);

    Integer saveAppSlide(AppSlideModel appSlideModel);

    AppSlideModel getAppSlideModelById(Integer id);

    Integer updateAppSlide(AppSlideModel appSlideModel);
}
