package com.topjet.manage.controller;

import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.bean.UserCommentBean;
import com.topjet.manage.service.CommentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by liyj on 2017/11/3.
 */
@Controller
@RequestMapping("/comment/")
public class CommentController extends BaseController{

    @Autowired
    private CommentInfoService commentInfoService;

    @ResponseBody
    @RequestMapping("list")
    public Object list(UserCommentBean userCommentBean){
        PaginationBean bean = new PaginationBean();
        List<UserCommentBean> dataList = commentInfoService.getCommentList(userCommentBean);
        Integer total = commentInfoService.getCommentCount(userCommentBean);
        bean.setRows(dataList);
        bean.setTotal(total);
        return bean;
    }

    @RequestMapping("details")
    public Object edit(Integer id){
        Object result=commentInfoService.edit(id);
        ModelAndView mv = new ModelAndView("view/comment/commentDetail");
        if (result != null){
            mv.addObject("data",result);
        }
        return mv;
    }

    @ResponseBody
    @RequestMapping("update")
    public Object update(Integer id,Integer version,String content,String operatorRemark){
        return commentInfoService.update(id, version, content,operatorRemark);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("delete")
    public Object delete(Integer id){
        return commentInfoService.delete(id);
    }

}
