package com.topjet.manage.service;


import com.topjet.manage.domain.bean.SensitiveWordInfoBean;
import com.topjet.manage.domain.model.SensitiveWordInfoModel;

import java.util.List;

/**
 * Created by liyj on 2017/7/24.
 */
public interface SensitiveService {
    //显示敏感字列表
    public List<SensitiveWordInfoBean> getSensitiveWordList(SensitiveWordInfoBean sensitiveWordInfoBean);
    //敏感字页数
    public Integer getSensitiveWordCount(SensitiveWordInfoBean sensitiveWordInfoBean);
    //根据敏感字id查询
    public SensitiveWordInfoBean getSensitiveWordById(Integer id);
    //根据敏感字查询
    public List<SensitiveWordInfoBean> getSenWordByName(String name);
    //添加敏感字
    public Integer addSensitiveWord(SensitiveWordInfoBean sensitiveWordInfoBean);
    //修改敏感字
    public Integer updateSensiticeWord(SensitiveWordInfoBean sensitiveWordInfoBean);
}
