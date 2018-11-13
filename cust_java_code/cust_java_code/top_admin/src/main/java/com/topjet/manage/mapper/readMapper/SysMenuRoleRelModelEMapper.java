package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.SysMenuRoleRelModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface SysMenuRoleRelModelEMapper extends BaseEMapper<SysMenuRoleRelModel> {

    public void deleteByPrimaryKey(Integer id);

    public void deleteByRoleId(Integer id);
    /**
     * 根据菜单id，关联类型删除
     */
    public void deleteByMenuId(Integer menuId);
	
}