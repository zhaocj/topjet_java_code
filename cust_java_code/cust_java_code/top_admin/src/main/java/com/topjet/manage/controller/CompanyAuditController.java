package com.topjet.manage.controller;

import com.topjet.common.ResultBaseMsg;
import com.topjet.common.SessionUtils;
import com.topjet.manage.constants.TaskConstants;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.CompanyAuditBean;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.bean.UserInfoBean;
import com.topjet.manage.domain.model.TaskBucketInfoModel;
import com.topjet.manage.domain.model.UserAuditHistoryModel;
import com.topjet.manage.mapper.readMapper.UserAuditHistoryModelEMapper;
import com.topjet.manage.service.UserInfoService;
import com.topjet.user.constant.AuditHistoryConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tsj010 on 2018/5/7.
 */
@Controller
@RequestMapping("/companyAudit/")
public class CompanyAuditController extends BaseController {

    @Autowired
    private UserInfoService    userInfoService;
    @Autowired
    private UserAuditHistoryModelEMapper userAuditHistoryModelEMapper;






    @ResponseBody
    @RequestMapping("companyAuditList")
    public Object companyAuditList(CompanyAuditBean companyAuditBean) {
        PaginationBean bean = new PaginationBean();
        List<TaskBucketInfoModel> taskUser = SessionUtils.getAssignSysUserSession();//是否为分配任务者
        boolean taskFlag = false;
        if (taskUser != null && taskUser.size() > 0) {
            for (TaskBucketInfoModel taskBucketInfoModel : taskUser) {
                if (taskBucketInfoModel.getType().equals(TaskConstants.COMPANY_USER_AUDIT)) {
                    taskFlag = true;
                    break;
                }
            }
        }
        if (taskFlag) {
            companyAuditBean.setSysUserId(taskUser.get(0).getSysUserId().toString());
        }
        List<CompanyAuditBean> CompanyAuditBeanList = userInfoService.companyAuditList(companyAuditBean);
        int total = userInfoService.getCompanyAuditCount(companyAuditBean);
        bean.setRows(CompanyAuditBeanList);
        bean.setTotal(total);
        return bean;
    }


    //详情
    @RequestMapping("companyAuditDetail")
    public ModelAndView companyAuditDetailInfo(CompanyAuditBean companyAuditBean) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("view/companyAudit/CompanyAuditDetail");
        UserInfoBean  userInfoBean = userInfoService.getCompanyUserInfo(companyAuditBean);
        UserAuditHistoryModel userAuditHistoryModel = new UserAuditHistoryModel();
        userAuditHistoryModel.setSourceId(userInfoBean.getId());
        List<UserAuditHistoryModel> dataOne = this.getCompanyOperation(userAuditHistoryModel);
        mv.addObject("userInfoBean", userInfoBean);
        mv.addObject("UserAuditHistoryList", dataOne);
        mv.addObject("type", "企业认证审核");
        return mv;
    }

    //详情
    @RequestMapping("tmsDetail")
    public ModelAndView tmsDetail(CompanyAuditBean companyAuditBean) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("view/companyAudit/CompanyDetail");
        UserInfoBean  userInfoBean = userInfoService.getCompanyUserInfo(companyAuditBean);
        UserAuditHistoryModel userAuditHistoryModel = new UserAuditHistoryModel();
        userAuditHistoryModel.setSourceId(userInfoBean.getId());
        List<UserAuditHistoryModel> dataOne = this.getCompanyOperation(userAuditHistoryModel);
        mv.addObject("userInfoBean", userInfoBean);
        mv.addObject("UserAuditHistoryList", dataOne);
        mv.addObject("type", "企业认证审核");
        return mv;
    }




    /*
     * 企业认证审核
     * */
    @RequestMapping("updateCompany")
    @ResponseBody
    public ResultBaseMsg updateCompanyDetail(UserInfoBean userInfoBean, HttpServletRequest request) throws Exception {
        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();
        try {
            resultBaseMsg = userInfoService.updateCompanyDetail(userInfoBean, request);//企业认证审核
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultBaseMsg;
    }

    /*
        * 企业维护提交
        * */
    @RequestMapping("updateDetail")
    @ResponseBody
    public ResultBaseMsg updateDetail(UserInfoBean userInfoBean, HttpServletRequest request) throws Exception {
        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();
        try {
            resultBaseMsg = userInfoService.updateDetail(userInfoBean, request);//企业维护
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultBaseMsg;
    }


    //企业认证审核操作日志
    private List<UserAuditHistoryModel> getCompanyOperation(UserAuditHistoryModel auditHistoryModel) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sourceId", auditHistoryModel.getSourceId());
        paramMap.put("sourceType", AuditHistoryConstant.AUDIT_COMPANY);
        paramMap.put("deleted", 0);
        List<UserAuditHistoryModel> dataList = userAuditHistoryModelEMapper.listHistory(paramMap);
        return dataList;
    }

}
