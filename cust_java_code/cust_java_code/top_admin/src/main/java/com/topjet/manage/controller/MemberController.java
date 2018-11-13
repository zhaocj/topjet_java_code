package com.topjet.manage.controller;

import com.topjet.basic.WalletFeginService;
import com.topjet.cloud.manage.service.basic.bean.GetBalanceRTS;
import com.topjet.cloud.manage.service.basic.bean.GetBalanceVRU;
import com.topjet.common.ResultBaseMsg;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.common.util.CityUtil;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.MemberListRequestBean;
import com.topjet.manage.domain.bean.MemberListResponseBean;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.bean.UserInfoBean;
import com.topjet.manage.domain.model.UserAuditHistoryModel;
import com.topjet.manage.domain.model.UserInfoModel;
import com.topjet.manage.service.CityService;
import com.topjet.manage.service.UserInfoService;
import com.topjet.manage.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by liyj on 2017/8/11.
 */
@Controller
@RequestMapping("/memberAction/")
public class MemberController extends BaseController{

    private final static Logger log = Logger.getLogger(MemberController.class);

    ResultBaseMsg resultBaseMsg = new ResultBaseMsg();


    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;
    @Autowired
    private WalletFeginService walletFeginService;


    @ResponseBody
    @RequestMapping("list")
    public  Object list(MemberListRequestBean memberListRequestBean){
        StringBuffer sbBuffer  = new StringBuffer();
        StringBuffer sbBufferE = new StringBuffer();
        if(!org.apache.commons.lang.StringUtils.isEmpty(memberListRequestBean.getResident2())){
            //2017-06-03 bug 当二级城市编码为4个直辖市时，查询数据时为空bug修复
            if(memberListRequestBean.getResident2().equals("010100") || memberListRequestBean.getResident2().equals("020100") ||
                    memberListRequestBean.getResident2().equals("030100") || memberListRequestBean.getResident2().equals("040100")){
                String codeS = sbBuffer.append(memberListRequestBean.getResident2().substring(0, 2)).append("0000").toString();
                String codeE = sbBufferE.append(memberListRequestBean.getResident2().substring(0, 2)).append("9999").toString();
                memberListRequestBean.setResidentStart(Integer.valueOf(codeS));
                memberListRequestBean.setResidentEnd(Integer.valueOf(codeE));

            }else{
                String codeS = sbBuffer.append(memberListRequestBean.getResident2().substring(0, 4)).append("00").toString();
                String codeE = sbBufferE.append(memberListRequestBean.getResident2().substring(0, 4)).append("99").toString();
                memberListRequestBean.setResidentStart(Integer.valueOf(codeS));
                memberListRequestBean.setResidentEnd(Integer.valueOf(codeE));
            }
        }else if(!org.apache.commons.lang.StringUtils.isEmpty(memberListRequestBean.getResident1())){
            String codeS = sbBuffer.append(memberListRequestBean.getResident1().substring(0, 2)).append("0000").toString();
            String codeE = sbBufferE.append(memberListRequestBean.getResident1().substring(0, 2)).append("9999").toString();
            memberListRequestBean.setResidentStart(Integer.valueOf(codeS));
            memberListRequestBean.setResidentEnd(Integer.valueOf(codeE));
        }
        List<MemberListResponseBean> dataList = userInfoService.getMemberList(memberListRequestBean);

        int total = userInfoService.getMemberCount(memberListRequestBean);
        PaginationBean bean = new PaginationBean();
        bean.setRows(dataList);
        bean.setTotal(total);
        return bean;
    }
    /**
     * 展示会员用户详情
     * mobile不为空表示为全局搜索
     */
    @RequestMapping("memberDetail")
    public Object memberDetail(UserInfoBean userInfoBean, HttpServletRequest request,String reditRemark)throws Exception {
        ModelAndView mv = new ModelAndView();
        Integer userId = userInfoBean.getId();
        if(request.getParameter("mobile") != null && !request.getParameter("mobile").equals("")){
            String mobile = new String(request.getParameter("mobile").getBytes("ISO-8859-1"), "UTF-8");
            List<UserInfoModel> userInfoModel = userInfoService.getMobile(mobile);
            if(userInfoModel != null){
                for(UserInfoModel user : userInfoModel){
                    userId = user.getId();
                }
            }
            else{
                mv.addObject("title","你搜索的用户"+mobile+"不存在");
                mv.setViewName("view/common/404");
                return  mv;
            }
        }
        //所有count   以及诚信值  评价得分
        UserInfoBean userInfoBeanCount = userService.getAllCountById(userId);
        userInfoBean = userInfoService.getUserInfoBean(userId,1);
        //根据userid 查询冻结 deleted
        Integer memberStatus = userInfoService.findDeletedByUserId(userId);
        //获取会员冻结状态  deleted= 0 为黑名单为冻结 deleted = 1不是黑名单 自然不是冻结状态
        userInfoBean.setMemberStatus(memberStatus);

        userInfoBean.setResidentCity(cityService.getCityName(CityUtil.convertToString(userInfoBean.getResidentCityId())));

         //承运数
        userInfoBean.setDriverOrderCount(userInfoBeanCount.getDriverOrderCount());
        //订单数  没有历史订单数
        userInfoBean.setOwnerOrderCount(userInfoBeanCount.getOwnerOrderCount());
        //车辆数
        userInfoBean.setTruckCount(userInfoBeanCount.getTruckCount());
        //评价别人数
        userInfoBean.setCommentCount(userInfoBeanCount.getCommentCount());
        //被评价数
       userInfoBean.setCommentedCount(userInfoBeanCount.getCommentedCount());
        //获取我推荐用户数
        userInfoBean.setRecommendCount(userInfoBeanCount.getRecommendCount());
        //熟车数
        userInfoBean.setOwnerTruckCount(userInfoBeanCount.getOwnerTruckCount());
        //获取推荐我的用户手机号
        userInfoBean.setRecommendUserMobile(userInfoBeanCount.getRecommendUserMobile());
        //诚信值
        userInfoBean.setCredit(userInfoBeanCount.getCredit());
        //评价得分
        userInfoBean.setDriverCommentLevel(userInfoBeanCount.getDriverCommentLevel());
        userInfoBean.setOwnerCommentLevel(userInfoBeanCount.getOwnerCommentLevel());


        UserAuditHistoryModel userAuditHistoryModel = new UserAuditHistoryModel();
        userAuditHistoryModel.setSourceId(userInfoBean.getId());
        userAuditHistoryModel.setSourceType(1);
        mv.setViewName("view/common/memberDetail");
        mv.addObject("ahmList",getOperationLog(userAuditHistoryModel));
        mv.addObject("userInfoBean", userInfoBean);
        String title = new String(request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8");
        mv.addObject("title", title);
        mv.addObject("reditRemark", reditRemark);
        return mv;
    }

    @ResponseBody
    @RequestMapping("updateUserInfo")
    public ResultBaseMsg updateUserInfo(UserInfoBean userInfoBean)throws Exception {
        userInfoService.updateUserInfo(userInfoBean);
       /* try {
            CreditInfoModel creditInfoModel = creditInfoService.creditInfoModelByUserId(userInfoBean.getId());
            creditInfoModel.setUserType(userInfoBean.getType());  //用户类型
            creditInfoService.updateCreditValue(creditInfoModel);
        }catch (Exception e){
            TopJetLog.error("更新信用值出错"+e.getMessage()+userInfoBean.getId());
        }*/
        resultBaseMsg.setStatus(ExceptionEnum.E_0.getStatus());
        resultBaseMsg.setMsg(ExceptionEnum.E_0.getMsg());
        return resultBaseMsg;
    }
    private List<UserAuditHistoryModel> getOperationLog(UserAuditHistoryModel userAuditHistoryModel) {
        userAuditHistoryModel.setSourceId(userAuditHistoryModel.getSourceId());
        List<UserAuditHistoryModel> dataList = userInfoService.getUserOperationLog(userAuditHistoryModel);
        return dataList;
    }
    @ResponseBody
    @RequestMapping("getMemberOperationLog")
    public Object getMemberOperationLog(UserAuditHistoryModel userAuditHistoryModel){
        return this.getOperationLog(userAuditHistoryModel);
    }
    /**
     * 用户冻结
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("frozenOrDefrozen")
    @ResponseBody
    public ResultBaseMsg frozenOrDefrozen(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Integer ubid = Integer.parseInt(request.getParameter("ubid"));
        Integer userId = Integer.parseInt( request.getParameter("id"));
        Integer flag = Integer.parseInt(request.getParameter("flag"));
        String  reditRemark= request.getParameter("reditRemark");
        ResultBaseMsg resultBaseMsg = userInfoService.frozenOrDefrozen(userId,reditRemark,flag,ubid);//update方法
        return  resultBaseMsg;
    }

    /**
     * 查询钱包余额
     */
    @RequestMapping("memberDetailExtend")
    @ResponseBody
    public Object memberDetailExtend(UserInfoBean userInfoBean, HttpServletRequest request) throws Exception {
        //钱包余额
        try {
            GetBalanceRTS getBalanceRTS = new GetBalanceRTS();
            getBalanceRTS.setWalletId(userInfoBean.getWalletId().toString());
            GetBalanceVRU getBalanceVRU = walletFeginService.getBalance(getBalanceRTS);
            if (getBalanceVRU.getCode() == 1) {
                userInfoBean.setWalletBalance(new BigDecimal(getBalanceVRU.getActiveAmount()));
            }
            if (getBalanceVRU == null || getBalanceVRU.getCode() == 0) {
                userInfoBean.setWalletBalance(new BigDecimal(0.00));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return  userInfoBean;
    }


    @RequestMapping("checkMobile")
    @ResponseBody
    public Object checkMobile(UserInfoModel  userInfoModel){
        List<UserInfoModel> list = userInfoService.getListByMobile(userInfoModel.getMobile());
        return list.isEmpty();
    }

    @RequestMapping("updateMobile")
    @ResponseBody
    public ResultBaseMsg updateMobile(UserInfoBean  userInfoBean){
        ResultBaseMsg resultBaseMsg =new ResultBaseMsg();
        userInfoService.updateMobile(userInfoBean);
        resultBaseMsg.setStatus(ExceptionEnum.E_0.getStatus());
        resultBaseMsg.setMsg(ExceptionEnum.E_0.getMsg());
        return resultBaseMsg;
    }

}
