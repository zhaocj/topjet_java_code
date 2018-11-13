package com.topjet.manage.service.impl;

import com.topjet.manage.domain.bean.ComplaintInfoBean;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.model.ComplaintInfoModel;
import com.topjet.manage.mapper.readMapper.ComplaintInfoModelEMapper;
import com.topjet.manage.service.ComplaintService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-09-21 16:12
 */

@Service
@Transactional
public class ComplaintServiceImpl implements ComplaintService{

    @Autowired
    private ComplaintInfoModelEMapper complaintInfoModelEMapper;

    @Override
    public PaginationBean listComplaint(ComplaintInfoModel model) {
        Map<String,Object> paramMap = new HashMap<String,Object>();

        if(!StringUtils.isBlank(model.getMobile())){
            paramMap.put("mobile",model.getMobile());
        }
        if(!StringUtils.isBlank(model.getOrderSerialNo())){
            paramMap.put("orderSerialNo",model.getOrderSerialNo());
        }
        if(!StringUtils.isBlank(model.getComplaintedMobile())){
            paramMap.put("complaintedMobile",model.getComplaintedMobile());
        }

        if(model.getStatus()!= null){
            paramMap.put("status",model.getStatus());
        }
        paramMap.put("offset",model.getOffset());
        paramMap.put("limit",model.getLimit());
        PaginationBean page = new PaginationBean();
        page.setRows(complaintInfoModelEMapper.selectPageListByParam(paramMap));
        page.setTotal(complaintInfoModelEMapper.getCountByParam(paramMap));
        return page;
    }

    @Override
    public ComplaintInfoBean getComplaintDetailById(Integer id) {
        return complaintInfoModelEMapper.getComplaintDetail(id);
    }

    @Override
    public ComplaintInfoModel getComplaintById(Integer id) {
        return complaintInfoModelEMapper.selectByPrimaryKey(id);
    }
}
