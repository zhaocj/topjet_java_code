package com.topjet.manage.service;

import com.topjet.manage.domain.vo.OwnerTruckListQuery;
import com.topjet.manage.domain.vo.OwnerTruckListVO;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.model.OwnerTruckInfoModel;

import java.util.List;

/**
 * @Author: zhaocj
 * @Description:熟车管理interface
 * @Date: 2017-09-12 14:49
 */

public interface OwnerTruckService {

    /**
     * 查询熟车列表
     * @param query
     * @return
     */
    List<OwnerTruckListVO> listOwnerTruck(OwnerTruckListQuery query);

    /**
     * 查询熟车数量
     * @param query
     * @return
     */
    Integer countOwnerTruck(OwnerTruckListQuery query);


    PaginationBean list(OwnerTruckListQuery query);

    Object delete(Integer id);

    OwnerTruckInfoModel selectByPrimaryKey(Integer id);

}
