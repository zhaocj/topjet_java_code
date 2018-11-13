package com.topjet.manage.service;

import com.topjet.manage.domain.bean.UserCommentBean;
import com.topjet.manage.domain.model.UserCommentInfoModel;

import java.util.List;

/**
 * Created by liyj on 2017/11/3.
 */
public interface CommentInfoService {
    /**
     * 查看全部评价信息分页
     */
    public List<UserCommentBean> getCommentList(UserCommentBean userCommentBean);
    /**
     * 评价信息页数
     */
    public Integer getCommentCount(UserCommentBean userCommentBean);
    /**
     * 根据id查询
     */
    public UserCommentInfoModel findCommentById(Integer id);
    public List<UserCommentInfoModel> findById(Integer id);
    /**
     * 根据id获取编辑信息
     * @param id
     * @return
     */
    public Object edit(Integer id);
    /**
     * 根据id更新
     */
    public Object update(Integer id,Integer version,String content,String operatorRemark);
    /**
     * 根据id删除
     * @param id
     * @return
     */
    public Object delete(Integer id);

}
