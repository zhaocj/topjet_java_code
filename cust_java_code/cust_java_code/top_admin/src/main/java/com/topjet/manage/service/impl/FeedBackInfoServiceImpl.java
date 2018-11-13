package com.topjet.manage.service.impl;

import com.topjet.manage.domain.bean.FeedbackInfoBean;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.mapper.readMapper.FeedbackInfoModelEMapper;
import com.topjet.manage.service.FeedBackInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-09-21 17:39
 */

@Service
@Transactional
public class FeedBackInfoServiceImpl implements FeedBackInfoService{

    @Autowired
    private FeedbackInfoModelEMapper feedbackInfoModelEMapper;

    @Override
    public PaginationBean listFeedBackInfo(FeedbackInfoBean feedbackInfoBean) {
        PaginationBean page = new PaginationBean();
        page.setRows(feedbackInfoModelEMapper.listFeedBackInfo(feedbackInfoBean));
        page.setTotal(feedbackInfoModelEMapper.countFeedBackInfo(feedbackInfoBean));
        return page;
    }
}
