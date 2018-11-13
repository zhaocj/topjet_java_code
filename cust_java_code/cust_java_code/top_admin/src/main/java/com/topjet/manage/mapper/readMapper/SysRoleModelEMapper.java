package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.SysRoleModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysRoleModelEMapper extends BaseEMapper<SysRoleModel> {

    List<SysRoleModel> selectListByEntity(SysRoleModel sysRoleModel);

    Integer getCountByEntity(SysRoleModel sysRoleModel);

    int queryCurrId();

    void deleteByPrimaryKey(Integer id);

    List<SysRoleModel> queryByUserId(Integer userid);

}