package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.bean.SysMenuBean;
import com.topjet.manage.domain.model.SysMenuModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface SysMenuModelMapper extends BaseMapper<SysMenuModel> {

    //添加
    public void insert(SysMenuBean menu) throws Exception;
//修改
    public void update(SysMenuBean menu) throws Exception;
    //删除
    public void deleteByPrimaryKey(Integer id)throws Exception;

}