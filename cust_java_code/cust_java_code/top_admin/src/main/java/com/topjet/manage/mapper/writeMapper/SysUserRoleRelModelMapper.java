package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.SysUserRoleRelModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysUserRoleRelModelMapper extends BaseMapper<SysUserRoleRelModel> {

    public void deleteByPrimaryKey(Integer id);

    void deleteByUserId(Integer sysUserId);
	
}