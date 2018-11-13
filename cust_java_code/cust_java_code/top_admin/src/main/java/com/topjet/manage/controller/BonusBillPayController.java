package com.topjet.manage.controller;

import com.topjet.common.constants.BillConstant;
import com.topjet.common.constants.GoodsConstants;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.common.util.AmtUtil;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.*;
import com.topjet.manage.domain.model.BonusBillInfoModel;
import com.topjet.manage.domain.model.RecommendationBonusInfoModel;
import com.topjet.manage.domain.model.RecommendationBonusSettingModel;
import com.topjet.manage.service.BonusBillInfoService;
import com.topjet.manage.service.RecommendationBonusInfoService;
import com.topjet.manage.service.RecommendationBonusInfoSettingService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-08-01 14:58
 */

@Controller
@RequestMapping("/bonusBillPay")
public class BonusBillPayController extends BaseController{
    private final static Logger log = Logger.getLogger(BonusBillPayController.class);

    @Autowired
    private BonusBillInfoService bonusBillInfoService;

    @Autowired
    private RecommendationBonusInfoService recommendationBonusInfoService;

    @Autowired
    private RecommendationBonusInfoSettingService recommendationBonusInfoSettingService;


    @RequestMapping("/list")
    @ResponseBody
    public Object getDataList(BonusBillInfoBean model,HttpServletRequest request){

        log.info("补贴发放");
        List<BonusBillInfoBean> bean=bonusBillInfoService.getBillInfo(model);//DateUtil.getCurrDate()
        int total = bonusBillInfoService.getBillCount(model);
        BonusPayListBean footerBean=new BonusPayListBean();
        footerBean.setBillType("0");
        for (BonusBillInfoBean billInfoBean : bean) {
            if(billInfoBean.getBillType().equals(GoodsConstants.BILL_TYPE_RECOMMAND_BONUS)){
                billInfoBean.setSumAmt(AmtUtil.stingToBigDecimalAdd(billInfoBean.getSumAmtOk(),billInfoBean.getSumAmtNo()));
            }

            //金额格式化
            billInfoBean.setSumAmt(AmtUtil.stingToBigDecimal(billInfoBean.getSumAmt()).toString());
            billInfoBean.setSumAmtOk(AmtUtil.stingToBigDecimal(billInfoBean.getSumAmtOk()).toString());
            billInfoBean.setSumAmtNo(AmtUtil.stingToBigDecimal(billInfoBean.getSumAmtNo()).toString());
            //合计数据
            footerBean.setCountId(AmtUtil.stingToIntegerAdd(billInfoBean.getCountId(),footerBean.getCountId()));
            footerBean.setSumAmt(AmtUtil.stingToBigDecimalAdd(footerBean.getSumAmt(),billInfoBean.getSumAmt()));
            footerBean.setCountIdOk(AmtUtil.stingToIntegerAdd(footerBean.getCountIdOk(),billInfoBean.getCountIdOk()));
            footerBean.setSumAmtOk(AmtUtil.stingToBigDecimalAdd(footerBean.getSumAmtOk(),billInfoBean.getSumAmtOk()));
            footerBean.setCountIdNo(AmtUtil.stingToIntegerAdd(footerBean.getCountIdNo(),billInfoBean.getCountIdNo()));
            footerBean.setSumAmtNo(AmtUtil.stingToBigDecimalAdd(footerBean.getSumAmtNo(),billInfoBean.getSumAmtNo()));
        }

        List<BonusPayListBean> foFooterBean=new ArrayList<BonusPayListBean>();
        foFooterBean.add(footerBean);

        PaginationBean beans=new PaginationBean();
        beans.setRows(bean);
        beans.setTotal(total);
        beans.setFooter(foFooterBean);
        return beans;
    }


    /**
     * 推荐费补贴订单明细data
     * @param model
     * @param response
     */
    @ResponseBody
    @RequestMapping("/recommendationFeeBounsBillDeatailList")
    public Object recommendationFeeBounsBillDeatailDataList(RecommendationFeeBounsBillDeatailBean model, HttpServletResponse response){
        log.info("推荐费补贴订单明细data");
        PaginationBean beans=bonusBillInfoService.getRecommendationFeeBounsBillDeatailBean(model);
        return beans;
    }


    /**
     * 补贴发放
     * @param model
     * @param password
     * @param response
     */
    @RequestMapping("/transferWalletAmt")
    @ResponseBody
    public Object transferWalletAmt(BonusBillInfoBean model,String password,HttpServletResponse response){
        List<BonusBillInfoModelBean> list=	bonusBillInfoService.getBillInfoModelBean(model);
        BonusBillResponseBean resultJson=new BonusBillResponseBean();
        resultJson.setFlag(0);
        msg.setMsg("数据过期，请刷新数据再次审核！");
        msg.setStatus("0");
        if(list==null){
            return msg;
        }
        if(!model.getCountIdNo().equals(list.size()+"")){
            return msg;
        }
        resultJson.setFlag(1);
        String setMsg="";
        boolean settingFlag = true;
        BigDecimal maxAmountPerDay = BigDecimal.ZERO;
        for (BonusBillInfoModelBean billInfoModel : list) {
            BonusBillInfoModel bim = new BonusBillInfoModel();
            bim.setId(billInfoModel.getId());
            try {
                BigDecimal money = billInfoModel.getAmount();
                if(billInfoModel.getBillType().equals(BillConstant.BILL_TYPE_RECOMMAND_BONUS)){
                    if(settingFlag){
                        RecommendationBonusInfoModel rbm = recommendationBonusInfoService.selectByPrimaryKey(billInfoModel.getSourceId());
                        RecommendationBonusSettingModel rsm = recommendationBonusInfoSettingService.selectByPrimaryKey(rbm.getSettingId());
                        maxAmountPerDay = rsm.getMaxAmountPerDay();
                        settingFlag = false;
                    }
                    if(money!= null && money.compareTo(BigDecimal.ZERO) < 0){
                        money = BigDecimal.ZERO;
                    }else if(money!= null && money.compareTo(maxAmountPerDay)>0){
                        money = maxAmountPerDay;
                    }
                }
                if(money.compareTo(BigDecimal.ZERO) == 0){
                    return true;
                }
                //TODO 调用服务进行支付
               /* bean.setAmt(AmtUtil.stingToBigDecimal(money+""));
                bean.setPaypwd(MD532.encode(password));//PWDUtil.encrypt(password)
                bean.setToOuterUserId(billInfoModel.getPayeeId()+"");
                bean.setOpmsg("补贴发放");
                bean.setOuterTrOrderId(billInfoModel.getBillNo());
                bean.setTransfertype(Integer.parseInt(getBillType(billInfoModel.getBillType()+"")));
                BaseHttpWalletResponseDTO result=walletService.transferWalletAmt(bean);*/
                BaseHttpWalletResponseDTO result = new BaseHttpWalletResponseDTO();
                result.setResultcode("0");
                if(result==null){
                    resultJson.setResponse(resultJson.getResponse()+1);
                }else if(result.getResultcode().equals("2048")){
                    resultJson.setFlag(2);
                    setMsg="支付密码错误!";
                    break;
                }else if(result.getResultcode().equals("0")){
                    bim.setStatus(3);
                    bim.setUpdateTime(DateUtil.now());
                    bim.setVersion(billInfoModel.getVersion()+1);
                    bim.setAmount(money);
                    bonusBillInfoService.update(bim);
                    BonusBillInfoModel bonusBillInfoModel = new BonusBillInfoModel();
                    bonusBillInfoModel.setParentId(bim.getId());
                    bonusBillInfoModel.setStatus(3);
                    //修改子账单支付状态
                    bonusBillInfoService.updateByParentId(bonusBillInfoModel);
                    resultJson.setSucceed(resultJson.getSucceed()+1);
                }else{
                    log.info("Resultcode:"+result.getResultcode());
                    resultJson.setDefeat(resultJson.getDefeat()+1);
                }
            } catch (Exception e) {
                resultJson.setErr(resultJson.getErr()+1);
                e.printStackTrace();
            }
        }

        if(setMsg==""){
            setMsg="操作成功:本次操作成功"+resultJson.getSucceed()+"笔订单";
            if(resultJson.getDefeat()!=0){
                setMsg=setMsg+"，失败"+resultJson.getDefeat()+"笔订单";
            }

            if(resultJson.getResponse()!=0){
                setMsg=setMsg+"，钱包未响应"+resultJson.getResponse()+"笔订单";
            }
            if(resultJson.getErr()!=0){
                setMsg=setMsg+"，支付出现异常"+resultJson.getErr()+"笔订单";
            }
        }
        msg.setMsg(setMsg);
        return msg;
    }


    /**
     * 运费中介费补贴订单明细data
     * @param response
     */
    @ResponseBody
    @RequestMapping("/deleteBill")
    public Object deleteBill(HttpServletRequest request, HttpServletResponse response){
        String checkedItems = request.getParameter("checkedItems");
        JSONArray jsonArray = JSONArray.fromObject(checkedItems);
        int total = jsonArray.size();
        if (!StringUtils.isEmpty(checkedItems)) {
            for (int i = 0; i < total; i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                int billId = jsonObj.getInt("billId");
                bonusBillInfoService.deleteBill(billId);
            }
        }
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
    }


    @RequestMapping("/transportFeeBounsBillDeatailList")
    @ResponseBody
    public PaginationBean TransportFeeBounsBillDeatailList(TransportFeeBounsBillDeatailBean model){
        return bonusBillInfoService.getTransportFeeBounsBillDeatailBean(model);
    }

}
