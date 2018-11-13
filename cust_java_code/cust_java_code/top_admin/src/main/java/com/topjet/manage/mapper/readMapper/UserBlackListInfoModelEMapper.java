package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.UserBlackListInfoModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by liyj on 2017/10/16.
 */
public interface UserBlackListInfoModelEMapper {

    /**
     * 根据userid查询黑名单
     * @param userId
     * @return
     */
    @Select("SELECT * from userDB.userBlackListInfo ub where ub.userId = #{userId} and ub.deleted = 0")
    public UserBlackListInfoModel getBlackListInfoByUserId(@Param("userId") Integer userId);

}
