package com.topjet.manage.controller;

import com.topjet.basic.TruckFeignService;
import com.topjet.cloud.manage.custservice.truck.bean.UpdateOwnerTruckInfoRTS;
import com.topjet.cloud.manage.custservice.truck.bean.UpdateOwnerTruckInfoVRU;
import com.topjet.common.ResultBaseMsg;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.manage.constants.CommonConstant;
import com.topjet.manage.domain.vo.OwnerTruckListQuery;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.model.OwnerTruckInfoModel;
import com.topjet.manage.service.OwnerTruckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-09-12 15:04
 */

@Controller
@RequestMapping("/ownerTruck")
public class OwnerTruckController {

    private static final Logger logger = LoggerFactory.getLogger(OwnerTruckController.class);

    @Autowired
    private OwnerTruckService ownerTruckService;

    @Autowired
    private TruckFeignService truckFeignService;


    @RequestMapping("/list")
    @ResponseBody
    public PaginationBean list(OwnerTruckListQuery query){
        return ownerTruckService.list(query);
    }

    /**
     * 熟车管理删除关联关系
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResultBaseMsg edit(Integer id) throws Exception {
        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();
        OwnerTruckInfoModel ownerTruckInfoModel = ownerTruckService.selectByPrimaryKey(id);

        ownerTruckInfoModel.setDeleted(CommonConstant.DB_RECORD_DELETED_STATUS_TURE);
        UpdateOwnerTruckInfoRTS rts = new UpdateOwnerTruckInfoRTS();
        rts.setOwnerTruckId(id);
        rts.setDeleted(CommonConstant.DB_RECORD_DELETED_STATUS_TURE);
        UpdateOwnerTruckInfoVRU vru = truckFeignService.updateOwnerTruckInfo(rts);
        if(vru != null && vru.getCode()==1){
            resultBaseMsg.setStatus(ExceptionEnum.E_0.getStatus());
            resultBaseMsg.setMsg(ExceptionEnum.E_0.getMsg());
        }else{
            resultBaseMsg.setStatus(ExceptionEnum.E_38.getStatus());
            resultBaseMsg.setMsg(ExceptionEnum.E_38.getMsg());
        }
        return resultBaseMsg;
    }


}
