package com.topjet.manage.service.impl;

import com.topjet.common.SessionUtils;
import com.topjet.manage.constants.CommonConstant;
import com.topjet.manage.domain.model.SysMenuRoleRelModel;
import com.topjet.manage.domain.model.SysRoleModel;
import com.topjet.manage.mapper.readMapper.SysRoleModelEMapper;
import com.topjet.manage.service.SysMenuRoleService;
import com.topjet.manage.service.SysRoleService;
import com.topjet.manage.mapper.writeMapper.SysMenuRoleRelModelMapper;
import com.topjet.manage.mapper.writeMapper.SysRoleModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaocj
 * @create 2017-08-03 20:05
 **/
@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleModelMapper sysRoleModelMapper;
    @Autowired
    private SysRoleModelEMapper sysRoleModelEMapper;
    @Autowired
    private SysMenuRoleRelModelMapper sysMenuRoleRelModelMapper;
    @Autowired
    private SysMenuRoleService sysMenuRoleService;

    @Override
    public List<SysRoleModel> listSysRoleModel(SysRoleModel sysRoleModel) {
        Map<String,Object> paraMap = new HashMap<String,Object>();
        paraMap.put("deleted", CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
        paraMap.put("offset",sysRoleModel.getOffset());
        paraMap.put("limit",sysRoleModel.getLimit());
        return sysRoleModelEMapper.selectPageListByParam(paraMap);
    }

    @Override
    public Integer countSysRoleModel(SysRoleModel sysRoleModel) {
//-----------
        return sysRoleModelEMapper.getCountByEntity(sysRoleModel);
    }

    @Override
    public SysRoleModel selectSysRoleModel(Integer sysRoleId) {
        return sysRoleModelEMapper.selectByPrimaryKey(sysRoleId);
    }

    /**
     * 添加
     *
     * @param role
     * @param menuIds
     * @throws Exception
     */
    public void add(SysRoleModel role, Integer[] menuIds, Integer[] btnIds) throws Exception {
        Integer id = sysRoleModelMapper.insert(role);
        addRoleMenuRel(role.getId(), menuIds);
    }

    /**
     * 添加角色&菜单关系
     */
    public void addRoleMenuRel(Integer roleId, Integer[] menuIds) throws Exception {
        if (roleId == null || menuIds == null || menuIds.length < 1) {
            return;
        }
        for (Integer menuid : menuIds) {
            SysMenuRoleRelModel model = new SysMenuRoleRelModel();
            model.setRoleId(roleId);
            model.setSysMenuId(menuid);
            model.setCreateBy(SessionUtils.getSysUserSession().getId());
            model.setUpdateBy(SessionUtils.getSysUserSession().getId());
            sysMenuRoleRelModelMapper.insert(model);
        }
    }

    @Override
    public SysRoleModel selectByPrimaryKey(Integer id) {
        return sysRoleModelEMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改
     *
     * @param role
     * @param menuIds
     * @throws Exception
     */
    public void update(SysRoleModel role, Integer[] menuIds, Integer[] btnIds) throws Exception {
        sysRoleModelMapper.update(role);
        //清除关联关系
        sysMenuRoleService.deleteByRoleId(role.getId());
        addRoleMenuRel(role.getId(), menuIds);
    }

    @Override
    public List<SysRoleModel> queryByUserId(Integer userId) {
        return sysRoleModelEMapper.queryByUserId(userId);
    }

    @Override
    public List<SysRoleModel> selectListByParam(Map<String, Object> param) {
        return sysRoleModelEMapper.selectListByParam(param);
    }
}
