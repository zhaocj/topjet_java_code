package com.topjet.manage.controller;

import com.topjet.common.ResultBaseMsg;
import com.topjet.common.SessionUtils;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.common.util.CityUtil;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.constants.CommonConstant;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.CallCenterCallLogBean;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.bean.UserInfoBean;
import com.topjet.manage.domain.model.CallCenterCallLogModel;
import com.topjet.manage.domain.model.DriverBusinessLineInfoModel;
import com.topjet.manage.domain.model.UserInfoModel;
import com.topjet.manage.domain.model.UserOtherInfoModel;
import com.topjet.manage.mapper.readMapper.DriverBusinessLineInfoModelEMapper;
import com.topjet.manage.mapper.readMapper.UserInfoModelEMapper;
import com.topjet.manage.mapper.readMapper.UserOtherInfoModelEMapper;
import com.topjet.manage.service.CallCenterCallLogService;
import com.topjet.manage.service.CityService;
import com.topjet.manage.service.DriverBusinessLineInfoService;
import com.topjet.manage.service.UserInfoService;
import com.topjet.util.HttpUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-09-14 16:33
 */

@Controller
@RequestMapping("/callCenter")
public class CallCenterController extends BaseController{
    private final static Logger log = Logger.getLogger(CallCenterController.class);

    @Autowired
    private CallCenterCallLogService callCenterCallLogService;

    @Autowired
    private UserInfoModelEMapper userInfoModelEMapper;

    @Autowired
    private UserOtherInfoModelEMapper userOtherInfoModelEMapper;
    @Autowired
    private DriverBusinessLineInfoService driverBusinessLineInfoService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private CityService cityService;
    @Autowired
    private DriverBusinessLineInfoModelEMapper driverBusinessLineInfoModelMapper;

    /**
     * 我的通话记录列表
     *
     * @param
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/myCallLogList")
    public PaginationBean myCallLogList(CallCenterCallLogBean ccl) throws Exception{
        ccl.setCreateBy(SessionUtils.getSysUserSession().getId());
        List<CallCenterCallLogModel> callLogList = callCenterCallLogService.getCallLogList(ccl);
        int total = callCenterCallLogService.getCallLogCount(ccl);
        PaginationBean bean = new PaginationBean();
        bean.setRows(callLogList);
        bean.setTotal(total);
        return bean;
    }

    /**
     * 根据来电查询用户信息
     *
     */
    @ResponseBody
    @RequestMapping("/queryByCallNo")
    public Object selectInfoByCallNo(UserInfoModel model){

        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("mobile",model.getMobile());
        paramMap.put("deleted",CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
        List<UserInfoModel> userInfoModels = userInfoModelEMapper.selectListByParam(paramMap);


        if(userInfoModels.isEmpty()){
            return "";
        }
        paramMap.clear();
        paramMap.put("userId",userInfoModels.get(0).getId());
        paramMap.put("deleted",CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
        List<UserOtherInfoModel> userOtherInfoModels = userOtherInfoModelEMapper.selectListByParam(paramMap);

        UserInfoModel userInfoModel = userInfoModels.get(0);
        UserOtherInfoModel userOtherInfoModel = userOtherInfoModels.get(0);
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.setUsername(userInfoModel.getUsername());
        userInfoBean.setId(userInfoModel.getId());
        userInfoBean.setIdNo(userInfoModel.getIdNo());
        userInfoBean.setTelephone(userOtherInfoModel.getTelephone());
        userInfoBean.setUserType(userInfoModel.getUserType());
        userInfoBean.setCompanyName(userOtherInfoModel.getCompanyName());
        userInfoBean.setCreateTime(userInfoModel.getCreateTime());

        //获取用户常住地
        userInfoBean.setResident1(CityUtil.getFirstLevelCityId(userOtherInfoModels.get(0).getResidentCityId()));
        userInfoBean.setResident2(CityUtil.getSecondLevelCityId(userOtherInfoModels.get(0).getResidentCityId()));
        userInfoBean.setResident3(CityUtil.getThirdLevelCityId(userOtherInfoModels.get(0).getResidentCityId()));

        //获取经营地址
        userInfoBean.setBusinessAddressCity1(CityUtil.getFirstLevelCityId(userOtherInfoModels.get(0).getBusinessAddressCityId()));
        userInfoBean.setBusinessAddressCity2(CityUtil.getSecondLevelCityId(userOtherInfoModels.get(0).getBusinessAddressCityId()));
        userInfoBean.setBusinessAddressCity3(CityUtil.getThirdLevelCityId(userOtherInfoModels.get(0).getBusinessAddressCityId()));

        //获取司机常跑路线
        this.getBusinessLine(userInfoBean,userInfoBean.getId());
        return userInfoBean;
    }
    public UserInfoBean getBusinessLine(UserInfoBean userInfoBean, int userId) {
        log.info("********************进入获取司机长跑路线**********************");
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("driverId", userId);
        paraMap.put("deleted",0);

        List<DriverBusinessLineInfoModel> dblList = driverBusinessLineInfoModelMapper.selectListByParam(paraMap);
        if (dblList != null && dblList.size() > 0) {
            int i = 0;
            for (DriverBusinessLineInfoModel driverBusinessLineInfoModel : dblList) {
                driverBusinessLineInfoModel = dblList.get(i);
                switch (i){
                    case 0:
                        if (StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine1())) {
                            userInfoBean.setBusinessLineFisrt1("0");
                        }
                        if (!StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine1())) {
                            userInfoBean.setBusinessLineFisrt1(CityUtil.convertToString(cityService.getFirstCity(CityUtil.convertToString(driverBusinessLineInfoModel.getBusinessLineCityId())).getAdcode()));
                        }
                        if (!StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine2())) {
                            userInfoBean.setBusinessLineFisrt2(CityUtil.convertToString(cityService.getSecondCity(CityUtil.convertToString(driverBusinessLineInfoModel.getBusinessLineCityId())).getAdcode()));
                        }
                        if (!StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine3())) {
                            userInfoBean.setBusinessLineFisrt3(CityUtil.convertToString(driverBusinessLineInfoModel.getBusinessLineCityId()));
                        }
                        if(driverBusinessLineInfoModel.getId() == null){
                            userInfoBean.setBusinessLineFisrtId(Integer.parseInt(""));
                        }
                        else{
                            userInfoBean.setBusinessLineFisrtId(driverBusinessLineInfoModel.getId());
                        }
                        break;
                    case 1:
                        if (StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine1())) {
                            userInfoBean.setBusinessLineSecond1("0");
                        }
                        if (!StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine1())) {
                            userInfoBean.setBusinessLineSecond1(CityUtil.convertToString(cityService.getFirstCity(CityUtil.convertToString(driverBusinessLineInfoModel.getBusinessLineCityId())).getAdcode()));
                        }
                        if (!StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine2())) {
                            userInfoBean.setBusinessLineSecond2(CityUtil.convertToString(cityService.getSecondCity(CityUtil.convertToString(driverBusinessLineInfoModel.getBusinessLineCityId())).getAdcode()));
                        }
                        if (!StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine3())) {
                            userInfoBean.setBusinessLineSecond3(CityUtil.convertToString(driverBusinessLineInfoModel.getBusinessLineCityId()));
                        }
                        if(driverBusinessLineInfoModel.getId() == null){
                            userInfoBean.setBusinessLineSecondId(Integer.parseInt(""));
                        }
                        else{
                            userInfoBean.setBusinessLineSecondId(driverBusinessLineInfoModel.getId());
                        }
                        break;
                    case 2:
                        if (StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine1())) {
                            userInfoBean.setBusinessLineThird1("0");
                        }
                        if (!StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine1())) {
                            userInfoBean.setBusinessLineThird1(CityUtil.convertToString(cityService.getFirstCity(CityUtil.convertToString(driverBusinessLineInfoModel.getBusinessLineCityId())).getAdcode()));
                        }
                        if (!StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine2())) {
                            userInfoBean.setBusinessLineThird2(CityUtil.convertToString(cityService.getSecondCity(CityUtil.convertToString(driverBusinessLineInfoModel.getBusinessLineCityId())).getAdcode()));
                        }
                        if (!StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine3())) {
                            userInfoBean.setBusinessLineThird3(CityUtil.convertToString(driverBusinessLineInfoModel.getBusinessLineCityId()));
                        }
                        if(driverBusinessLineInfoModel.getId() == null){
                            userInfoBean.setBusinessLineThirdId(Integer.parseInt(""));
                        }
                        else{
                            userInfoBean.setBusinessLineThirdId(driverBusinessLineInfoModel.getId());
                        }
                        break;
                    case 3:
                        if (StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine1())) {
                            userInfoBean.setBusinessLineForth1("0");
                        }
                        if (!StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine1())) {
                            userInfoBean.setBusinessLineForth1(CityUtil.convertToString(cityService.getFirstCity(CityUtil.convertToString(driverBusinessLineInfoModel.getBusinessLineCityId())).getAdcode()));
                        }
                        if (!StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine2())) {
                            userInfoBean.setBusinessLineForth2(CityUtil.convertToString(cityService.getSecondCity(CityUtil.convertToString(driverBusinessLineInfoModel.getBusinessLineCityId())).getAdcode()));
                        }
                        if (!StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine3())) {
                            userInfoBean.setBusinessLineForth3(CityUtil.convertToString(driverBusinessLineInfoModel.getBusinessLineCityId()));
                        }
                        if(driverBusinessLineInfoModel.getId() == null){
                            userInfoBean.setBusinessLineForthId(Integer.parseInt(""));
                        }
                        else{
                            userInfoBean.setBusinessLineForthId(driverBusinessLineInfoModel.getId());
                        }
                        break;
                }
                i++;
            }
            return userInfoBean;

        }
        userInfoBean.setBusinessLineFisrt1("0");
        userInfoBean.setBusinessLineSecond1("0");
        userInfoBean.setBusinessLineThird1("0");
        userInfoBean.setBusinessLineForth1("0");
        return userInfoBean;
    }

    /**
     * 通话记录列表
     */
    @ResponseBody
    @RequestMapping("/listCallCenterCallLog")
    public Object listCallCenterCallLog(CallCenterCallLogBean callCenterCallLogBean){
        PaginationBean paginationBean = new PaginationBean();
        paginationBean.setRows(callCenterCallLogService.getCallLogList(callCenterCallLogBean));
        paginationBean.setTotal(callCenterCallLogService.getCallLogCount(callCenterCallLogBean));
        return paginationBean;
    }

    /**
     * 保存通话记录
     * @param ccu
     * @return
     */
    @RequestMapping("addCallCenterCallLog")
    @ResponseBody
    public ResultBaseMsg add(CallCenterCallLogBean ccu)throws Exception {
        log.info("***************开始保存通话记录*******************"+ccu.getOriginCallNo());
        CallCenterCallLogModel cccl = new CallCenterCallLogModel();
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("deleted", CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
        paramMap.put("callSheetId",ccu.getCallSheetId());
        List<CallCenterCallLogModel> list = callCenterCallLogService.selectListByParam(paramMap);

        if(list!=null && !list.isEmpty()){
            msg.setStatus(ExceptionEnum.E_3.getStatus());
            msg.setMsg(ExceptionEnum.E_3.getMsg());
            return msg;
        }
        if(!StringUtils.isEmpty(ccu.getRingTime())&&!ccu.getRingTime().contains("NaN")){
            cccl.setRingTime(DateUtil.StringToDate(ccu.getRingTime()));
        }
        if(!StringUtils.isEmpty(ccu.getBeginTime())&&!ccu.getBeginTime().equals("undefined")){
            cccl.setBeginTime(DateUtil.StringToDate(ccu.getBeginTime()));
        }
        if(!StringUtils.isEmpty(ccu.getEndTime())&&!ccu.getEndTime().equals("undefined")){
            cccl.setEndTime(DateUtil.StringToDate(ccu.getEndTime()));
        }
        if(!ccu.getAgent().equals("undefined")){
            cccl.setAgent(ccu.getAgent());
        }

        if(!ccu.getAgentName().equals("undefined")){
            cccl.setAgentName(ccu.getAgentName());
        }
        if(!ccu.getQueue().equals("undefined")){
            cccl.setQueue(ccu.getQueue());
        }
        if(!ccu.getQueueName().equals("undefined")){
            cccl.setQueueName(ccu.getQueueName());
        }

        if(!ccu.getMonitorFilename().equals("undefined")){
            cccl.setMonitorFilename(ccu.getMonitorFilename());
        }
        if(!ccu.getCallSheetId().equals("undefined")){
            cccl.setCallSheetId(ccu.getCallSheetId());
        }
        if(!ccu.getStatus().equals("undefined")){
            cccl.setStatus(ccu.getStatus());
        }
        if(!ccu.getCallType().equals("undefined")){
            cccl.setCallType(ccu.getCallType());
        }
        if(!ccu.getOriginCalledNo().equals("undefined")){
            cccl.setOriginCalledNo(ccu.getOriginCalledNo());
        }
        if(!ccu.getOriginCallNo().equals("undefined")){
            cccl.setOriginCallNo(ccu.getOriginCallNo());
        }

        cccl.setCreateTime(DateUtil.now());
        cccl.setCreateBy(SessionUtils.getSysUserSession().getId());
        cccl.setCreateByName(SessionUtils.getSysUserSession().getNickName());
        cccl.setBusinessType(ccu.getBusinessType());
        cccl.setRemark(ccu.getRemark());
        callCenterCallLogService.insertSelective(cccl);
        log.info("*********************保存通话记录成功**********************");
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
    }

    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public ResultBaseMsg updateUserInfo(UserInfoBean userInfoBean)throws Exception {

        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();
        userInfoService.updateUser(userInfoBean);
        resultBaseMsg.setStatus(ExceptionEnum.E_0.getStatus());
        resultBaseMsg.setMsg(ExceptionEnum.E_0.getMsg());

        return  resultBaseMsg;

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
        dataList.add( callCenterCallLogService.getSysUser());
        dataList.add(SessionUtils.getSysUserSession().getId());
        return dataList;
    }

    /**
     * 添加黑名单
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("addBlackList")
    @ResponseBody
    public ResultBaseMsg addBlackList(HttpServletRequest request) throws Exception {
        log.info("*************开始添加黑名单*****************");
        try {
            String PBX = request.getParameter("PBX");
            String BlackNum =request.getParameter("BlackNum");
            String sig = request.getParameter("sig");
            String Type = request.getParameter("Type");
            String authorization =request.getParameter("authorization");
            //请求地址
            String url ="http://apis.7moor.com/v20160818/blacknum/saveBlackList/N00000008474?sig="+sig;
            //请求数据
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("PBX",PBX);
            jsonParam.put("Type",Type);
            jsonParam.put("BlackNum",BlackNum);
            String data = jsonParam.toString();//将json对象转换为字符串
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Authorization",authorization);
            HttpResponse response = httpClient.execute(httpPost);
            // 发送 POST 请求
            String stringObject = HttpUtils.httpPost(url, data);
            System.out.println("返回结果是："+stringObject);
            log.info("*************添加黑名单接口返回结果*****************"+stringObject);
            msg.setStatus(ExceptionEnum.E_0.getStatus());
            msg.setMsg(ExceptionEnum.E_0.getMsg());
            return msg;

        } catch (Exception e) {
            e.printStackTrace();;
        }
        return msg;
    }





    @RequestMapping("/updateRemark")
    @ResponseBody
    public ResultBaseMsg updateRemark(CallCenterCallLogBean callCenterCallLogBean)throws Exception {
        return  callCenterCallLogService.updateRemark(callCenterCallLogBean);
    }

}
