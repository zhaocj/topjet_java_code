package com.topjet.manage.service;


import com.topjet.manage.domain.model.SysRoleModel;

import java.util.List;
import java.util.Map;

/**
 * Created by bjb074 on 2017/8/3.
 */
public interface SysRoleService {

     List<SysRoleModel> listSysRoleModel(SysRoleModel sysRoleModel);

     Integer countSysRoleModel(SysRoleModel sysRoleModel);

     SysRoleModel selectSysRoleModel(Integer sysRoleId);

     /**
      * 添加
      *
      * @param role
      * @param menuIds
      * @throws Exception
      */
     public void add(SysRoleModel role, Integer[] menuIds, Integer[] btnIds) throws Exception;

     /**
      * 添加角色&菜单关系
      */
     public void addRoleMenuRel(Integer roleId, Integer[] menuIds) throws Exception;

     public SysRoleModel selectByPrimaryKey(Integer id);

     /**
      * 修改
      *
      * @param role
      * @param menuIds
      * @throws Exception
      */
     public void update(SysRoleModel role, Integer[] menuIds, Integer[] btnIds) throws Exception;

     /**
      * 查询全部有效的权限
      *
      * @return
      */
     public List<SysRoleModel> queryByUserId(Integer userId);

     List<SysRoleModel> selectListByParam(Map<String,Object> param);
}
