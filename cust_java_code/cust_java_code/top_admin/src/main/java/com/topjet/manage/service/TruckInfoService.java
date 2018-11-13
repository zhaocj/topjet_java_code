package com.topjet.manage.service;

import com.topjet.common.ResultBaseMsg;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.bean.TruckDetailBean;
import com.topjet.manage.domain.bean.TruckVerifyBean;
import com.topjet.manage.domain.model.DriverTruckInfoModel;
import com.topjet.manage.domain.model.TruckInfoModel;
import com.topjet.manage.domain.vo.TruckListVerifyQuery;

import java.util.List;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-09-15 13:57
 */

public interface TruckInfoService {

    /**
     * 获取待审核车辆列表
     * @param query
     * @return
     */
    public PaginationBean listTruckListVerify(TruckListVerifyQuery query);


    /**
     * 获取待审核司机车辆详情
     * @param id
     * @return
     */
    public TruckDetailBean getTruckDetil(Integer id);


    /**
     * 车辆认证审核service方法
     * @return
     * @throws Exception
     */
    public ResultBaseMsg updateVerify(TruckVerifyBean bean) throws Exception;


    /**
     *查询同一
     * @param plateNo
     * @return
     */
    List<DriverTruckInfoModel> countAuditPlateNo(String plateNo);





}
