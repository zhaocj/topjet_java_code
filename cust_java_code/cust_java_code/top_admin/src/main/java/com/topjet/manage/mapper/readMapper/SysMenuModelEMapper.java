package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.bean.SysMenuBean;
import com.topjet.manage.domain.model.SysMenuModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface SysMenuModelEMapper extends BaseEMapper<SysMenuModel> {

    //查询全部菜单
    public List<SysMenuBean> getMenuList(SysMenuModel sysMenuModel);
    //菜单页数
    public int getMenuCount(SysMenuModel sysMenuModel);
    /**
     * 查询所有父级菜单，parentId Is null
     * @return
     */
    public List<SysMenuModel> queryMenu();
    /**
     * 获取顶级菜单
     *
     * @return
     */
    public List<SysMenuModel> getRootMenu(Map map);

    List<SysMenuModel> selectSecondMenuListByParam(Map<String, Object> paramMap);

    /**
     * 根据用户id查询父菜单菜单
     *
     * @param userId
     * @return
     */
    public List<SysMenuModel> getRootMenuByUser(Integer userId);

    /**
     * 根据用户id查询子菜单菜单
     *
     * @param userId
     * @return
     */
    public List<SysMenuModel> getChildMenuByUser(Integer userId);
    //根据id查询
    public SysMenuModel selectByPrimaryKey(Integer id);
    //添加
    public void insert(SysMenuBean menu) throws Exception;
//修改
    public void update(SysMenuBean menu) throws Exception;
    //删除
    public void deleteByPrimaryKey(Integer id)throws Exception;

    /**
     * 根据角色id获取菜单
     * @param roleId
     * @return
     */
    public List<SysMenuBean> getMenuByRoleId(Integer roleId);


}