package com.topjet.manage.controller;

import com.topjet.common.SessionUtils;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.common.exception.pms.TopjetExceptionHandler;
import com.topjet.manage.constants.TaskConstants;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.OrderBonusBean;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.bean.TransPortAgentBean;
import com.topjet.manage.domain.model.OrderAuditHistoryModel;
import com.topjet.manage.domain.model.OrderBonusInfoModel;
import com.topjet.manage.domain.model.TaskBucketInfoModel;
import com.topjet.manage.domain.vo.OrderBonusListQuery;
import com.topjet.manage.mapper.readMapper.OrderAuditHistoryModelEMapper;
import com.topjet.manage.mapper.readMapper.OrderBonusInfoModelEMapper;
import com.topjet.manage.service.OrderBonusInfoService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhaocj
 * @Description:订单补贴配置Controller
 * @Date: 2017-10-16 19:48
 */

@Controller
@RequestMapping("/orderBonusInfo")
public class OrderBonusInfoController extends BaseController{

    private static Logger log = LoggerFactory.getLogger(OrderBonusInfoController.class);

    @Autowired
    private OrderBonusInfoService orderBonusInfoService;

    @Autowired
    private OrderAuditHistoryModelEMapper orderAuditHistoryModelEMapper;

    @Autowired
    private OrderBonusInfoModelEMapper orderBonusInfoModelEMapper;

    /**
     * 运费补贴列表列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/listOrderBonusInfo")
    public PaginationBean listOrderBonusInfo(OrderBonusListQuery query){
        return orderBonusInfoService.listOrderBonusInfo(query);
    }


    /**
     * 获取待审核订单补贴列表
     * statusFlag=1代表一审，statusFlag=2代表二审，statusFlag=3代表三审
     * typeFlag=1运费补贴
     * @param orderBonusBean`
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/listAuditOrderBonusInfo")
    public PaginationBean listAuditOrderBonusInfo(OrderBonusBean orderBonusBean , HttpServletRequest request){

        //获取审核标识  statusFlag=1代表一审，statusFlag=2代表二审，statusFlag=3代表三审  typeFlag=1运费补贴
        String statusFlag = request.getParameter("statusFlag");
        String typeFlag = request.getParameter("typeFlag");
        orderBonusBean.setAuditCycle(Integer.valueOf(statusFlag));
        orderBonusBean.setType(typeFlag);

        //判断是否是任务分配者
        List<TaskBucketInfoModel> assignSysUserSession = SessionUtils.getAssignSysUserSession();
        boolean taskFlag = false;
        for (TaskBucketInfoModel taskBucketInfoModel : assignSysUserSession) {
            if(typeFlag.equals("1") && statusFlag.equals("1")){
                if(taskBucketInfoModel.getType().equals(TaskConstants.FRIGHT_FIRST_AUDIT)){
                    taskFlag = true;
                    break;
                }
            }else if(typeFlag.equals("1") && statusFlag.equals("2")){
                if(taskBucketInfoModel.getType().equals(TaskConstants.FRIGHT_SECOND_AUDIT)){
                    taskFlag = true;
                    break;
                }
            }
        }
        if(taskFlag){
            orderBonusBean.setFlag("1");
            orderBonusBean.setSysUserId(assignSysUserSession.get(0).getSysUserId()+"");
        }
        return orderBonusInfoService.listAuditOrderBonusInfo(orderBonusBean);
    }


    @ResponseBody
    @RequestMapping("/auditOrderBonusInfo")
    public Object auditOrderBonusInfo(OrderBonusBean orderBonusBean, HttpServletRequest request) throws TopjetExceptionHandler {
        TransPortAgentBean transPortAgentBean = new TransPortAgentBean();

        log.info("开始审核运费补贴，运费补贴id:" + orderBonusBean.getId());
        String driverId = null;
        String ownerId = null;
        String firstAuditRemark = null;
        String firstAuditStatus = null;
        String checkedItems = request.getParameter("checkedItems");
        Integer version = null;
        Integer id = null;
        if (StringUtils.isEmpty(checkedItems)) {
            id = Integer.parseInt(request.getParameter("id"));
            version = Integer.parseInt(request.getParameter("version"));
            driverId = request.getParameter("driverId");
            ownerId = request.getParameter("ownerId");
            firstAuditRemark = request.getParameter("firstAuditRemark");
            firstAuditStatus = request.getParameter("firstAuditStatus");
        }
        if (StringUtils.isNotBlank(firstAuditRemark) && StringUtils.isNotBlank(firstAuditStatus)) {
            orderBonusBean.setFirstAuditStatus(firstAuditStatus);
            orderBonusBean.setFirstAuditRemark(firstAuditRemark);
            orderBonusBean.setVersion(version);
        }
        String secondAuditRemark = request.getParameter("secondAuditRemark");
        String secondAuditStatus = request.getParameter("secondAuditStatus");
        if (StringUtils.isNotBlank(secondAuditRemark) && StringUtils.isNotBlank(secondAuditStatus)) {
            orderBonusBean.setSecondAuditStatus(secondAuditStatus);
            orderBonusBean.setSecondAuditRemark(secondAuditRemark);
        }
        String thirdAuditRemark = request.getParameter("thirdAuditRemark");
        String thirdAuditStatus = request.getParameter("thirdAuditStatus");
        if (StringUtils.isNotBlank(thirdAuditStatus) && StringUtils.isNotBlank(thirdAuditRemark)) {
            orderBonusBean.setThirdAuditStatus(thirdAuditStatus);
            orderBonusBean.setThirdAuditRemark(thirdAuditRemark);
        }
        orderBonusBean.setId(id);
        if (StringUtils.isNotBlank(driverId)) orderBonusBean.setDriverId(Integer.parseInt(driverId));
        if (StringUtils.isNotBlank(ownerId)) orderBonusBean.setOwnerId(Integer.parseInt(ownerId));
        orderBonusBean.setOrderBonusVersion(version);
        JSONArray jsonArray = JSONArray.fromObject(checkedItems);
        int total = jsonArray.size();
        StringBuffer returnMessage = new StringBuffer();
        int failCount = 0;
        OrderBonusBean resultOrderBonusBean = null;
        if (StringUtils.isNotBlank(checkedItems)) {
            for (int i = 0; i < total; i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                orderBonusBean.setOrderBonusVersion(jsonObj.getInt("orderBonusVersion"));
                orderBonusBean.setDriverId(jsonObj.getInt("driverId"));
                orderBonusBean.setOwnerId(jsonObj.getInt("ownerId"));
                orderBonusBean.setBonusOrderId(jsonObj.getInt("bonusOrderId"));
                orderBonusBean.setId(jsonObj.getInt("id"));
                orderBonusBean.setSecondAuditStatus(secondAuditStatus);
                orderBonusBean.setThirdAuditStatus(thirdAuditStatus);
                resultOrderBonusBean = orderBonusInfoService.auditOrderBonus(orderBonusBean);

                //审核运费补贴
                resultOrderBonusBean = new OrderBonusBean();
                if (!org.apache.commons.lang.StringUtils.isEmpty(resultOrderBonusBean.getReturnMessage())) {
                    returnMessage.append(resultOrderBonusBean.getReturnMessage() + "|");
                }
                if (resultOrderBonusBean.getFailCount() != null && resultOrderBonusBean.getFailCount() > 0) {
                    failCount++;
                }
            }
        } else {
            resultOrderBonusBean = orderBonusInfoService.auditOrderBonus(orderBonusBean);
            if (StringUtils.isNotBlank(resultOrderBonusBean.getReturnMessage())) {
                    String message = resultOrderBonusBean.getReturnMessage();
                    message.replace("|", "");
                    returnMessage.append(message);
            }
        }
        resultOrderBonusBean.setFailCount(failCount);
        resultOrderBonusBean.setSuccessCount(total - failCount);
        resultOrderBonusBean.setReturnMessage(returnMessage.toString());
        transPortAgentBean.setOrderBonusBean(resultOrderBonusBean);
        transPortAgentBean.setMsg(ExceptionEnum.E_0.getMsg());
        transPortAgentBean.setStatus(ExceptionEnum.E_0.getStatus());
        return transPortAgentBean;

    }

    @ResponseBody
    @RequestMapping("/getAuditHistory")
    public Object getAuditHistory(OrderBonusInfoModel orderBonusInfoModel){
        return orderAuditHistoryModelEMapper.listOrderAuditHistory(orderBonusInfoModel.getId());
    }

    @ResponseBody
    @RequestMapping("/getBonusAudit")
    public Object getBonusAudit(HttpServletRequest request) throws TopjetExceptionHandler{
        TransPortAgentBean resultBean = new TransPortAgentBean();

        String id = request.getParameter("id");
        OrderBonusInfoModel orderBonusInfoModel = orderBonusInfoModelEMapper.selectByPrimaryKey(Integer.parseInt(id));
        if(orderBonusInfoModel != null){
            List<OrderAuditHistoryModel> orderAuditHistoryModels = orderAuditHistoryModelEMapper.listOrderAuditHistory(orderBonusInfoModel.getId());
            if(orderAuditHistoryModels != null && orderAuditHistoryModels.size()>0){
                for (OrderAuditHistoryModel orderAuditHistoryModel : orderAuditHistoryModels) {
                    if(orderAuditHistoryModel.getSourceType()==3){
                        resultBean.setFirstAuditRemark(orderAuditHistoryModel.getRemark());
                        resultBean.setFirstAuditUser(orderAuditHistoryModel.getAuditName());
                        switch (orderAuditHistoryModel.getStatusAfter()){
                            case 3: resultBean.setFirstAuditStatus("待定"); break;
                            case 4: resultBean.setFirstAuditStatus("审核通过");break;
                            case 5: resultBean.setFirstAuditStatus("审核驳回");break;
                        }
                    }else if(orderAuditHistoryModel.getSourceType()==4){
                        resultBean.setSecondAuditRemark(orderAuditHistoryModel.getRemark());
                        resultBean.setSecondAuditUser(orderAuditHistoryModel.getAuditName());
                        switch (orderAuditHistoryModel.getStatusAfter()){
                            case 4: resultBean.setSecondAuditStatus("审核通过");break;
                            case 5: resultBean.setSecondAuditStatus("审核驳回");break;
                        }
                    }
                }
            }
        }
        resultBean.setStatus(ExceptionEnum.E_0.getStatus());
        resultBean.setMsg(ExceptionEnum.E_0.getMsg());
        return resultBean;
    }


}
