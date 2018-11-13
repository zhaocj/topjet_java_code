package com.topjet.manage.service.impl;

import com.topjet.manage.constants.CommonConstant;
import com.topjet.manage.domain.model.PhotoInfoModel;
import com.topjet.manage.mapper.readMapper.PhotoInfoModelEMapper;
import com.topjet.manage.mapper.writeMapper.PhotoInfoModelMapper;
import com.topjet.manage.service.PhotoInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-08-06 14:27
 */

@Service
@Transactional
public class PhotoInfoServiceImpl implements PhotoInfoService{

    private static final Logger LOG = LoggerFactory.getLogger(PhotoInfoServiceImpl.class);

    @Autowired
    private PhotoInfoModelEMapper photoInfoModelMapper;
    @Autowired
    private PhotoInfoModelMapper photoInfoMapper;


    @Override
    public List<PhotoInfoModel> getPhotosByUserId(Integer userId) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("userId",userId);

        return photoInfoModelMapper.selectListByParam(paramMap);
    }

    @Override
    public String getUserPhotoInfo(Integer userId, Integer type) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("userId",userId);
        paramMap.put("type",type);
        paramMap.put("deleted", CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
        List<PhotoInfoModel> photoInfoModels = photoInfoModelMapper.selectListByParam(paramMap);
        if(photoInfoModels != null && photoInfoModels.size()>0){
            return photoInfoModels.get(0).getUrl();
        }else{
            return "";
        }
    }

    @Override
    public List<PhotoInfoModel> getPhotoList(Integer userId) {
        return photoInfoModelMapper.getPhotoList(userId);
    }

    @Override
    public Integer updatePhoto(PhotoInfoModel photoInfoModel) {
        return photoInfoMapper.updatePhoto(photoInfoModel);
    }
}
