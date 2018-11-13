package com.topjet.manage.controller;

import com.topjet.common.ExceptionEnum;
import com.topjet.common.ResultBaseMsg;
import com.topjet.common.SessionUtils;
import com.topjet.common.exception.pms.TopjetExceptionHandler;
import com.topjet.manage.common.SysMail;
import com.topjet.manage.constants.BaseAdminConstant;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.bean.SysUserBean;
import com.topjet.manage.domain.model.SysRoleModel;
import com.topjet.manage.domain.model.SysUserModel;
import com.topjet.manage.domain.model.SysUserRoleRelModel;
import com.topjet.manage.domain.model.TaskBucketInfoModel;
import com.topjet.manage.mapper.readMapper.TaskBucketInfoModelEMapper;
import com.topjet.manage.mapper.writeMapper.SysUserRoleRelModelMapper;
import com.topjet.manage.mapper.writeMapper.TaskBucketInfoModelMapper;
import com.topjet.manage.service.SysRoleService;
import com.topjet.manage.service.SysUserRoleRelService;
import com.topjet.manage.service.SysUserService;
import com.topjet.manage.service.TaskAssignService;
import com.topjet.tool.common.util.DateUtil;
import com.topjet.util.MethodUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.topjet.common.SessionUtils.getSysUserSession;

/**
 * @author zhaocj
 * @create 2017-08-05 10:50
 **/
@Controller
@RequestMapping("/sysUser")
public class SysUserController extends BaseController{

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserRoleRelService sysUserRoleRelService;

    @Autowired
    private SysUserRoleRelModelMapper sysUserRoleRelModelMapper;

    @Autowired
    private TaskAssignService taskAssignService;



    @RequestMapping("list")
    @ResponseBody
    public Object list(SysUserModel sysUserModel, HttpServletRequest request) throws Exception {
        SysUserModel user = getSysUserSession();
        Integer total=0;
        List<SysUserModel> dataList=new ArrayList<>();
        if(user.getSuperAdmin().equals(BaseAdminConstant.IS_SUPER_ADMIN_TRUE)){
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("offset", sysUserModel.getOffset());
            paramMap.put("limit", sysUserModel.getRows());
            dataList = sysUserService.listPageSysUser(paramMap);
            total = sysUserService.countSysUser(paramMap);
        }else {
            sysUserModel.setCreateBy(user.getId());
            dataList = sysUserService.selectAllRecord(sysUserModel);
            total=sysUserService.countSelectAllRecord(user.getId());
        }
        List<SysUserBean> resultList = new ArrayList<SysUserBean>();
        if (dataList != null && dataList.size() > 0) {
            for (SysUserModel userModel : dataList) {
                if(userModel.getSuperAdmin().equals(BaseAdminConstant.IS_SUPER_ADMIN_TRUE)){
                    total=total-1;
                    continue;
                }
                SysUserBean userBean = new SysUserBean();
                userBean.setId(userModel.getId());
                userBean.setEmail(userModel.getEmail());
                userBean.setPwd(userModel.getPwd());
                userBean.setNickName(userModel.getNickName());
                userBean.setState(userModel.getState());
                userBean.setLoginCount(userModel.getLoginCount());
                userBean.setLoginTime(userModel.getLoginTime());
                userBean.setDeleted(userModel.getDeleted());
                userBean.setCreateTime(userModel.getCreateTime());
                userBean.setUpdateTime(userModel.getUpdateTime());
                userBean.setCreateBy(userModel.getCreateBy());
                userBean.setUpdateBy(userModel.getUpdateBy());
                userBean.setSuperAdmin(userModel.getSuperAdmin());

                List<SysRoleModel> list = sysRoleService.queryByUserId(userModel.getId());
                userBean.setRoleStr(rolesToStr(list));
                resultList.add(userBean);
            }
        }

        PaginationBean beans = new PaginationBean();
        beans.setRows(resultList);
        beans.setTotal(total);
        return beans;
    }
    /*
     * @author        :  zhaocj
     * @Create Date   :  2017年8月5日
    * @function	   :	用户详情
    * */
    @RequestMapping("userDetail")
    public ModelAndView update(Integer id) {

        ModelAndView view = new ModelAndView("view/sysAdmin/sysUser/userDetail");

        if (id != null) {
            SysUserModel user = sysUserService.selectByPrimaryKey(id);
            view.addObject("user", user);
        }

        List<SysRoleModel> roleDataList=new ArrayList<>();

        if(!getSysUserSession().getSuperAdmin().equals(BaseAdminConstant.IS_SUPER_ADMIN_TRUE)){
            Map<String,Object> paraMap = new HashMap<String,Object>();
            paraMap.put("sysUserId", getSysUserSession().getId());
            List<SysUserRoleRelModel> sysRoleRelList = sysUserRoleRelService.selectListByParam(paraMap);

            if(!sysRoleRelList.isEmpty()){
                SysRoleModel model=sysRoleService.selectByPrimaryKey(sysRoleRelList.get(0).getRoleId());
                roleDataList.add(model);
            }
        }else {
            Map<String,Object> paraMap = new HashMap<String,Object>();
            roleDataList = sysRoleService.selectListByParam(paraMap);
        }
        view.addObject("roleDataList", roleDataList);

        int currentOperatorId = getSysUserSession().getId();
        //当前用户为部门Leader取当前部门信息
        if (getSysUserSession().getSuperAdmin() == 2) {
            id = currentOperatorId;
        }

        List<SysRoleModel> list = sysRoleService.queryByUserId(id);
        if (list != null && list.size() > 0) {
            SysRoleModel SysRoleV2Model = list.get(0);
            view.addObject("currRole", SysRoleV2Model);
        }
        view.addObject("currUser", getSysUserSession());

        return view;
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Integer id) throws Exception {
        SysUserModel model=sysUserService.selectByPrimaryKey(id);
        if(!SessionUtils.getSysUserSession().getSuperAdmin().equals(BaseAdminConstant.IS_SUPER_ADMIN_TRUE) && !model.getCreateBy().equals(SessionUtils.getSysUserSession().getId())){
            msg.setStatus(ExceptionEnum.E_23.getStatus());
            msg.setMsg(ExceptionEnum.E_23.getMsg());
            return msg;
        }
        List<Integer> updateUser=sysUserService.getUserId(id);
        if(!updateUser.isEmpty()){
            msg.setStatus(ExceptionEnum.E_24.getStatus());
            msg.setMsg(ExceptionEnum.E_24.getMsg());
            return msg;
        }
        //删除用户
        sysUserService.deleteByPrimarykey(id);
        //删除权限
        //删除部门关联关系
        sysUserRoleRelService.deleteByUserId(id);
        //删除用户下所分配的任务，taskBucketInfo, taskInfo
        taskAssignService.deleteTaskBySysUserId(model.getId());

        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
    }

    /*
       * @author        :  zhaocj
       * @Create Date   :  2017年8月9日
       * @function	   :	保存用户信息
       * */
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public ResultBaseMsg saveOrUpdate(SysUserModel user, Integer roleId, Integer[] menuIds) throws TopjetExceptionHandler, Exception {
        int count = sysUserService.getUserCountByEmail(user.getEmail());
        int userId;
        if (user.getId() == null) {
            if (count > 0) {
                throw new TopjetExceptionHandler(ExceptionEnum.E_1.getStatus(), ExceptionEnum.E_1.getMsg());
            }
            user.setCreateBy(getSysUserSession().getId());
            user.setUpdateBy(getSysUserSession().getId());
            user.setCreateTime(DateUtil.now());
            user.setLoginCount(0);
            user.setDeleted(BaseAdminConstant.TABLE_COLUMN_DELETE);
            user.setSuperAdmin(BaseAdminConstant.IS_SUPER_ADMIN_FALSE);
            user.setUpdateTime(DateUtil.now());
            if (StringUtils.isBlank(user.getPwd())) {
                user.setPwd("123456");
            }
            String content = "用户登录名:" + user.getEmail() + " 用户登录密码:" + user.getPwd();
            user.setPwd(MethodUtil.MD5(user.getPwd()));
            user.setVersion(1);
            sysUserService.insertAndGetId(user);

            userId = user.getId();
            String consigneeAddress = user.getEmail();
            String title = "560管理后台新建用户成功";
            String copyto = "";
            SysMail.sendMail(content, consigneeAddress, title, copyto);

        } else {

            SysUserModel userUpdate = sysUserService.selectByPrimaryKey(user.getId());
            if (count > 0 && !userUpdate.getEmail().equals(user.getEmail())) {
                throw new TopjetExceptionHandler(ExceptionEnum.E_1.getStatus(), ExceptionEnum.E_1.getMsg());
            }

            if (!getSysUserSession().getSuperAdmin().equals(BaseAdminConstant.IS_SUPER_ADMIN_TRUE) && !userUpdate.getCreateBy().equals(getSysUserSession().getId())) {
                msg.setStatus(ExceptionEnum.E_23.getStatus());
                msg.setMsg(ExceptionEnum.E_23.getMsg());
                return msg;
            }
            if (!StringUtils.isBlank(user.getPwd())) {
                userUpdate.setPwd(MethodUtil.MD5(user.getPwd()));
            }
            userUpdate.setEmail(user.getEmail());
            userUpdate.setNickName(user.getNickName());
            userUpdate.setState(user.getState());
            userUpdate.setUpdateBy(getSysUserSession().getId());
            userUpdate.setUpdateTime(DateUtil.now());
            sysUserService.updateByPrimaryKey(userUpdate);

            userId = userUpdate.getId();

        }

        //设置部门
        Map<String, Object> paraMap = new HashMap<String,Object>();
        paraMap.put("sysUserId",userId);
        List<SysUserRoleRelModel> sysRoleRelList = sysUserRoleRelService.selectListByParam(paraMap);

        if (sysRoleRelList.size() > 0) {
            SysUserRoleRelModel sysUserRoleRelModel = sysRoleRelList.get(0);
            sysUserRoleRelModel.setRoleId(roleId);
            sysUserRoleRelModelMapper.update(sysUserRoleRelModel);
        } else {
            SysUserRoleRelModel sysRoleRelModel = new SysUserRoleRelModel();
            sysRoleRelModel.setRoleId(roleId);
            sysRoleRelModel.setSysUserId(userId);
            sysUserRoleRelModelMapper.insert(sysRoleRelModel);
        }


        //线下认证白名单权限
           /* WhiteUserModelCriteria userModelCriteria=new WhiteUserModelCriteria();
            userModelCriteria.createCriteria().andMobileEqualTo(user.getEmail()).andTypeEqualTo(2).andUserIdEqualTo(userId);
            List<WhiteUserModel> whiteUserModels=whiteUserService.selectByCriteria(userModelCriteria);

            if(IsWhiteUser){
            if(whiteUserModels.isEmpty()){
                WhiteUserModel whiteUserModel=new WhiteUserBean();
                whiteUserModel.setType(2);
                whiteUserModel.setCreateBy(getSysUserSession().getId());
                whiteUserModel.setCreateTime(DateUtil.now());
                whiteUserModel.setMobile(user.getEmail());
                whiteUserModel.setUserId(userId);
                whiteUserModel.setLoginCount(0);
                whiteUserService.insertSelective(whiteUserModel);
            }else {
                WhiteUserModel whiteUserModel=whiteUserModels.get(0);
                whiteUserModel.setDeleted(CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
                whiteUserModel.setUpdateBy(getSysUserSession().getId());
                whiteUserModel.setUpdateTime(DateUtil.now());
                whiteUserService.updateByPrimaryKeySelective(whiteUserModel);
            }

          }else {
                if(!whiteUserModels.isEmpty()){
                    WhiteUserModel whiteUserModel=whiteUserModels.get(0);
                    whiteUserModel.setDeleted(CommonConstant.DB_RECORD_DELETED_STATUS_TURE);
                    whiteUserModel.setUpdateBy(getSysUserSession().getId());
                    whiteUserModel.setUpdateTime(DateUtil.now());
                    whiteUserService.updateByPrimaryKeySelective(whiteUserModel);
                }
            }*/

        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
    }

    /**
     * 校验用户邮箱是否存在
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkEmail")
    public Map<String,Object> checkEmail(@RequestBody String email , HttpServletRequest request){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("email", email);
        paramMap.put("deleted", 0);
        List<SysUserModel> sysUserModels = sysUserService.selectSysUser(paramMap);
        if(sysUserModels != null && sysUserModels.size() > 0){
            resultMap.put("success", "false");
        }else{
            resultMap.put("success","true");
        }
        return resultMap;

    }




    /**
     * 角色列表转成字符串
     *
     * @param list
     * @return
     */
    private String rolesToStr(List<SysRoleModel> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            SysRoleModel role = list.get(i);
            str.append(role.getRoleName());
            if ((i + 1) < list.size()) {
                str.append(",");
            }
        }
        return str.toString();
    }

}
