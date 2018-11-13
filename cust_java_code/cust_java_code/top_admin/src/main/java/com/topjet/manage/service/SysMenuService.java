package com.topjet.manage.service;


import com.topjet.manage.domain.bean.SysMenuBean;
import com.topjet.manage.domain.model.SysMenuModel;

import java.util.List;

/**
 * Created by zhaocj on 2017/8/3.
 */
public interface SysMenuService {
    public void addSysMenu(SysMenuBean menu) throws Exception;

    public void updateSysMenu(SysMenuBean menu) throws Exception;
    //删除
    public void delete(Integer... ids) throws Exception;
    //查询全部菜单
    public List<SysMenuBean> getMenuList(SysMenuModel sysMenuModel);
    //菜单页数
    public int getMenuCount(SysMenuModel sysMenuModel);
    /**
     * 获取顶级菜单
     * @param menuId
     * @return
     */
    public List<SysMenuModel> getRootMenu(Integer menuId);


    /**
     * 查询父级菜单 parentId is null
     * @return
     */
    public List<SysMenuModel> queryMenu();
    /**
     * 获取子菜单
     *
     * @return
     */
    public List<SysMenuModel> getChildMenu();

    /**
     * 根据登陆用户获取一级菜单
     * @param userId
     * @return
     */
    public List<SysMenuModel> getRootMenuByUser(Integer userId);

    /**
     * 根据登陆用户获取子菜单
     * @param userId
     * @return
     */
    public List<SysMenuModel> getChildMenuByUser(Integer userId);
    //根据id查询
    public SysMenuModel selectByPrimaryKey(Integer id);

    /**
     * 根据权限id查询菜单
     *
     * @param roleId
     * @return
     */
    public List<SysMenuBean> getMenuByRoleId(Integer roleId);
}
