package com.topjet.manage.controller;

import com.topjet.basic.BasicFeignService;
import com.topjet.basic.WalletFeginService;
import com.topjet.cloud.manage.constant.PictureServerProperties;
import com.topjet.cloud.manage.service.basic.bean.GeturlRTS;
import com.topjet.cloud.manage.service.basic.bean.RefundRTS;
import com.topjet.cloud.manage.service.basic.bean.RefundVRU;
import com.topjet.common.ResultBaseMsg;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.constants.CommonConstant;
import com.topjet.manage.domain.bean.BaseHttpWalletResponseDTO;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.bean.RefundDetail2Bean;
import com.topjet.manage.domain.model.RefundInfoModel;
import com.topjet.manage.domain.model.RefundPhotoInfoModel;
import com.topjet.manage.service.RefundService;
import com.topjet.manage.service.impl.RefundServiceImpl;
import com.topjet.tool.common.util.FormatUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by liyj on 2017/9/25.
 */
@Controller
@RequestMapping("/refund/")
public class RefundController {

    private static Logger log = LoggerFactory.getLogger(RefundController.class);

    @Autowired
    private RefundService refundService;
    @Autowired
    private BasicFeignService basicFeignService;
    @Autowired
    private WalletFeginService walletFeginService;

    ResultBaseMsg msg = new ResultBaseMsg();

    @RequestMapping("list")
    @ResponseBody
    public Object list(RefundDetail2Bean refundDetail2Bean){
        refundDetail2Bean = getCondition(refundDetail2Bean);
        PaginationBean bean = new PaginationBean();
        List<RefundDetail2Bean> dataList = refundService.getRefundsList(refundDetail2Bean);
        int total = refundService.getRefundsCount(refundDetail2Bean);
        bean.setRows(dataList);
        bean.setTotal(total);
        return bean;
    }

    //退款详情
    @RequestMapping("getRefundInfo")
    public ModelAndView getRefund2Info(Integer id) throws Exception {
        RefundDetail2Bean rb = new RefundDetail2Bean();
        rb.setId(id);
        ModelAndView view=new ModelAndView("view/refund/refundDetail");
        List<RefundDetail2Bean> rim = refundService.getRefundsList(rb);
        if(!rim.isEmpty()){
            RefundDetail2Bean rb2 = new RefundDetail2Bean();
            rb2 = rim.get(0);
            //RefundType  '退款类型:0发起退款人1被退款人'
            //根据退款id和发起退款人查询数据
            List<RefundPhotoInfoModel> rfpList = refundService.getRefundPhontList0(rb2.getId());
            GeturlRTS geturlRTS = new GeturlRTS();
            if(!rfpList.isEmpty()){
                for(RefundPhotoInfoModel rp :rfpList){
                    if(!StringUtils.isBlank(rp.getPictureUrl())){
                        geturlRTS.setKey(rp.getPictureUrl());
                        geturlRTS.setType(PictureServerProperties.PHOTO_OWNER_REMARK);
                        rp.setPictureUrl(basicFeignService.getUrl(geturlRTS).getObjurl());
                    }
                }
                view.addObject("provePhotoList", rfpList);
            }

            //RefundType  '退款类型:0发起退款人1被退款人'
            List<RefundPhotoInfoModel> rpiList = refundService.getRefundPhontList1(rb2.getId());
            if(!rpiList.isEmpty()){
                for(RefundPhotoInfoModel rp :rpiList){
                    if(!StringUtils.isBlank(rp.getPictureUrl())){
                        geturlRTS.setKey(rp.getPictureUrl());
                        geturlRTS.setType(PictureServerProperties.PHOTO_OWNER_REMARK);
                        rp.setPictureUrl(basicFeignService.getUrl(geturlRTS).getObjurl());
                    }
                }
                view.addObject("refutePhotoList", rpiList);
            }

            view.addObject("Refund", rim.get(0));
        }
        return view;
    }


    public RefundDetail2Bean getCondition(RefundDetail2Bean refund2Bean){
        /*if(refund2Bean.getBillNo() != null && !refund2Bean.getBillNo().equals("")){
            refund2Bean.setBillNo("%"+refund2Bean.getBillNo()+"%");
        }*/
        if(refund2Bean.getOrderNo() != null && !refund2Bean.getOrderNo().equals("")){
            refund2Bean.setOrderNo("%"+refund2Bean.getOrderNo()+"%");
        }
        if(refund2Bean.getTriggerName() != null && !refund2Bean.getTriggerName().equals("")){
            refund2Bean.setTriggerName("%"+refund2Bean.getTriggerName()+"%");
        }
        return refund2Bean;
    }

    //同意退款
    @RequestMapping("/agreeRefund")
    @ResponseBody
    public ResultBaseMsg agreeRefund(RefundInfoModel rim, String billNo,Integer ownerId,Integer orderId) {
        log.debug("收到请求 ： agreeRefund " + rim + " billNo : " + billNo +" ownerId : "+ownerId +" orderId: "+orderId);
        rim.setUpdateTime(DateUtil.now());
        rim.setOrderId(orderId);
        rim.setTriggerId(ownerId);
        refundService.updateById(rim,ownerId,orderId);
        //同意退款推送
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
    }

    /**
     * 关闭退款
     */
    @RequestMapping("updateRefund")
    @ResponseBody
    public ResultBaseMsg updateRefund(RefundInfoModel rim,Integer ownerId,Integer orderId){
        log.debug("收到请求 ： updateRefund " + rim  +" ownerId : "+ownerId +" orderId: "+orderId);
        rim.setUpdateTime(DateUtil.now());
        rim.setOrderId(orderId);
        rim.setTriggerId(ownerId);
        refundService.updateById(rim,ownerId,orderId);
        //关闭退款推送
        try {
            /*RefundInfoModel refundInfoModel=refundInfoService.selectByPrimaryKey(rim.getId());
            OrderInfoModel orderInfoModel=ordderInfoService.selectByPrimaryKey(refundInfoModel.getOrderId());
            GoodsInfoModel goodsInfoModel=goodsInfoService.selectByPrimaryKey(orderInfoModel.getGoodsId());
            UserModel  userModel = userService.selectByPrimaryKey(refundInfoModel.getTriggerId());//发起方信息
            UserModel userModel1 ;
            Integer pushType = PushEum.PUSHEUM_TYPE_DRIVER;//司机
            String beingpushType = CommonConstant.BEINGPUSH_DRIVER;//推送给司机
            PushEum push=PushEum.ORDER_UPDATEREFUND;
            String content ="";

            //判断发起方是司机还是货主
            if(refundInfoModel.getTriggerId().equals(orderInfoModel.getOwnerId())){//发起方是货主
                userModel1= userService.selectByPrimaryKey(orderInfoModel.getDriverId());//获取被退款方信息
            }else{
                pushType = PushEum.PUSHEUM_TYPE_OWNER;//货主
                beingpushType = CommonConstant.BEINGPUSH_OWNER;//推送给货主
                userModel1 =userService.selectByPrimaryKey(orderInfoModel.getOwnerId());//获取被退款方信息
            }

            content = PushEum.getPushMsgFormat(push,pushType,PushEum.getAddressInfo(goodsInfoModel),userModel.getName());

            OrderPushData orderPushData = new OrderPushData();
            PushBean bean = PushBeanFactory.getPushBean(PushEum.ORDER_SIGNFOR, orderPushData, orderInfoModel);
            //给被退款人app推送消息
            pushService.sendPushMessage(userModel1.getId(), bean, content, beingpushType);
            //给被退款人推送短信
            sendMessageService.sendVerificationMessage(userModel1.getMobile(), content);*/
        }catch (Exception e){
            e.getStackTrace();
        }
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
    }

}
