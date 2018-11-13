package com.topjet.manage.service;

import com.topjet.manage.domain.model.SysUserRoleRelModel;

import java.util.List;
import java.util.Map;

/**
 * @author zhaocj
 * @create 2017-08-05 14:28
 **/
public interface SysUserRoleRelService {

    public List<SysUserRoleRelModel> queryByRoleId(Integer roleId, Integer relType);

    public List<SysUserRoleRelModel> queryByObjId(Integer objId, Integer relType);

    /**
     * 根据关联对象id,关联类型删除
     *
     * @param sysUserId
     */
    public void deleteByUserId(Integer sysUserId);

    /**
     * 根据角色id删除
     *
     * @param roleId
     */
    public void deleteByRoleId(Integer roleId);

    /**
     * 根据角色id,关联类型删除
     *
     * @param roleId
     * @param relType
     */
    public void deleteByRoleId(Integer roleId, Integer relType);

    List<SysUserRoleRelModel> selectListByParam(Map<String,Object> paraMap);

}
