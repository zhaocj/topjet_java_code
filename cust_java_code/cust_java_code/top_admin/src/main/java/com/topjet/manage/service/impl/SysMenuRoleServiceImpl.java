package com.topjet.manage.service.impl;

import com.topjet.manage.mapper.writeMapper.SysMenuRoleRelModelMapper;
import com.topjet.manage.service.SysMenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author zhaocj
 * @create 2017-08-04 16:48
 **/
@Service
@Transactional
public class SysMenuRoleServiceImpl implements SysMenuRoleService {

    @Autowired
    private SysMenuRoleRelModelMapper sysMenuRoleRelModelMapper;
    @Override
    public void deleteByRoleId(Integer roleId) {
        if(roleId != null){
            sysMenuRoleRelModelMapper.deleteByRoleId(roleId);
        }
    }

    @Override
    public void deleteByMenuId(Integer menuId) {
        if(menuId != null){
            sysMenuRoleRelModelMapper.deleteByMenuId(menuId);
        }
    }
}
