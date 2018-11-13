package com.topjet.manage.service;


import com.topjet.manage.common.dynamicpassword.bean.SysUserResonseBean;
import com.topjet.manage.domain.bean.RemindBean;
import com.topjet.manage.domain.model.SysUserModel;

import java.util.List;
import java.util.Map;

/**
 * Created by bjb074 on 2017/8/4.
 */
public interface SysUserService {

    public List<Integer> getRoleAllUserId(Integer roleId);

    List<SysUserModel> listPageSysUser(Map<String, Object> paramMap);

    Integer countSysUser(Map<String, Object> paramMap);

    /**
     * 获得用户列表
     *
     * @param
     * @return
     */
    public List<SysUserModel> selectAllRecord(SysUserModel sym);

    /**
     * 统计selectAllRecord数
     * @param userId
     * @return
     */
    public Integer countSelectAllRecord(Integer userId);

    SysUserModel selectByPrimaryKey(Integer id);

    /**
     * 根据创建人查询用户
     * @param createBy
     * @return
     */
    public List<Integer> getUserId(Integer createBy);

    int deleteByPrimarykey(Integer id);

    public int getUserCountByEmail(String email);

    public int insertAndGetId(SysUserModel sym);

    int updateByPrimaryKey(SysUserModel sysUserModel);

    List<SysUserModel> selectSysUser(Map<String,Object> paramMap);

    /* 管理平台用户提醒*/
    public RemindBean getAllRemindCount();

    SysUserResonseBean getDynamicPasswordRQ(String path);

}
