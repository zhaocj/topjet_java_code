package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.bean.FeedbackInfoBean;
import com.topjet.manage.domain.model.FeedbackInfoModel;

import java.util.List;

public interface FeedbackInfoModelEMapper extends BaseEMapper<FeedbackInfoModel>{

    List<FeedbackInfoBean> listFeedBackInfo(FeedbackInfoBean feedbackInfoBean);

    int countFeedBackInfo(FeedbackInfoBean feedbackInfoBean);

}