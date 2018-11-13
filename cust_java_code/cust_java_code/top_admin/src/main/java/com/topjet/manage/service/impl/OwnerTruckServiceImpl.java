package com.topjet.manage.service.impl;

import com.topjet.manage.domain.vo.OwnerTruckListQuery;
import com.topjet.manage.domain.vo.OwnerTruckListVO;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.model.OwnerTruckInfoModel;
import com.topjet.manage.framwork.exception.HandlerException;
import com.topjet.manage.mapper.readMapper.OwnerTruckInfoModelEMapper;
import com.topjet.manage.service.OwnerTruckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-09-12 14:53
 */

@Service
@Transactional
public class OwnerTruckServiceImpl implements OwnerTruckService{

    private static final Logger logger = LoggerFactory.getLogger(HandlerException.class);

    @Autowired
    private OwnerTruckInfoModelEMapper ownerTruckInfoModelEMapper;


    @Override
    public List<OwnerTruckListVO> listOwnerTruck(OwnerTruckListQuery query) {
        return ownerTruckInfoModelEMapper.listOwnerTruck(query);
    }

    @Override
    public Integer countOwnerTruck(OwnerTruckListQuery query) {
        return ownerTruckInfoModelEMapper.countOwnerTruck(query);
    }

    @Override
    public PaginationBean list(OwnerTruckListQuery query) {
        List<OwnerTruckListVO> list = this.listOwnerTruck(query);
        Integer total = this.countOwnerTruck(query);
        PaginationBean beans = new PaginationBean();
        beans.setRows(list);
        beans.setTotal(total);
        return beans;
    }

    @Override
    public Object delete(Integer id) {
        return null;
    }

    @Override
    public OwnerTruckInfoModel selectByPrimaryKey(Integer id) {
        return ownerTruckInfoModelEMapper.selectByPrimaryKey(id);
    }

}
