package com.topjet.manage.service.impl;

import com.topjet.manage.domain.bean.ScoreNewSettingBean;
import com.topjet.manage.domain.model.ScoreNewSettingModel;
import com.topjet.manage.mapper.readMapper.ScoreNewSettingModelEMapper;
import com.topjet.manage.mapper.writeMapper.ScoreNewSettingModelMapper;
import com.topjet.manage.service.ScoreNewSettingService;
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
 * @Date: 2017-10-13 10:23
 */

@Service
@Transactional
public class ScoreNewSettingServiceImpl implements ScoreNewSettingService{
    private static Logger log = LoggerFactory.getLogger(ScoreNewSettingServiceImpl.class);

    @Autowired
    private ScoreNewSettingModelEMapper scoreNewSettingModelEMapper;

    @Autowired
    private ScoreNewSettingModelMapper scoreNewSettingModelMapper;

    @Override
    public List<ScoreNewSettingBean> listScoreNewSetting(Integer deleted) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("deleted", deleted);
        return scoreNewSettingModelEMapper.selectScoreSettingList(paramMap);
    }

    @Override
    public List<ScoreNewSettingBean> listScoreNewSettings(Integer deleted, Integer scoreType) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("deleted", deleted);
        paramMap.put("scoreType",scoreType);
        return scoreNewSettingModelEMapper.selectScoreSettingList(paramMap);
    }

    @Override
    public void updateScoreNewSetting(ScoreNewSettingModel scoreNewSettingModel) {
        scoreNewSettingModelMapper.updateScoreNewSetting(scoreNewSettingModel);
    }

    @Override
    public ScoreNewSettingModel selectScoreNewSettingById(Integer id) {
        return scoreNewSettingModelEMapper.selectScoreNewSettingByPrimaryKey(id);
    }

    @Override
    public Integer insertSelective(ScoreNewSettingModel scoreNewSettingModel) {
        return scoreNewSettingModelMapper.insertSelective(scoreNewSettingModel);
    }
}
