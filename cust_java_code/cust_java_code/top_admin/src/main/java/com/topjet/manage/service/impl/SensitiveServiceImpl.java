package com.topjet.manage.service.impl;

import com.topjet.manage.domain.bean.SensitiveWordInfoBean;
import com.topjet.manage.domain.model.SensitiveWordInfoModel;
import com.topjet.manage.mapper.readMapper.SensitiveWordInfoModelEMapper;
import com.topjet.manage.mapper.writeMapper.SensitiveWordInfoModelMapper;
import com.topjet.manage.service.SensitiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyj on 2017/7/24.
 */
@Service
public class SensitiveServiceImpl implements SensitiveService {
    @Autowired
    private SensitiveWordInfoModelEMapper sensitiveWordInfoModelEMapper;
    @Autowired
    private SensitiveWordInfoModelMapper sensitiveWordInfoModelMapper;


    @Override
    public List<SensitiveWordInfoBean> getSensitiveWordList(SensitiveWordInfoBean sensitiveWordInfoBean) {
        return sensitiveWordInfoModelEMapper.getSensitiveWordList(sensitiveWordInfoBean);
    }

    @Override
    public Integer getSensitiveWordCount(SensitiveWordInfoBean sensitiveWordInfoBean) {
        return sensitiveWordInfoModelEMapper.getSensitiveWordCount(sensitiveWordInfoBean);
    }

    @Override
    public SensitiveWordInfoBean getSensitiveWordById(Integer id) {
        return sensitiveWordInfoModelEMapper.getSensitiveWordById(id);
    }

    @Override
    public Integer addSensitiveWord(SensitiveWordInfoBean sensitiveWordInfoBean) {
        return sensitiveWordInfoModelMapper.addSensitiveWord(sensitiveWordInfoBean);
    }

    @Override
    public Integer updateSensiticeWord(SensitiveWordInfoBean sensitiveWordInfoBean) {
        return sensitiveWordInfoModelMapper.updateSensiticeWord(sensitiveWordInfoBean);
    }

    @Override
    public List<SensitiveWordInfoBean> getSenWordByName(String name) {
        return sensitiveWordInfoModelEMapper.getSenWordByName(name);
    }
}
