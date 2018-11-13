package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.SysUserModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysUserModelEMapper extends BaseEMapper<SysUserModel> {

    List<SysUserModel> selectListByEntity(SysUserModel sysUserModel);

    /**
     * 根据角色ID获取所有系统用户ID
     * @param roleId
     * @return
     */
    List<Integer> getRoleAllUserId(Integer roleId);

    List<SysUserModel> selectAllRecord(SysUserModel sym);

    Integer countSelectAllRecord(Integer userId);

    List<Integer> getUserId(Integer createBy);

}