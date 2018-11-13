package com.topjet.manage.service;

import com.topjet.manage.domain.bean.ComplaintInfoBean;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.model.ComplaintInfoModel;


/**
 * @Author: zhaocj
 * @Description:用户投诉service
 * @Date: 2017-09-21 15:11
 */

public interface ComplaintService {

    PaginationBean listComplaint(ComplaintInfoModel model);

    ComplaintInfoBean getComplaintDetailById(Integer id);

    ComplaintInfoModel getComplaintById(Integer id);

}
