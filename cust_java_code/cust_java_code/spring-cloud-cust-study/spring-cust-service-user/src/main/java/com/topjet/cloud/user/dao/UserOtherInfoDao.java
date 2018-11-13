package com.topjet.cloud.user.dao;

import com.topjet.cloud.user.dao.model.UserOtherInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * Created by hongtaoer-win on 2017/8/8.
 * userOtherInfo 单表操作dao
 */
@Mapper
public interface UserOtherInfoDao {



    /**
     * 根据用户ID修改用户固定电话
     * @param
     * @return
     */
    @Update("UPDATE userOtherInfo set telephone = #{telephone} , resident1 = #{resident1} , resident2 = #{resident2} , resident3 = #{resident3} ,residentCityId = #{residentCityId} , companyName = #{companyName} , businessAddress = #{businessAddress} , businessAddressCityId = #{businessAddressCityId} WHERE userId = #{userId}")
    Integer updateTelephoneByUid(UserOtherInfoModel model);

}
