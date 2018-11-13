package com.topjet.manage.controller;

import com.topjet.common.ExceptionEnum;
import com.topjet.manage.domain.bean.BaseBean;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.bean.SysMenuBean;
import com.topjet.manage.domain.model.SysMenuModel;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by liyj on 2017/8/3.
 */
@Controller
@RequestMapping("/sysMenu/")
public class SysMenuController extends BaseController {
    @Autowired
    private SysMenuService sysMenuService;

    @ResponseBody
    @RequestMapping("list")
    public Object list(SysMenuModel sysMenuModel){
        List<SysMenuBean> dataList = sysMenuService.getMenuList(sysMenuModel);
        Integer total = sysMenuService.getMenuCount(sysMenuModel);
        List<SysMenuModel> menu = sysMenuService.queryMenu();
        PaginationBean beans = new PaginationBean();
        beans.setFooter(menu);
        beans.setRows(dataList);
        beans.setTotal(total);
        return beans;
    }

    /*
* @author        :  liyj
* @Create Date   :  2017年8月3日
* @function		 :	初始子菜单列表
* */
    @ResponseBody
    @RequestMapping("queryMenuItem")
    public Object queryMenu(Integer id) throws Exception {
        SysMenuModel model = sysMenuService.selectByPrimaryKey(id);
        return model;
    }

    /*
    * @author        :   liyj
    * @Create Date   :  2017年8月3日
   * @function		 :	初始父菜单列表
   * */
    @ResponseBody
    @RequestMapping("queryParentList")
    public Object queryList(SysMenuModel model) throws Exception {
        List<SysMenuModel> menu = sysMenuService.queryMenu();
        PaginationBean beans = new PaginationBean();
        beans.setRows(menu);
        return beans;
    }

    /*
    * @author        :  liyj
    * @Create Date   :  2017年8月3日
    * @function		 :	子菜单详情
    * */
    @RequestMapping("detail")
    public ModelAndView detail(Integer id) throws Exception {
        ModelAndView view = new ModelAndView("view/sysAdmin/sysMenu/detail");
        view.addObject("parentId", id);
        return view;
    }

    /*
     * @author        :  liyj
     * @Create Date   :  2017年8月3日
     * @function	  :	保存菜单信息
     * */
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(SysMenuBean bean, Integer[] menuIds)
            throws Exception {

        if (bean.getId() == null) {
            bean.setDeleted(BaseBean.DELETED.NO.key);
            sysMenuService.addSysMenu(bean);
        } else {
            sysMenuService.updateSysMenu(bean);
        }
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
    }

    /*
    * @author        :  liyj
    * @Create Date   :  2017年8月3日
    * @function	  :	删除菜单信息包含子菜单不准删除
    * */
    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Integer id) throws Exception {

        //查询是否有子菜单，无才能删除
        SysMenuModel model = new SysMenuModel();
        model.setId(id);
        model.setSort("id");
        model.setOrder("desc");
        model.setOffset(0);
        model.setLimit(10);
        model.setPage(1);
        model.setRows(10);
        List<SysMenuBean> menuList = sysMenuService.getMenuList(model);
        if (menuList != null && menuList.size() > 0) {
            SysMenuBean sysMenuBean = menuList.get(0);

            if (sysMenuBean.getSubCount() > 0) {
                msg.setStatus(ExceptionEnum.E_17.getStatus());
                msg.setMsg(ExceptionEnum.E_17.getMsg());
                return msg;
            }
        }
        sysMenuService.delete(id);
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
    }

}
