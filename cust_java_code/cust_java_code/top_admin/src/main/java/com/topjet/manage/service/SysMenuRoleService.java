package com.topjet.manage.service;

/**
 * Created by bjb074 on 2017/8/4.
 */
public interface SysMenuRoleService {

    /**
     * 根据角色id,关联类型删除
     *
     * @param roleId
     */
    public void deleteByRoleId(Integer roleId);
    /**
     * 根据菜单id，关联类型删除
     */
    public void deleteByMenuId(Integer menuId);

}
