package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.bean.UserInfoBean;
import com.topjet.tool.common.constant.TopJetDBConstant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserInfoModelMapper extends BaseMapper<UserInfoModelMapper> {

    //修改电话号码
    @Select(" CALL "+ TopJetDBConstant.DB_NAME_USER+".pr_updateMobile(#{oldMobile},#{newMobile}) ")
    public void updateMobile(UserInfoBean userInfoBean);

}