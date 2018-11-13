package com.topjet.cloud.user.dao;

import com.topjet.cloud.user.dao.model.UserBlackListInfoModel;
import org.apache.ibatis.annotations.*;

/**
 * Created by zhangn on 2017/8/7.
 */
@Mapper
public interface UserBlackListInfoDao {

    /**
     * 根据 type 获取用户黑名单
     * @param model
     * @return
     */
    @Select("SELECT id,userId,bType,deleted FROM userBlackListInfo WHERE userId = #{userId} and bType = #{bType}")
    UserBlackListInfoModel getUserBlackByType(UserBlackListInfoModel model);


    /**
     * 添加用户黑名单
     * 需要userid, bType
     */
    @Insert("INSERT INTO userBlackListInfo(userId,bType) VALUES(#{userId},#{bType})")
    Integer insertUserBlackList(UserBlackListInfoModel userBlackListInfoModel);


    /**
     * 把用户移除黑名单
     * @param model
     * @return
     */
    @Update("UPDATE userBlackListInfo SET updateTime = #{updateTime} , deleted  = ${deleted}  WHERE id = ${id}")
    UserBlackListInfoModel frostUserBlackById(UserBlackListInfoModel model);



}
