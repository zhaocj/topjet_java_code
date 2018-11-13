package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.bean.ComplaintInfoBean;
import com.topjet.manage.domain.model.ComplaintInfoModel;

import java.util.List;

public interface ComplaintInfoModelEMapper extends BaseEMapper<ComplaintInfoModel>{

    List<ComplaintInfoModel> selectListByPage(ComplaintInfoModel complaintInfoModel);

    int getCountByPage(ComplaintInfoModel complaintInfoModel);

    ComplaintInfoBean getComplaintDetail(Integer id);

}