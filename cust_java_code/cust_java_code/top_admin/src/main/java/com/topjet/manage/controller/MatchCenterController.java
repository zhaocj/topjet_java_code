package com.topjet.manage.controller;

import com.topjet.basic.OrderFeignService;
import com.topjet.cloud.manage.constant.PushConstant;
import com.topjet.cloud.manage.custservice.order.bean.HiddenOrShowOrderRTS;
import com.topjet.cloud.manage.custservice.order.bean.HiddenOrShowOrderVRU;
import com.topjet.cloud.manage.mq.message.AppAction;
import com.topjet.cloud.manage.mq.message.AppButton;
import com.topjet.common.ResultBaseMsg;
import com.topjet.common.SessionUtils;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.common.exception.pms.TopjetExceptionHandler;
import com.topjet.common.topjetlog.TopJetLog;
import com.topjet.common.util.CityUtil;
import com.topjet.common.util.DateUtil;
import com.topjet.config.MessageConfig;
import com.topjet.manage.constants.CommonConstant;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.MatchCenterDriverBean;
import com.topjet.manage.domain.bean.MatchCenterOrderBean;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.model.*;
import com.topjet.manage.domain.vo.UserPushTokenVO;
import com.topjet.manage.mapper.readMapper.UserInfoModelEMapper;
import com.topjet.manage.mapper.writeMapper.CallLogModelMapper;
import com.topjet.manage.service.*;
import com.topjet.tool.common.constant.SystemConstant;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liyj on 2017/8/23.
 */
@Controller
@RequestMapping("/matchCenter/")
public class MatchCenterController extends BaseController{

    @Autowired
    private DriverOptionService driverOptionService;
    @Autowired
    private CityService cityService;
    @Autowired
    private CallLogModelMapper callLogModelMapper;
    @Autowired
    private CallLogService callLogService;
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private MatchOrderInfoService matchOrderInfoService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderFeignService orderFeignService;

    @Autowired
    private MessageConfig messageConfig;

    @Autowired
    private UserInfoModelEMapper userInfoModelEMapper;
     /*
     * @author        :  liyj
     * @Create Date   :  2017年8月23日
    * @function		 :	管理平台匹配中心司机位置查询
    * */

    @RequestMapping("driverQueryList")
    @ResponseBody
    public Object driverQueryList(MatchCenterDriverBean matchCenterDriverBean){
        matchCenterDriverBean = setDriverCondition(matchCenterDriverBean);
        List<MatchCenterDriverBean> dataList = driverOptionService.getDriverOptionList(matchCenterDriverBean);
        if(dataList != null && dataList.size() >= 0){
            for (MatchCenterDriverBean mb : dataList) {
                if (!StringUtils.isBlank(mb.getBusinessLine1())) {
                    mb.setBusinessLine1(mb.getBusinessLine1());
                }
                if (!StringUtils.isBlank(mb.getBusinessLine2())) {
                    mb.setBusinessLine2(mb.getBusinessLine2());
                }
                if (!StringUtils.isBlank(mb.getBusinessLine3())) {
                    mb.setBusinessLine3(mb.getBusinessLine3());
                }
            }
        }
        PaginationBean bean = new PaginationBean();
        int total = driverOptionService.getDriverOptionCount(matchCenterDriverBean);
        bean.setRows(dataList);
        bean.setTotal(total);
        return bean;
    }

    //司机位置查询设值
    public MatchCenterDriverBean setDriverCondition(MatchCenterDriverBean matchCenterDriverBean){
        if(!StringUtils.isBlank(matchCenterDriverBean.getAddress1())){
            matchCenterDriverBean.setAddress1(cityService.getFullName(matchCenterDriverBean.getAddress1()));
        }
        if (!StringUtils.isBlank(matchCenterDriverBean.getAddress2())) {
            matchCenterDriverBean.setAddress2(cityService.getCityModel(matchCenterDriverBean.getAddress2()).getCityFullName());
        }
        if (!StringUtils.isBlank(matchCenterDriverBean.getAddress3())) {
            matchCenterDriverBean.setAddress3(cityService.getCityModel(matchCenterDriverBean.getAddress3()).getCityFullName());
        }
        if (!StringUtils.isBlank(matchCenterDriverBean.getResident1())) {
            matchCenterDriverBean.setResident1(cityService.getCityName(matchCenterDriverBean.getResident1()) + "%");
        }
        if (!StringUtils.isBlank(matchCenterDriverBean.getResident2())) {
            matchCenterDriverBean.setResident2(cityService.getCityName(matchCenterDriverBean.getResident2()) + "%");
        }
       return matchCenterDriverBean;
    }
    /**
     * 联系记录
     */
    @ResponseBody
    @RequestMapping("callLog")
    public Object callLog(HttpServletRequest request,String mobile,String remark)throws Exception {
        ResultBaseMsg msg = new ResultBaseMsg();
        CallLogModel callLogModel = new CallLogModel();
        callLogModel.setMobile(mobile);
        callLogModel.setRemark(remark);
        callLogModel.setCreateTime(DateUtil.now());
        callLogModel.setCreateBy(SessionUtils.getSysUserSession().getId());
        callLogModel.setCreateByName(SessionUtils.getSysUserSession().getNickName());
        callLogModel.setUpdateBy(SessionUtils.getSysUserSession().getId());
        callLogModel.setUpdateTime(DateUtil.now());
        callLogModel.setVersion(1);
        callLogModel.setDeleted(CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
        try {
            callLogService.insertCallLog(callLogModel);
        }
        catch (Exception e) {
            TopJetLog.error("插入联系记录异常");
            throw new TopjetExceptionHandler(ExceptionEnum.E_BUSINESS_MSG.getStatus(), ExceptionEnum.E_BUSINESS_MSG.getMsg());
        }
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
    }
    /**
     * 联系记录列表
     */
    @RequestMapping("callLogQueryList")
    @ResponseBody
    public Object callLogQueryList(CallLogModel callLogModel) throws Exception {
        List<CallLogModel> dataList = callLogService.getCallLogList(callLogModel.getMobile());
        int total = callLogService.getCallLogCount(callLogModel.getMobile());
        PaginationBean bean = new PaginationBean();
        bean.setRows(dataList);
        bean.setTotal(total);
        return bean;
    }
        /*
        * 管理平台匹配中心货源查询
        * */
        @ResponseBody
        @RequestMapping("orderQueryList")
        public Object orderQueryList(MatchCenterOrderBean matchCenterOrderBean)throws Exception {
            matchCenterOrderBean = setOrderCondition(matchCenterOrderBean);
            if(!matchCenterOrderBean.getDepart1().equals("")){
                matchCenterOrderBean.setDepart1(matchCenterOrderBean.getDepart1().substring(0,2));
            }
            if(!matchCenterOrderBean.getDepart2().equals("")){
                matchCenterOrderBean.setDepart2(matchCenterOrderBean.getDepart2().substring(0,2));
            }
            if(!matchCenterOrderBean.getDepart3().equals("")){
                matchCenterOrderBean.setDepart3(matchCenterOrderBean.getDepart3().substring(0,2));
            }
            List<MatchCenterOrderBean> dataList = orderInfoService.goodQuertList(matchCenterOrderBean);
            for(MatchCenterOrderBean mb : dataList){
                mb.setResidentCity(cityService.getFullName(mb.getResidentCityId()));
            }
            int total = orderInfoService.goodQuertCount(matchCenterOrderBean);
            PaginationBean bean = new PaginationBean();
            bean.setRows(dataList);
            bean.setTotal(total);
            return bean;
        }

    //货源查询设值
    public MatchCenterOrderBean setOrderCondition(MatchCenterOrderBean mcdb) {
        if (!StringUtils.isBlank(mcdb.getDepart1())) {
            mcdb.setDepart1(cityService.getCityModel(mcdb.getDepart1()).getCityFullName());
        }
        if (!StringUtils.isBlank(mcdb.getDepart2())) {
            mcdb.setDepart2(cityService.getCityModel(mcdb.getDepart2()).getCityFullName());
        }
        if (!StringUtils.isBlank(mcdb.getDepart3())) {
            mcdb.setDepart3(cityService.getCityModel(mcdb.getDepart3()).getCityFullName());
        }
        if (!StringUtils.isBlank(mcdb.getDestination1())) {
            mcdb.setDestination1(cityService.getCityModel(mcdb.getDestination1()).getCityFullName());
        }
        if (!StringUtils.isBlank(mcdb.getDestination2())) {
            mcdb.setDestination2(cityService.getCityModel(mcdb.getDestination2()).getCityFullName());
        }
        if (!StringUtils.isBlank(mcdb.getDestination3())) {
            mcdb.setDestination3(cityService.getCityModel(mcdb.getDestination3()).getCityFullName());
        }
        return mcdb;
    }

    //货源详情
    @RequestMapping("goodsdetail")
    public Object goodsdetail(Integer id, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView("view/common/goodsDetail");
        if(request.getParameter("goodsNo") != null && !request.getParameter("goodsNo").equals("")){
            String orderNo = new String(request.getParameter("goodsNo").getBytes("ISO-8859-1"), "UTF-8");
            //根据货源号查询
            OrderInfoModel orderList = orderService.findByOrderNo(orderNo);

            if(orderList != null){
                id = orderList.getId();
            }else{
                mv.addObject("title", "你搜索的订单 "+orderNo+"不存在");
                mv.setViewName("common/404");
                return mv;
            }
        }

       /* OrderDetailAdminVO result=orderService.orderDetail(id);
        if (result != null){
            mv.addObject("data",result);
        }
        String title = new String(request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8");
        mv.addObject("title",title);*/
        return mv;
    }

    //货源隐藏
    @ResponseBody
    @RequestMapping("hideOrder")
    public Object hideOrder(Integer id)throws TopjetExceptionHandler {
        return matchOrderInfoService.hideOrder(id);
    }
    /**
     * 检查手机号查询
     */
    @ResponseBody
    @RequestMapping("checkMobile")
    public Object checkMobile(HttpServletRequest request){
        Map<String,Object> paramMap = new HashMap<>();
          String pushMobile = request.getParameter("pushMobile");
        List<UserInfoModel> dataList = userInfoService.getListByMobile(pushMobile);
        if(dataList.isEmpty()){
            paramMap.put("success","false");
            return paramMap;
        }else{
            paramMap.put("success","true");
            paramMap.put("userType",dataList.get(0).getUserType());
            return paramMap;
        }
    }
    /**
     * 批量标记虚假订单
     */
    @ResponseBody
    @RequestMapping("falseSupply")
    public Object falseSupply(HttpServletRequest request, HttpServletResponse response)throws Exception {
        MatchCenterOrderBean mcob = new MatchCenterOrderBean();
        String checkedItems = request.getParameter("checkedItems");
        JSONArray jsonArray = JSONArray.fromObject(checkedItems);
        int total = jsonArray.size();
        int successCount = 0;
        for (int i = 0; i < total; i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
           //jsonObj.getInt("Goodsversion")就是货物的版本号
           //jsonObj.getInt("goodsId")就是货物id
             //根据货物id，版本号查询数据
            //将goodsInfo表种的isHidden字段修改为1（根据货物id修改）
            GoodsInfoModel goodsInfoModel = new GoodsInfoModel();
            goodsInfoModel.setId(jsonObj.getInt("goodsId"));
            List<GoodsInfoModel> dataList = orderInfoService.getGoodsInfo(goodsInfoModel);
             if (!dataList.isEmpty()) {
                for (GoodsInfoModel good : dataList) {
                    HiddenOrShowOrderRTS hiddenOrShowOrderRTS = new HiddenOrShowOrderRTS();
                    hiddenOrShowOrderRTS.setGoodsId(good.getId());
                    hiddenOrShowOrderRTS.setIsHidden(1);
                    hiddenOrShowOrderRTS.setOrderId(jsonObj.getInt("goodsId"));
                    hiddenOrShowOrderRTS.setUserId(good.getUserId());
                    HiddenOrShowOrderVRU hiddenOrShowOrderVRU = orderFeignService.hiddenOrShowOrder(hiddenOrShowOrderRTS);
                    if (hiddenOrShowOrderVRU.getCode() == 1) {
                        successCount++;
                    }
                }
            }
        }
        mcob.setSuccessCount(successCount);
        mcob.setFailCount(total - successCount);
        return mcob;
    }
    /**
     * 推送货源给司机
     * @return
     */
    @RequestMapping("pushSupply")
    @ResponseBody
    public ResultBaseMsg pushSupply(HttpServletRequest request){
        ResultBaseMsg msg = new ResultBaseMsg();
        String mobile = request.getParameter("mobile");
        String orderId =request.getParameter("orderId");
        Integer id  = Integer.valueOf(orderId);//id就是货源id
        OrderInfoModel orderInfoModel = orderInfoService.selectByPrimaryKey(id);
        Integer freightFee = 0;
        if(orderInfoModel == null){
            freightFee = 0;
        }
        else{
            freightFee = orderInfoModel.getFreightFee();
        }
        GoodsInfoModel goodsInfoModel = orderService.selectByPrimaryKey(id);
        String count = "";
        if(goodsInfoModel.getQuantityType() == 1){
            count = goodsInfoModel.getQuantityMax().toString();
        }
        if(goodsInfoModel.getQuantityType() == 2){
            count = goodsInfoModel.getQuantityMax().toString()+"--"+goodsInfoModel.getQuantityMin().toString();
        }
        GoodsDetailInfoModel goodsDetailInfoModel = orderService.findGoodDetailById(id);
        List<UserInfoModel> dataList = userInfoService.getListByMobile(mobile);
        UserInfoModel userInfoModel = dataList.get(0);
        //推送货源接口
      //  String aa = messageConfig.getGoodsPushMessage();
        String content = MessageFormat.format(messageConfig.getGoodsPushMessage(),
                getMsg("到",CityUtil.cityNameFormat(goodsDetailInfoModel.getDepart1(), goodsDetailInfoModel.getDepart2(),goodsDetailInfoModel.getDepart3()),
                        CityUtil.cityNameFormat(goodsDetailInfoModel.getDestination1(), goodsDetailInfoModel.getDestination2(), goodsDetailInfoModel.getDestination3())),
                goodsInfoModel.getCategory(),
                getMsg(count, "重量", goodsInfoModel.getUnit()),
                getMsg(freightFee.toString(),"运费", "元"));
        AppButton appButton = new AppButton();
        appButton.setText(messageConfig.getMessagePushButton());
        List<AppButton> buttonList = new ArrayList<>();
        buttonList.add(appButton);
        //给发起方app推送消息
        String userAppTokenType = this.getUserAppType(userInfoModel.getId(),SystemConstant.BEINGPUSH_DRIVER);
        if (StringUtils.isNotBlank(userAppTokenType)){
            userInfoService.pushMessage(userInfoModel.getId(), "新货源推送", content, SystemConstant.BEINGPUSH_DRIVER,AppAction.getGoodsAppAction(AppAction.ACTION_OWNER_TOGOODS, userAppTokenType, goodsInfoModel.getId()) ,"" ,buttonList, PushConstant.MESSAGETYPE_ORDER, goodsInfoModel.getId());
        }
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
    }

    /**
     * 处理推送参数前后拼接
     *
     * @param obj
     * @param prefix
     *            前缀
     * @param suffix
     *            后缀
     * @return
     */
    private static String getMsg(String obj, String prefix, String suffix) {
        String str = "";
        if (obj == null) {
            return str;
        }
        str=obj.toString();

        if (org.apache.commons.lang.StringUtils.isBlank(str)) {
            return "";
        }
        if (str.equals("0.00")) {
            return "";
        }
        str = prefix + str + suffix;
        return str;
    }

    private String getUserAppType(Integer userId, String beingPushType) {
        UserPushTokenVO userPushToken = userInfoModelEMapper.getUserPushToken(userId);

        if (userPushToken==null) return "";

        String msgPushToken = "";
        if (SystemConstant.BEINGPUSH_DRIVER.equals(beingPushType)) {
            msgPushToken = userPushToken.getDriverToken();
        } else if (SystemConstant.BEINGPUSH_OWNER.equals(beingPushType)) {
            msgPushToken = userPushToken.getOwnerToken();
        }

        if (org.apache.commons.lang.StringUtils.isEmpty(msgPushToken)) {
            return ""; // 没有token则不推送
        }
        String[] split = msgPushToken.split(",");
        if (split.length != 2) {
            return "";
        }
        return split[0];

    }
}
