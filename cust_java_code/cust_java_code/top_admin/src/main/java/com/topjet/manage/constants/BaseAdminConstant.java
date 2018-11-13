package com.topjet.manage.constants;

/**
 * Created by Tym on 2016/8/24.
 * 管理平台2.1常量
 */
public class BaseAdminConstant {

    /**
     * 数据库表列delete标识
     */
    public static final Integer TABLE_COLUMN_DELETE=0;

    /**
     * 创建用户：是否是超级管理员 0不是 1是
     */
    public static final Integer IS_SUPER_ADMIN_TRUE=1;

    /**
     * 创建用户：是否是超级管理员 0不是 1是
     */
    public static final Integer IS_SUPER_ADMIN_FALSE=0;

    /**
     * 部门角色关联表type  0关联菜单
     */
    public static final Integer SYSROLEREL_TYPE_ONE=0;

    /**
     * 部门角色关联表type  1关联用户
     */
    public static final Integer SYSROLEREL_TYPE_TWO=1;

    /**
     * 当前用户标识
     */
    public  static final String SESSION_USER = "session_user";


    /**
     * 线下认证审核权限判断(根据会员认证审核id判断)拥有这个权限则新增whiteUser用户
     */
    public static final Integer WHITEUSER_TAG=118;
}
