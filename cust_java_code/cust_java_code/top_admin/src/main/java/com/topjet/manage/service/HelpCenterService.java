package com.topjet.manage.service;

import com.sun.mail.imap.protocol.INTERNALDATE;
import com.topjet.manage.domain.bean.HelpCenterBean;
import com.topjet.manage.domain.model.HelpCategoryModel;
import com.topjet.manage.domain.model.HelpQuestionModel;

import java.util.List;

/**
 * Created by liyj on 2017/12/6.
 */
public interface HelpCenterService {
    /**
     * 查询所有分类问题
     */
    public List<HelpCenterBean> getHelpCategoryList(HelpCenterBean helpCategoryModel);
   /* *
     * 所有分类问题的页数
     */
    public Integer getHelpCategoryCount(HelpCenterBean helpCategoryModel);
    /**
     * 根据主表id查询主表信息
     */
    public HelpCategoryModel findCategoryByhelpCategoryID(Integer helpCategoryID);
    /**
     * 根据附表id查询附表信息
     */
    public HelpQuestionModel findQuestionByhelpQuestionID(Integer helpQuestionID);
    /**
     * 主表添加问题类型
     */
    public Integer insertQuestion(HelpCenterBean helpCenterBean);
    /**
     * 主表修改问题类型
     */
    public Integer updateQuestion(HelpCenterBean helpCenterBean);
    /**
     * 附表添加问题
     */
    public Integer insertQuestionContent(HelpCenterBean helpCenterBean);
    /**
     * 附表修改问题
     */
    public Integer updateQuestionContent(HelpCenterBean helpCenterBean);
    /**
     * 附表修改问题
     */
    public Integer update(Integer sortNo,Integer helpQuestionID);
   /**
    * 删除问题附表
    */
   public Integer deleteQuestion(Integer helpQuestionID);
    /**
     * 根据版本类型，问题类型查询数据
     */
    public HelpCategoryModel findByNameAndVersion(String name,Integer version);
}
