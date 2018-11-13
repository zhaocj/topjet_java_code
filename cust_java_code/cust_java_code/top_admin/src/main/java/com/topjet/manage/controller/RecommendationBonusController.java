package com.topjet.manage.controller;

import com.topjet.common.ResultBaseMsg;
import com.topjet.common.SessionUtils;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.constants.TaskConstants;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.CheckMobileAndNameResponseDTO;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.bean.RecommendationBonusBean;
import com.topjet.manage.domain.bean.RecommendationBonusResponseBean;
import com.topjet.manage.domain.model.RecommendationBonusInfoModel;
import com.topjet.manage.domain.model.TaskBucketInfoModel;
import com.topjet.manage.domain.model.UserAuditHistoryModel;
import com.topjet.manage.service.CheckMobileAndNameService;
import com.topjet.manage.service.RecommendationBonusInfoService;
import com.topjet.manage.service.UserAuditHistoryService;
import com.topjet.user.constant.AuditHistoryConstant;
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
import java.util.List;

/**
 * @Author: zhaocj
 * @Description: 推荐补贴controller
 * @Date: 2017-08-28 10:35
 */

@Controller
@RequestMapping("/recommendationBonus")
public class RecommendationBonusController extends BaseController{

    private final static Logger log = Logger.getLogger(RecommendationBonusController.class);

    @Autowired
    private RecommendationBonusInfoService recommendationBonusInfoService;

    @Autowired
    private UserAuditHistoryService userAuditHistoryService;

    @Autowired
    private CheckMobileAndNameService checkMobileAndNameService;


    @RequestMapping("/list")
    @ResponseBody
    public Object dataFirstList(RecommendationBonusBean recommendationBonusBean, HttpServletRequest rq) throws Exception {
        String statusFlag = rq.getParameter("statusFlag");
        recommendationBonusBean.setAuditCycle(Integer.valueOf(statusFlag));//1代表一审，2代表二审
        List<TaskBucketInfoModel> taskUser = SessionUtils.getAssignSysUserSession();//是否为分配任务者

        boolean taskFlag = false;
        if(taskUser != null && taskUser.size() > 0){
            for(TaskBucketInfoModel tb : taskUser){
                if(statusFlag.equals("1") ){
                    if(tb.getType().equals(TaskConstants.RECO_FIRST_AUDIT)){
                        taskFlag = true;
                        break;
                    }
                }else if(statusFlag.equals("2")){
                    if(tb.getType().equals(TaskConstants.RECO_SECOND_AUDIT)){
                        taskFlag = true;
                        break;
                    }
                }

            }
        }

        if(taskFlag){
            recommendationBonusBean.setFlag("1");
            recommendationBonusBean.setSysUserId(taskUser.get(0).getSysUserId().toString());
        }
        List<RecommendationBonusBean> dataList = recommendationBonusInfoService.listRecommendationBonusInfo(recommendationBonusBean);
        int total = recommendationBonusInfoService.queryByCount(recommendationBonusBean);
        PaginationBean bean = new PaginationBean();
        bean.setRows(dataList);
        bean.setTotal(total);
        return bean;
    }


    /**
     * 通过sourceID获取手机三元素审核历史
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("/getMatchCode")
    @ResponseBody
    public Object getMatchCode(HttpServletRequest request) throws Exception {
        UserAuditHistoryModel userAuditHistoryModel = new UserAuditHistoryModel();
        userAuditHistoryModel.setSourceId(Integer.valueOf(request.getParameter("bonusId")));
        userAuditHistoryModel.setDeleted(0);
        userAuditHistoryModel.setSourceType(AuditHistoryConstant.AUDIT_CHECK_ID);
        List<UserAuditHistoryModel> userAuditHistoryModels = userAuditHistoryService.listUserAuditHistory(userAuditHistoryModel);
        if(!userAuditHistoryModels.isEmpty()){
            return  userAuditHistoryModels.get(userAuditHistoryModels.size()-1);
        }else {
            return "";
        }
    }

    /**
     * 手机三元素审核
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("/CheckIdcardAndMobile")
    @ResponseBody
    public Object addBlackList(HttpServletRequest request) throws Exception {
        String recommendedName = request.getParameter("recommendedName");
        String recommendedMobile =request.getParameter("recommendedMobile");
        String recommendedIdNo = request.getParameter("recommendedIdNo");
        String bonusId = request.getParameter("bonusId");
        //TODO 手机三元素审核
        //校验手机号 姓名 身份证号
        CheckMobileAndNameResponseDTO responseDTO = checkMobileAndNameService.checkMobileAndName(recommendedName,recommendedMobile,recommendedIdNo);
        Integer matchCode =-1;
        if(!responseDTO.getMatchcode().equals("")){
            matchCode = Integer.valueOf( responseDTO.getMatchcode());
        }
        else{
            responseDTO.setMatchcode("1");
        }

        UserAuditHistoryModel ahm = new UserAuditHistoryModel();

        //TODO  审核人信息写日志
        ahm.setSourceType(AuditHistoryConstant.AUDIT_CHECK_ID);//手机三元素审核
        ahm.setSourceId(Integer.valueOf(bonusId));//
        ahm.setStatusAfter(matchCode);//审核的状态
        ahm.setRemark("");
        ahm.setCreateBy(SessionUtils.getSysUserSession().getId());
        ahm.setAuditName(SessionUtils.getSysUserSession().getNickName());
        ahm.setCreateTime(DateUtil.now());
        userAuditHistoryService.insert(ahm);

        return responseDTO;
    }

    /*
		* @author        :  zhaocj
		* @Create Date   :  2017年8月30日
	   * @function		 :	审核
	   * */
    @RequestMapping("/audit")
    @ResponseBody
    public Object audit(RecommendationBonusBean recommendationBonusBean, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String checkedItems = request.getParameter("checkedItems");

        JSONArray jsonArray = JSONArray.fromObject(checkedItems);
        int total = jsonArray.size();
        int failCount = 0;
        RecommendationBonusResponseBean returnRecommendationBonusBean = null;
        if (!StringUtils.isEmpty(checkedItems)) {
            for (int i = 0; i < total; i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                recommendationBonusBean.setVersion(jsonObj.getInt("version"));
                recommendationBonusBean.setId(jsonObj.getInt("id"));
                recommendationBonusBean.setSecondAuditStatus(request.getParameter("secondAuditStatus"));
                recommendationBonusBean.setThirdAuditStatus(request.getParameter("thirdAuditStatus"));
                returnRecommendationBonusBean = recommendationBonusInfoService.audit(recommendationBonusBean);
                if (returnRecommendationBonusBean.getFailCount() != null && returnRecommendationBonusBean.getFailCount() > 0) {
                    failCount++;
                }
            }
        } else {
            returnRecommendationBonusBean = recommendationBonusInfoService.audit(recommendationBonusBean);
            returnRecommendationBonusBean.setMsg(ExceptionEnum.E_0.getMsg());
        }
        returnRecommendationBonusBean.setFailCount(failCount);
        returnRecommendationBonusBean.setSuccessCount(total - failCount);
        return returnRecommendationBonusBean;
    }


    /**
     *记录回访次数
     */
    @RequestMapping("/callCount")
    @ResponseBody
    public ResultBaseMsg callCount(RecommendationBonusInfoModel re, HttpServletRequest rq){
        List<RecommendationBonusInfoModel> data = recommendationBonusInfoService.selectRecommendationBonusInfo(re);

        int auditCycle = Integer.parseInt(rq.getParameter("statusFlag"));
        if(data.isEmpty()){
            msg.setMsg(ExceptionEnum.E_DATA_EXPIRED.getMsg());
            msg.setStatus(ExceptionEnum.E_DATA_EXPIRED.getStatus());
            return msg;
        }else{
            if(auditCycle ==1){//一审
                re.setFirstCallCount(data.get(0).getFirstCallCount()+1);
            }else if(auditCycle ==2){
                re.setSecondCallCount(data.get(0).getSecondCallCount()+1);
            }else{
                re.setPendingCallCount(data.get(0).getPendingCallCount()+1);
            }
            re.setVersion(data.get(0).getVersion()+1);
            recommendationBonusInfoService.updateByPrimaryKey(re);
            msg.setStatus(ExceptionEnum.E_0.getStatus());
            msg.setMsg(ExceptionEnum.E_0.getMsg());
        }
        return msg;
    }

    /**
     * 查询三审列表
     * @param recommendationBonusBean
     * @param rq
     * @return
     * @throws Exception
     */
    @RequestMapping("/pendingList")
    @ResponseBody
    public Object pendingList(RecommendationBonusBean recommendationBonusBean, HttpServletRequest rq) throws Exception {
        List<RecommendationBonusBean> dataList = recommendationBonusInfoService.queryByPendingList(recommendationBonusBean);
        int total = recommendationBonusInfoService.queryByPendingCount(recommendationBonusBean);
        PaginationBean bean = new PaginationBean();
        bean.setRows(dataList);
        bean.setTotal(total);
        return bean;
    }
}
