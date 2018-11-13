package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.SysRoleModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysRoleModelMapper extends BaseMapper<SysRoleModel> {

    void deleteByPrimaryKey(Integer id);

}