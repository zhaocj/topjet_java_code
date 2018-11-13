package com.topjet.manage.controller;

import com.topjet.common.ExceptionEnum;
import com.topjet.common.SessionUtils;
import com.topjet.manage.constants.CommonConstant;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.bean.SysMenuBean;
import com.topjet.manage.domain.bean.SysMenuTreeBean;
import com.topjet.manage.domain.model.SysMenuModel;
import com.topjet.manage.domain.model.SysMenuRoleRelModel;
import com.topjet.manage.domain.model.SysRoleModel;
import com.topjet.manage.domain.model.SysUserRoleRelModel;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.mapper.readMapper.SysMenuModelEMapper;
import com.topjet.manage.mapper.readMapper.SysMenuRoleRelModelEMapper;
import com.topjet.manage.mapper.readMapper.SysUserRoleRelModelEMapper;
import com.topjet.manage.mapper.writeMapper.SysMenuModelMapper;
import com.topjet.manage.mapper.writeMapper.SysMenuRoleRelModelMapper;
import com.topjet.manage.mapper.writeMapper.SysRoleModelMapper;
import com.topjet.manage.mapper.writeMapper.SysUserRoleRelModelMapper;
import com.topjet.manage.service.SysMenuService;
import com.topjet.manage.service.SysRoleService;
import com.topjet.manage.service.SysUserService;
import com.topjet.tool.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaocj
 * @create 2017-08-03 19:32
 **/
@Controller
@RequestMapping("/sysRoleController")
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleModelMapper sysRoleModelMapper;
    @Autowired
    private SysUserRoleRelModelMapper sysUserRoleRelModelMapper;
    @Autowired
    private SysUserRoleRelModelEMapper sysUserRoleRelModelEMapper;
    @Autowired
    private SysMenuRoleRelModelMapper sysMenuRoleRelModelMapper;
    @Autowired
    private SysMenuRoleRelModelEMapper sysMenuRoleRelModelEMapper;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuModelMapper sysMenuModelMapper;
    @Autowired
    private SysMenuModelEMapper sysMenuModelEMapper;

    @RequestMapping("/list")
    @ResponseBody
    public Object listSysRole(SysRoleModel sysRoleModel, HttpServletRequest request){

        PaginationBean pageBean = new PaginationBean();
        pageBean.setRows(sysRoleService.listSysRoleModel(sysRoleModel));
        pageBean.setTotal(sysRoleService.countSysRoleModel(sysRoleModel));
        return pageBean;
    }

    @RequestMapping("/roleDetail")
    public ModelAndView sysRoleDetail(Integer id){
        ModelAndView mv = new ModelAndView("view/sysAdmin/sysRole/roleDetail");
        if(id != null && id != 0){
            mv.addObject("sysRoleModel",sysRoleService.selectSysRoleModel(id));
        }
        return mv;
    }


    @RequestMapping("getMenuTree")
    @ResponseBody
    public Object getMenuTree(Integer id, Integer userId) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("deleted", CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
        List<SysMenuModel> menuModels = sysMenuModelEMapper.selectListByParam(paramMap);
        List<SysMenuTreeBean> menuTreeBeans = new ArrayList<SysMenuTreeBean>();
        if (id != null) {
            //设置部门权限
            List<SysMenuBean> menuList = sysMenuService.getMenuByRoleId(id);
            for (SysMenuModel menu : menuModels) {
                SysMenuTreeBean bean = new SysMenuTreeBean();
                bean.setText(menu.getName());
                bean.setId(menu.getId().toString());
                bean.setIsCheck("false");
                bean.setIsShow("false");
                for (SysMenuBean sysMenuBean : menuList) {
                    if (sysMenuBean.getId().equals(menu.getId())) {
                        bean.setIsCheck("true");
                        bean.setIsShow("true");
                        continue;
                    }
                }
                if (menu.getParentId() == null) {
                    bean.setPid("0");
                } else {
                    bean.setPid(menu.getParentId().toString());
                }
                menuTreeBeans.add(bean);
            }

            if (userId != null) {
                //修改 只选中自身权限，但是可选其他权限
                Map<String,Object> paraMap = new HashMap<String,Object>();
                paraMap.put("sysUserId", userId);
                Integer roleId = sysUserRoleRelModelEMapper.selectListByParam(paraMap).get(0).getRoleId();
                paraMap.clear();
                paraMap.put("roleId",roleId);
                List<SysMenuRoleRelModel> sysMenuRoleRelModels = sysMenuRoleRelModelEMapper.selectListByParam(paraMap);
                for (SysMenuTreeBean sysMenuTreeBean : menuTreeBeans) {
                    if (!sysMenuTreeBean.getPid().equals("0")) {
                        for (SysMenuRoleRelModel sysMenuRoleRelModel : sysMenuRoleRelModels) {
                            if (sysMenuTreeBean.getId().equals(sysMenuRoleRelModel.getSysMenuId().toString())) {
                                sysMenuTreeBean.setIsCheck("true");
                                break;
                            } else {
                                sysMenuTreeBean.setIsCheck("false");
                            }
                        }
                    }
                    if(sysMenuTreeBean.getIsShow().equals("false")){
                        sysMenuTreeBean.setIsCheck("false");
                    }
                }
            }
        } else {
            for (SysMenuModel menu : menuModels) {
                SysMenuTreeBean bean = new SysMenuTreeBean();
                bean.setText(menu.getName());
                bean.setId(menu.getId().toString());
                bean.setIsCheck("false");
                if (menu.getParentId() == null) {
                    bean.setPid("0");
                } else {
                    bean.setPid(menu.getParentId().toString());
                }
                menuTreeBeans.add(bean);
            }
        }
        return menuTreeBeans;
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object save(SysRoleModel model, Integer[] menuIds) throws Exception {
        if (model.getId() == null) {//保存
            model.setCreateBy(SessionUtils.getSysUserSession().getId());
            model.setUpdateBy(SessionUtils.getSysUserSession().getId());
            model.setCreateTime(DateUtil.now());
            model.setUpdateTime(DateUtil.now());
            model.setSysRolecol("");
            model.setDeleted(0);
            model.setVersion(0);
            sysRoleService.add(model, menuIds, null);
        } else {//update
            SysRoleModel modelUpdate = sysRoleService.selectByPrimaryKey(model.getId());
            modelUpdate.setUpdateTime(DateUtil.now());
            modelUpdate.setUpdateBy(SessionUtils.getSysUserSession().getId());
            modelUpdate.setRoleName(model.getRoleName());
            modelUpdate.setState(model.getState());
            modelUpdate.setDescr(model.getDescr());
            sysRoleService.update(modelUpdate, menuIds, null);

            //该部门下所有用户权限更新
        }
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Integer id) throws Exception {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("roleId",id);
        List<SysUserRoleRelModel> userRoleRelList = sysUserRoleRelModelEMapper.selectListByParam(paramMap);

        if (userRoleRelList.size() > 0) {
            msg.setStatus(ExceptionEnum.E_22.getStatus());
            msg.setMsg(ExceptionEnum.E_22.getMsg());
            return msg;
        }
        sysRoleModelMapper.deleteByPrimaryKey(id);
        sysMenuRoleRelModelMapper.deleteByRoleId(id);
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
    }

}
