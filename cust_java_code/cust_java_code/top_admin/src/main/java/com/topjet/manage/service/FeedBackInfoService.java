package com.topjet.manage.service;

import com.topjet.manage.domain.bean.FeedbackInfoBean;
import com.topjet.manage.domain.bean.PaginationBean;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-09-21 17:38
 */

public interface FeedBackInfoService{

    /**
     * 意见反馈列表
     * @param feedbackInfoBean
     * @return
     */
    PaginationBean listFeedBackInfo(FeedbackInfoBean feedbackInfoBean);

}
