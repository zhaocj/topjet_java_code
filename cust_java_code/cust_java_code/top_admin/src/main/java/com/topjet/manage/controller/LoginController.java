package com.topjet.manage.controller;

import com.topjet.common.ExceptionEnum;
import com.topjet.common.SessionUtils;
import com.topjet.common.constants.SystemConfig;
import com.topjet.manage.common.dynamicpassword.bean.SysUserResonseBean;
import com.topjet.manage.common.dynamicpassword.constant.DynamicPasswordConstant;
import com.topjet.manage.common.dynamicpassword.service.DynamicPasswordService;
import com.topjet.manage.domain.bean.RemindBean;
import com.topjet.manage.domain.bean.TreeNode;
import com.topjet.manage.domain.model.*;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.mapper.readMapper.CallCenterUserInfoModelEMapper;
import com.topjet.manage.mapper.readMapper.SysUserModelEMapper;
import com.topjet.manage.mapper.readMapper.TruckLengthDictionaryModelEMapper;
import com.topjet.manage.mapper.readMapper.TruckTypeDictionaryModelEMapper;
import com.topjet.manage.mapper.writeMapper.CallCenterUserInfoModelMapper;
import com.topjet.manage.mapper.writeMapper.SysUserModelMapper;
import com.topjet.manage.service.SysMenuService;
import com.topjet.manage.service.SysUserService;
import com.topjet.manage.service.UserService;
import com.topjet.tool.common.util.DateUtil;
import com.topjet.util.MethodUtil;
import com.topjet.util.TreeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaocj
 * @create 2017-08-01 13:26
 **/
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    private SysUserModelMapper sysUserModelMapper;

    @Autowired
    private SysUserModelEMapper sysUserModelEMapper;

    @Resource
    private SysMenuService sysMenuService;

    @Autowired
    private UserService userService;

    @Autowired
    private SysUserService sysUserService;

    @Resource
    private DynamicPasswordService dynamicPasswordService;

    @Autowired
    private CallCenterUserInfoModelMapper callCenterUserInfoModelMapper;

    @Autowired
    private CallCenterUserInfoModelEMapper callCenterUserInfoModelEMapper;

    @Autowired
    private TruckTypeDictionaryModelEMapper truckTypeDictionaryModelEMapper;

    @Autowired
    private TruckLengthDictionaryModelEMapper truckLengthDictionaryModelEMapper;

    private String verfiedPassword = "verfiedPassword";


    @ResponseBody
    @RequestMapping("/login")
    public Object login(String userName, String password, HttpServletResponse response) {
        SysUserModel user = null;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("email", userName);
        paramMap.put("pwd", MethodUtil.MD5(password));
        List<SysUserModel> userList = sysUserModelEMapper.selectListByParam(paramMap);
        if (userList != null && userList.size() > 0) {
            user = userList.get(0);
        }
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        if (user != null) {
            if (user.getState() == 1) {
                msg.setStatus(ExceptionEnum.E_12.getStatus());
                msg.setMsg(ExceptionEnum.E_12.getMsg());
                return msg;
            } else {
                SessionUtils.setSysUserSession(user);

                SessionUtils.setTruckType(truckTypeDictionaryModelEMapper.listTruckType());

                SessionUtils.setTruckLength(truckLengthDictionaryModelEMapper.listTruckLength());
                if (systemConfig.getDynamicPassWordSwitch()==1) {
                    SessionUtils.setAttr("userId", user.getId());
                    SysUserResonseBean sysUserResponseBean = sysUserService.getDynamicPasswordRQ(getRequest().getSession().getServletContext().getRealPath("/"));

                    Map<String, Object> result = new HashMap<String, Object>();

                    if (sysUserResponseBean == null) {
                        result.put("userId", user.getId());
                        result.put("msgs", "2");
                        result.put("RQCode", null);
                    } else if (sysUserResponseBean.getIsBindSuccess() != null && sysUserResponseBean.getIsBindSuccess().intValue() == 1) {
                        String path = DynamicPasswordConstant.PATH + user.getId() + "QR_Code.JPG";
                        if (path.contains(user.getId().toString())) {
                            result.put("userId", user.getId());
                            result.put("msgs", sysUserResponseBean.getDynamicpasswordBind().toString());
                            result.put("RQCode", DynamicPasswordConstant.VISITURL + user.getId() + "QR_Code.JPG");
                            //PrintWriterUtil.writer(response,result);

                        }
                    } else {
                        result.put("userId", user.getId());
                        result.put("msgs", sysUserResponseBean.getDynamicpasswordBind().toString());
                        result.put("RQCode", null);
                        //PrintWriterUtil.writer(response, sysUserResponseBean.getDynamicpasswordBind().toString());
                    }
                    return result;
                }

                user.setLoginTime(DateUtil.now());
                user.setUpdateTime(DateUtil.now());
                user.setLoginCount(user.getLoginCount() + 1);
                sysUserModelMapper.update(user);

            }
        } else {
            msg.setStatus(ExceptionEnum.E_10.getStatus());
            msg.setMsg(ExceptionEnum.E_10.getMsg());
        }

        return msg;
    }

    @RequestMapping("/main")
    public ModelAndView main() {
        SysUserModel user = SessionUtils.getSysUserSession();
        List<TaskBucketInfoModel> timList = userService.getTaskSysUser(user.getId());
        SessionUtils.setAssignSysUserSession(timList);
        if (user != null) {
            Map<String, Object> context = new HashMap<String, Object>();
            List<SysMenuModel> rootMenus = new ArrayList<SysMenuModel>();
            List<SysMenuModel> childMenus = new ArrayList<SysMenuModel>();
            buildingMenu(rootMenus, childMenus, user);
            RemindBean rb = sysUserService.getAllRemindCount();


            //查询客服的账号和密码
            Map<String,Object> paramMap = new HashMap<String ,Object>();
            paramMap.put("sysUserId",user.getId());
            List<CallCenterUserInfoModel> dataList = callCenterUserInfoModelEMapper.selectListByParam(paramMap);
            if(!dataList.isEmpty()){
                context.put("account", dataList.get(0).getAccount());
                context.put("password", dataList.get(0).getPwd());
            }else{
                context.put("account", "");
                context.put("password", "");
            }
            context.put("remind", rb);
            context.put("menuList", treeMenu(rootMenus, childMenus));

            return new ModelAndView("view/main/index", context);
        }
        return new ModelAndView("view/main/login.html");
    }

    private void buildingMenu(List<SysMenuModel> rootMenus, List<SysMenuModel> childMenus, SysUserModel user) {
        //超级管理员
        if (user != null && 1 == user.getSuperAdmin()) {
            rootMenus.addAll(sysMenuService.getRootMenu(null));// 查询所有根节点
            childMenus.addAll(sysMenuService.getChildMenu());//查询所有子节点
        } else {
            rootMenus.addAll(sysMenuService.getRootMenuByUser(user.getId()));//根节点
            childMenus.addAll(sysMenuService.getChildMenuByUser(user.getId()));//子节点
        }

        //给当前用户增加除角色外的权限

/*
        SysUserMenuRelModelCriteria sysUserMenuRelModelCriteria = new SysUserMenuRelModelCriteria();
        sysUserMenuRelModelCriteria.createCriteria().andUserIdEqualTo(user.getId());

        List<SysUserMenuRelModel> sysUserMenuRelList = sysUserMenuRelService.selectByCriteria(sysUserMenuRelModelCriteria);
        if (sysUserMenuRelList.size() > 0) {
            childMenus.clear();
        }

        for (SysUserMenuRelModel sysUserMenuRelModel : sysUserMenuRelList) {
            SysMenuV2Model sysMenu = sysMenuService.selectByPrimaryKey(sysUserMenuRelModel.getMenuId());
            if (sysMenu != null) {
                childMenus.add(sysMenu);
            }
        }*/
    }

    private List<TreeNode> treeMenu(List<SysMenuModel> rootMenus, List<SysMenuModel> childMenus) {
        TreeUtil util = new TreeUtil(rootMenus, childMenus);
        return util.getTreeNode();
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Object outLogin() {
        SessionUtils.removeSysUserSession();
        SessionUtils.removeTypeSession();
        SessionUtils.removeLengthSession();
        return msg;
    }

    @ResponseBody
    @RequestMapping("/updatePassword")
    public Object updatePassword(String pw, String pwNew) {

        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("email",SessionUtils.getSysUserSession().getEmail());
        paramMap.put("pwd",MethodUtil.MD5(pw));
        List<SysUserModel> models = sysUserModelEMapper.selectListByParam(paramMap);

        if (models == null || models.size() < 1) {
            msg.setStatus(com.topjet.common.exception.pms.ExceptionEnum.E_10.getStatus());
            msg.setMsg(com.topjet.common.exception.pms.ExceptionEnum.E_10.getMsg());
        } else {
            SysUserModel model = models.get(0);
            model.setPwd(MethodUtil.MD5(pwNew));
            sysUserService.updateByPrimaryKey(model);
            msg.setStatus(com.topjet.common.exception.pms.ExceptionEnum.E_0.getStatus());
            msg.setMsg(com.topjet.common.exception.pms.ExceptionEnum.E_0.getMsg());
            SessionUtils.removeSysUserSession();
        }
        return msg;
    }


    @ResponseBody
    @RequestMapping("/verfiedPassword")
    public Object verfiedPassword(String RQCode, HttpServletResponse response) {
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());

        Object userId = SessionUtils.getAttr("userId");
        if (userId == null) {
            msg.setStatus(ExceptionEnum.E_15.getStatus());
            msg.setMsg(ExceptionEnum.E_15.getMsg());
            return msg;
        }

        Integer result = dynamicPasswordService.verify((Integer) userId, RQCode);
        if (result == null || result == 1) {
            msg.setStatus(ExceptionEnum.E_10.getStatus());
            msg.setMsg(ExceptionEnum.E_10.getMsg());
            return msg;
        }
        SysUserModel sysUserModel = sysUserService.selectByPrimaryKey((Integer) userId);
        if (sysUserModel == null) {
            msg.setStatus(ExceptionEnum.E_10.getStatus());
            msg.setMsg(ExceptionEnum.E_10.getMsg());
            return msg;
        }
        SessionUtils.setSysUserSession(sysUserModel);
        return msg;
    }

}
