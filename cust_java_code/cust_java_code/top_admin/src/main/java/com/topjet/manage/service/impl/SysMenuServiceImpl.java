package com.topjet.manage.service.impl;

import com.topjet.manage.domain.bean.BaseBean;
import com.topjet.manage.domain.bean.SysMenuBean;
import com.topjet.manage.domain.model.SysMenuModel;
import com.topjet.manage.mapper.readMapper.SysMenuModelEMapper;
import com.topjet.manage.mapper.writeMapper.SysMenuModelMapper;
import com.topjet.manage.mapper.writeMapper.SysMenuRoleRelModelMapper;
import com.topjet.manage.service.SysMenuService;
import com.topjet.tool.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取顶级菜单
 * @author zhaocj
 * @create 2017-08-03 9:55
 **/
@Service
@Transactional
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuModelMapper sysMenuModelMapper;
    @Autowired
    private SysMenuModelEMapper sysMenuModelEMapper;
    @Autowired
    private SysMenuRoleRelModelMapper sysMenuRoleRelModelMapper;

    public void addSysMenu(SysMenuBean menu) throws Exception {
        SysMenuModel model = new SysMenuModel();
        model.setDeleted(BaseBean.DELETED.NO.key);
        model.setUrl(menu.getUrl());
        model.setRank(menu.getRank());
        model.setParentId(model.getParentId());
        model.setUpdateTime(DateUtil.now());
        model.setCreateTime(DateUtil.now());
        model.setActions(menu.getActions());
        model.setName(menu.getName());
        model.setParentId(menu.getParentId());
        model.setCssStyle(menu.getCssStyle());
        sysMenuModelMapper.insert(model);
    }


    public void updateSysMenu(SysMenuBean menu) throws Exception {
        SysMenuModel model = sysMenuModelEMapper.selectByPrimaryKey(menu.getId());
        /*model.setDeleted(menu.getDeleted());*/
        model.setActions(model.getActions());
        model.setName(menu.getName());
        model.setParentId(menu.getParentId());
        model.setRank(menu.getRank());
        model.setUpdateTime(DateUtil.now());
        model.setUrl(menu.getUrl());
        model.setCssStyle(menu.getCssStyle());
        sysMenuModelMapper.update(model);
    }
    //删除
    public void delete(Integer[] ids) throws Exception {
        this.deletes(ids);//删除菜单的时候 指某个部门的某个菜单 并不会影响部门
        //删除关联关系
        for (Object id : ids) {
            //根据菜单id操作删除菜单部门表
            sysMenuRoleRelModelMapper.deleteByMenuId((Integer)id);
        }
    }

    @Override
    public List<SysMenuModel> getRootMenu(Integer menuId) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("deleted",0);
        paramMap.put("parentId",0);
        if(menuId != null){
            paramMap.put("id",menuId);
        }
        return sysMenuModelEMapper.selectListByParam(paramMap);
    }

    @Override
    public List<SysMenuModel> getChildMenu() {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("deleted",0);
        return sysMenuModelEMapper.selectSecondMenuListByParam(paramMap);
    }

    @Override
    public List<SysMenuModel> getRootMenuByUser(Integer userId) {

        return sysMenuModelEMapper.getRootMenuByUser(userId);
    }

    @Override
    public List<SysMenuModel> getChildMenuByUser(Integer userId) {
        return sysMenuModelEMapper.getChildMenuByUser(userId);
    }
    @Override
    public List<SysMenuBean> getMenuList(SysMenuModel sysMenuModel) {
        return sysMenuModelEMapper.getMenuList(sysMenuModel);
    }

    @Override
    public int getMenuCount(SysMenuModel sysMenuModel) {
        return sysMenuModelEMapper.getMenuCount(sysMenuModel);
    }


    @Override
    public List<SysMenuModel> queryMenu() {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("parentId",0);
        return sysMenuModelEMapper.getRootMenu(paramMap);
    }

    @Override
    public SysMenuModel selectByPrimaryKey(Integer id) {
        return sysMenuModelEMapper.selectByPrimaryKey(id);
    }

    public List<SysMenuBean> getMenuByRoleId(Integer roleId) {

        return sysMenuModelEMapper.getMenuByRoleId(roleId);
    }

    private void deletes(Integer... ids) throws Exception {
        if (ids == null || ids.length < 1) {
            return;
        }
        for (Integer id : ids) {
            sysMenuModelMapper.deleteByPrimaryKey(id);
        }
    }
}
