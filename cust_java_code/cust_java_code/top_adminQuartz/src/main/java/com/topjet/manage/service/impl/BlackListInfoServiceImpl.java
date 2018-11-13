package com.topjet.manage.service.impl;

import com.topjet.manage.mapper.readMapper.BlackListInfoModelEMapper;
import com.topjet.manage.service.BlackListInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-09-04 17:32
 */

@Service
public class BlackListInfoServiceImpl implements BlackListInfoService {

    @Autowired
    private BlackListInfoModelEMapper blackListInfoModelMapper;


    @Override
    public boolean isBlackByType(Integer userId, String type) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("deleted",0);
        paramMap.put("userId",userId);
        paramMap.put("type",type);
        Integer countByParam = blackListInfoModelMapper.getCountByParam(paramMap);

        if (countByParam > 0) {
            return true;
        }
        return false;
    }
}
