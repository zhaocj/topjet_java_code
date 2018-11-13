package com.topjet.cloud.user.dao;


import com.topjet.cloud.user.dao.model.UserInfoModel;
import org.apache.ibatis.annotations.*;

/**
 * Created by hongtaoer-win on 2017/8/3.
 */
@Mapper
public interface UserInfoDao {


    /**
     * 通过ID查询用户实名认证状态
     * @param userId
     * @return
     */
    @Select("SELECT id ,useStatus,mobile FROM  userInfo WHERE deleted = 0 and  id = #{userId}")
    UserInfoModel selectUserStatusById(Integer userId);


    /**
     * 通过ID修改用户实名认证状态
     * @param model
     * @return
     */
    @Update("UPDATE userInfo set useStatus = #{useStatus}, userType = #{userType} , idNo = #{idNo}, username = #{username}  WHERE id = #{id}")
    Integer updateUserStatusById(UserInfoModel model);


    /**
     * 通过ID查询用户身份认证状态
     * @param userId
     * @return
     */
    @Select("SELECT id, userAuthStatus FROM  userInfo WHERE deleted = 0 and id = #{userId}")
    UserInfoModel selectUserAuthStatusById(Integer userId);


    /**
     * 通过ID修改身份认证状态
     * @param model
     * @return
     */
    @Update("UPDATE userInfo set userAuthStatus = #{userAuthStatus}  WHERE id = #{id}")
    Integer updateUserAuthStatusById(UserInfoModel model);


    /**
     * 通过ID查询头像审核状态
     * @param userId
     * @return
     */
    @Select("SELECT id,iconStatus FROM  userInfo WHERE deleted = 0 and id = #{userId}")
    UserInfoModel selectIconStatusById(Integer userId);


    /**
     * 通过ID修改头像审核状态
     * @param model
     * @return
     */
    @Update("UPDATE userInfo set iconStatus = #{iconStatus}  WHERE id = #{id}")
    Integer updateIconStatusById(UserInfoModel model);

    /**
     * 通过用户ID修改用户类型
     * @param model
     * @return
     */
    @Update("UPDATE userInfo set userType = #{userType}  WHERE id = #{id}")
    Integer updateUserTypeById(UserInfoModel model);


}
