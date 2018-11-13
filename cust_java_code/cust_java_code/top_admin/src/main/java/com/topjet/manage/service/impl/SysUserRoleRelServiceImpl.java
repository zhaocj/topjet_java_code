package com.topjet.manage.service.impl;

import com.topjet.manage.domain.model.SysUserRoleRelModel;
import com.topjet.manage.mapper.readMapper.SysUserRoleRelModelEMapper;
import com.topjet.manage.mapper.writeMapper.SysUserRoleRelModelMapper;
import com.topjet.manage.service.SysUserRoleRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author zhaocj
 * @create 2017-08-05 14:29
 **/
@Service
@Transactional
public class SysUserRoleRelServiceImpl implements SysUserRoleRelService{
    @Autowired
    private SysUserRoleRelModelMapper sysUserRoleRelModelMapper;

    @Autowired
    private SysUserRoleRelModelEMapper sysUserRoleRelModelEMapper;

    @Override
    public List<SysUserRoleRelModel> queryByRoleId(Integer roleId, Integer relType) {
        return null;
    }

    @Override
    public List<SysUserRoleRelModel> queryByObjId(Integer objId, Integer relType) {
        return null;
    }

    @Override
    public void deleteByUserId(Integer sysUserId) {
        sysUserRoleRelModelMapper.deleteByUserId(sysUserId);
    }

    @Override
    public void deleteByRoleId(Integer roleId) {

    }

    @Override
    public void deleteByRoleId(Integer roleId, Integer relType) {

    }

    @Override
    public List<SysUserRoleRelModel> selectListByParam(Map<String, Object> paraMap) {

        return sysUserRoleRelModelEMapper.selectListByParam(paraMap);
    }
}
