package com.topjet.manage.service.impl;

import com.topjet.manage.domain.bean.HelpCenterBean;
import com.topjet.manage.domain.model.HelpCategoryModel;
import com.topjet.manage.domain.model.HelpQuestionModel;
import com.topjet.manage.mapper.readMapper.HelpCategoryModelEMapper;
import com.topjet.manage.mapper.writeMapper.HelpCategoryModelMapper;
import com.topjet.manage.service.HelpCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyj on 2017/12/6.
 */
@Service
public class HelpCenterServiceImpl implements HelpCenterService{

    @Autowired
    private HelpCategoryModelEMapper helpCategoryModelEMapper;
    @Autowired
    private HelpCategoryModelMapper helpCategoryModelMapper;

    @Override
    public List<HelpCenterBean> getHelpCategoryList(HelpCenterBean helpCenterBean) {
        return helpCategoryModelEMapper.getHelpCategoryList(helpCenterBean);
    }

    @Override
    public Integer getHelpCategoryCount(HelpCenterBean helpCenterBean) {
        return helpCategoryModelEMapper.getHelpCategoryCount(helpCenterBean);
    }

    @Override
    public Integer insertQuestion(HelpCenterBean helpCenterBean) {
        return helpCategoryModelMapper.insertQuestion(helpCenterBean);
    }

    @Override
    public Integer updateQuestion(HelpCenterBean helpCenterBean) {
        return helpCategoryModelMapper.updateQuestion(helpCenterBean);
    }

    @Override
    public Integer insertQuestionContent(HelpCenterBean helpCenterBean) {
        return helpCategoryModelMapper.insertQuestionContent(helpCenterBean);
    }

    @Override
    public Integer updateQuestionContent(HelpCenterBean helpCenterBean) {
        return helpCategoryModelMapper.updateQuestionContent(helpCenterBean);
    }

    @Override
    public HelpCategoryModel findCategoryByhelpCategoryID(Integer helpCategoryID) {
        return helpCategoryModelEMapper.findCategoryByhelpCategoryID(helpCategoryID);
    }

    @Override
    public HelpQuestionModel findQuestionByhelpQuestionID(Integer helpQuestionID) {
        return helpCategoryModelEMapper.findQuestionByhelpQuestionID(helpQuestionID);
    }

    @Override
    public Integer deleteQuestion(Integer helpQuestionID) {
        return helpCategoryModelMapper.deleteQuestion(helpQuestionID);
    }

    @Override
    public HelpCategoryModel findByNameAndVersion(String name, Integer version) {
        return helpCategoryModelEMapper.findByNameAndVersion(name,version);
    }

    @Override
    public Integer update(Integer sortNo, Integer helpQuestionID) {
        return helpCategoryModelMapper.update(sortNo,helpQuestionID);
    }
}
