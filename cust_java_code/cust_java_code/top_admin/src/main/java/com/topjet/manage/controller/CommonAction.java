package com.topjet.manage.controller;

import com.topjet.common.ResultBaseMsg;
import com.topjet.common.SessionUtils;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.manage.domain.model.CallCenterUserInfoModel;
import com.topjet.manage.domain.model.ResourceFileVersionInfoModel;
import com.topjet.manage.domain.model.TruckLengthDictionaryModel;
import com.topjet.manage.domain.model.TruckTypeDictionaryModel;
import com.topjet.manage.mapper.readMapper.CallCenterUserInfoModelEMapper;
import com.topjet.manage.mapper.readMapper.TruckLengthDictionaryModelEMapper;
import com.topjet.manage.mapper.readMapper.TruckTypeDictionaryModelEMapper;
import com.topjet.manage.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-09-11 15:43
 */

@Controller
@RequestMapping("/commonAction")
public class CommonAction {

    @Autowired
    private TruckTypeDictionaryModelEMapper truckTypeDictionaryModelEMapper;

    @Autowired
    private TruckLengthDictionaryModelEMapper truckLengthDictionaryModelEMapper;

    @Autowired
    private CallCenterUserInfoModelEMapper  callCenterUserInfoModelEMapper;

    @Autowired
    private CommonService  commonService;

    /**
     * 获取车型车长方法
     */
    @RequestMapping("/getTruckData")
    @ResponseBody
    private List<Object> getTruckData(){
        List<Object> dataList =new ArrayList<>();
        if(SessionUtils.getTruckType().isEmpty()){
            List<TruckTypeDictionaryModel>  dataList1 = truckTypeDictionaryModelEMapper.listTruckType();
            if(!dataList1.isEmpty()){
                dataList.add(dataList1);
            }
            List<TruckLengthDictionaryModel> datalist =truckLengthDictionaryModelEMapper.listTruckLength();
            if(!datalist.isEmpty()){
                dataList.add(dataList);
            }
        }else{
            dataList.add(SessionUtils.getTruckType());
            dataList.add(SessionUtils.getTruckLength());
        }
        return dataList;
    }

    /**
     * 获取客服列表
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("getSysUser")
    @ResponseBody
    public Object getSysUser() throws Exception {
        List<Object> dataList = new ArrayList();
        List<CallCenterUserInfoModel> list = callCenterUserInfoModelEMapper.getSysUser();
        dataList.add(list);
        dataList.add(SessionUtils.getSysUserSession().getId());
        return dataList;
    }


    /**
     * 首页底部  中间配置 点击生效
     * 修改后更新resourceFileVersionInfo表
     * @param
     * @return
     */
    @RequestMapping("updateResource")
    @ResponseBody
    public ResultBaseMsg updateResource(ResourceFileVersionInfoModel rfvModel,String beginDate) {
        try{
            //資源文件更新
            commonService.updateResourceFileVersionInfo(rfvModel,beginDate);
        }catch (Exception e){
            e.printStackTrace();
        }
        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();
        resultBaseMsg.setStatus(ExceptionEnum.E_0.getStatus());
        resultBaseMsg.setMsg(ExceptionEnum.E_0.getMsg());
        return resultBaseMsg;
    }


}
