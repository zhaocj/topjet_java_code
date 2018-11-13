package com.topjet.manage.service;

import com.topjet.manage.domain.bean.ScoreNewSettingBean;
import com.topjet.manage.domain.model.ScoreNewSettingModel;

import java.util.List;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-10-13 10:21
 */


public interface ScoreNewSettingService{

    List<ScoreNewSettingBean> listScoreNewSetting(Integer deleted);

    List<ScoreNewSettingBean> listScoreNewSettings(Integer deleted,Integer scoreType);

    void updateScoreNewSetting(ScoreNewSettingModel scoreNewSettingModel);

    ScoreNewSettingModel selectScoreNewSettingById(Integer id);

    Integer insertSelective(ScoreNewSettingModel scoreNewSettingModel);
}
