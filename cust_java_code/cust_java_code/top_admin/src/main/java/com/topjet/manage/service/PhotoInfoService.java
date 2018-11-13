package com.topjet.manage.service;

import com.topjet.manage.domain.model.PhotoInfoModel;

import java.util.List;

/**
 * @Author: zhaocj
 * @Description:图片service
 * @Date: 2017-08-06 14:26
 */

public interface PhotoInfoService {

    public List<PhotoInfoModel> getPhotosByUserId(Integer userId);

    String getUserPhotoInfo(Integer userId,Integer type);

    public List<PhotoInfoModel> getPhotoList(Integer userId);

    /**
     * 修改头像
     */
    public Integer updatePhoto(PhotoInfoModel photoInfoModel);

}
