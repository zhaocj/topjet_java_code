package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.SysUserModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysUserModelMapper extends BaseMapper<SysUserModel> {

    int deleteByPrimaryKey(Integer id);

}